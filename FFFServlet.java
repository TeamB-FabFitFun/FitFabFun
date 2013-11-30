
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.MessagingException;

import business.Member;
import util.MailUtil;

public class FFFServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Member mem = (Member) session.getAttribute("member");
        if (mem == null) {
            // create the Member object
            mem = new Member();
        }

        String url = "";
        String action = request.getParameter("action");
        if ("about".equalsIgnoreCase(action)) {
            url = "/jsp/index.jsp";
        } else if ("member".equalsIgnoreCase(action)) {
            url = "/jsp/newMember.jsp";
        } else if ("class".equalsIgnoreCase(action)) {
            url = "/jsp/activity.jsp";
        } else if ("contact".equalsIgnoreCase(action)) {
            url = "/jsp/contactus.jsp";

        } else if ("register".equalsIgnoreCase(action)) {
            // get parameters from the request
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String password = request.getParameter("password1");
            String gender = request.getParameter("gender");
            String age = request.getParameter("age");

            mem.setFirstName(fName);
            mem.setLastName(lName);
            mem.setEmail(email);
            mem.setPassword(password);
            mem.setGender(gender);
            mem.setAge(Integer.parseInt(age)); // Needs "" check

            url = "/jsp/regConfirm.jsp";
        } else if ("contact_submit".equalsIgnoreCase(action)) {
            // get parameters from the request
            String fName = request.getParameter("firstName");
            String lName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String feedback = request.getParameter("feedback");

            // send a feedback email to the customer service	
            //		String to = "fatfitfun_support@gmail.com";
            String to = "tongbai.meng@gmail.com";
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
            
        } else if ("add".equalsIgnoreCase(action.substring(0, 3))) {
            mem.addToCart(action.substring(9));
            url = "/jsp/activity.jsp";
            
        } else if ("remove".equalsIgnoreCase(action.substring(0, 6))) {
            mem.removeFromCart(action.substring(14));
            url = "/jsp/cart.jsp";

        } else if ("goToCart".equalsIgnoreCase(action)) {
            url = "/jsp/cart.jsp";
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
