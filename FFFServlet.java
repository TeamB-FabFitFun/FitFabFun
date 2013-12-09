
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.MessagingException;
import java.util.ArrayList;

import business.Member;
import business.Activity;
import business.ActivityDataBase;
import business.MemberDataBase;
import util.MailUtil;

public class FFFServlet extends HttpServlet {

    private static MemberDataBase memberDB;
    private static ActivityDataBase activityDB;

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        request.setAttribute("findUserId", "true");
        request.setAttribute("matchPassword", "true");

        Member mbr = (Member) session.getAttribute("member");

        if (!MemberDataBase.isInitialized()) {
            memberDB = new MemberDataBase();
        }

        // Prints members table - Debug only //
        //memberDB.printMembersTbl();

        if (!ActivityDataBase.isInitialized()) {
            activityDB = new ActivityDataBase();
        }
        session.setAttribute("activityDB", activityDB);

        // Prints Activity table - Debug only //
        //activityDB.printActivityTbl();

        String url = "";
        String action = request.getParameter("action");
        if ("about".equalsIgnoreCase(action)) {
            url = "/jsp/index.jsp";

        } else if ("member".equalsIgnoreCase(action)) {
            if (mbr == null) {
                url = "/jsp/login.jsp";
            } else {
                url = "/jsp/memberProfile.jsp";
            }

        } else if ("newMember".equalsIgnoreCase(action)) {
            if (mbr == null) {
                url = "/jsp/newMember.jsp";
            } else {
                url = "/jsp/memberProfile.jsp";
            }

        } else if ("class".equalsIgnoreCase(action)) {
            url = "/jsp/activity.jsp";

        } else if ("contact".equalsIgnoreCase(action)) {
            url = "/jsp/contactus.jsp";

        } else if ("logon".equalsIgnoreCase(action)) {
            // use email as the unique ID for member
            String email = request.getParameter("username");
            String password = request.getParameter("password");

            Member member = memberDB.getMember(email);

            if (member == null) {
                url = "/jsp/login.jsp";
                request.setAttribute("findUserId", "false");
            } else {
                request.setAttribute("userId", email);
                if (member.getPassword().equals(password)) {
                    session.setAttribute("member", member);
                    url = "/jsp/activity.jsp";
                } else {
                    url = "/jsp/login.jsp";
                    request.setAttribute("matchPassword", "false");
                }
            }

        } else if ("login".equalsIgnoreCase(action)) {
            url = "/jsp/login.jsp";

        } else if ("logout".equalsIgnoreCase(action)) {
            // Terminate the session
            session.removeAttribute("member");
            url = "/jsp/index.jsp";

        } else if ("cancel".equalsIgnoreCase(action)) {
            url = "/jsp/activity.jsp";

        } else if ("register".equalsIgnoreCase(action)) {
            // get parameters from the request
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password1");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");

            Member newMbr = new Member();
            newMbr.setFirstName(fName);
            newMbr.setLastName(lName);
            newMbr.setEmail(email);
            newMbr.setPassword(password);
            newMbr.setGender(gender);
            newMbr.setAge(Integer.parseInt(age));

            // store the registration object in the session
            session.setAttribute("member", newMbr);
            memberDB.addMember(newMbr);
            url = "/jsp/regConfirm.jsp";

        } else if ("contact_submit".equalsIgnoreCase(action)) {
            // get parameters from the request
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            if (email == null || email == "") {
                email = "NA";
            }
            String phone = request.getParameter("phone");
            String feedback = request.getParameter("feedback");

            // send a feedback email to the customer service	
            String to = "jhu.fabfitfun@gmail.com";
            String from = email;
            String subject = "Fab Fit Fun Customer Feedback";

            // create email body
            String body = "Customer feedback from " + fName + " " + lName + "\n";
            body += "Email: " + email + "\n";
            body += "Phone: " + phone + "\n\n";
            body += "Feedback message:\n";
            body += feedback;
            boolean isBodyHTML = false;

            try {
                MailUtil.sendMail(to, from, subject, body, isBodyHTML);
            } catch (MessagingException e) {
                System.out.println("Error sending out an email.");
            }

            session.setAttribute("feedbackFirstName", fName);

            url = "/jsp/confirmContact.jsp";

        } else if ("addToCart".equalsIgnoreCase(action)) {
            String actId = request.getParameter("addedActivity");
            mbr.addToCart(actId);
            memberDB.updateMember(mbr);
            session.setAttribute("member", mbr);
            url = "/jsp/activity.jsp";

        } else if ("removeFromCart".equalsIgnoreCase(action)) {
            String actId = request.getParameter("removedActivity");
            mbr.removeFromCart(actId);
            memberDB.updateMember(mbr);
            session.setAttribute("member", mbr);
            url = "/jsp/cart.jsp";

        } else if ("goToCart".equalsIgnoreCase(action)) {
            url = "/jsp/cart.jsp";

        } else if ("checkout".equalsIgnoreCase(action)) {
            url = "/jsp/checkout.jsp";

        } else if ("confirmPayment".equalsIgnoreCase(action)) {
            // get member parameters
            String email = mbr.getEmail();
            String subject = "Fab Fit Fun Confirmation";
            String fName = mbr.getFirstName();
            ArrayList<String> cart = mbr.getCart();
            int totalCost = 0;

            // create email body
            String body = "Dear " + fName + ",\n";
            body += "You have registerd the following course(s):\n";
            for (String actId : cart) {
                Activity activity = activityDB.getActivity(actId);
                totalCost += activity.getFeeInt();

                body += activity.getName() + "\t" + activity.getDate() + "\t" + activity.getCategory() + "\t" + activity.getFee() + "\n";
            }
            body += "\nThe total cost of $" + totalCost + " has been charged to your credit card.\n\n";
            body += "Thank you!\n\nFab Fit Fun Team";
            boolean isBodyHTML = false;

            // send a confirmation email to the member
            try {
                MailUtil.sendMail(email, "noreply", subject, body, isBodyHTML);
            } catch (MessagingException e) {
                System.out.println("Error sending out an email.");
            }

            // Change the number of openings available for each added activity
            for (String cartItem : mbr.getCart()) {
                activityDB.decrementOpenings(cartItem);
            }

            mbr.checkoutCart();
            memberDB.updateMember(mbr);
            session.setAttribute("member", mbr);
            url = "/jsp/confirmPayment.jsp";

        } else if ("updateProfile".equalsIgnoreCase(action)) {
            // get parameters from the request
            String oldEmail = request.getParameter("oldEmail");
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password1");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");

            Member newMbr = new Member();
            newMbr.setFirstName(fName);
            newMbr.setLastName(lName);
            newMbr.setEmail(email);
            newMbr.setPassword(password);
            newMbr.setGender(gender);
            newMbr.setAge(Integer.parseInt(age));
            newMbr.setActivities(mbr.getActivities());
            newMbr.setCart(mbr.getCart());

            // store the registration object in the session
            session.setAttribute("member", newMbr);
            memberDB.updateMember(newMbr, oldEmail);
            url = "/jsp/updateConfirm.jsp";

        } else {
            // No action was matched.  Return to start page.
            url = "/jsp/index.jsp";
        }
        // forward request and response objects to JSP page
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
}
