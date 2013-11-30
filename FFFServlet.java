
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.MessagingException;

import business.Member;
import util.MailUtil;

/**
 *
 * @author
 */
public class FFFServlet extends HttpServlet {
    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response)
                          throws ServletException, IOException 
    {
		HttpSession session = request.getSession();
		Member mem = (Member) session.getAttribute("member");
		if (mem == null) {		
			// create the Member object
			mem = new Member();
		}
		
		String url = "";
		String action = request.getParameter("action");
		if (action.equalsIgnoreCase("about")) {
			url = "/jsp/index.jsp";
		} else if (action.equalsIgnoreCase("member")) {
			url = "/jsp/newMember.jsp";
		} else if (action.equalsIgnoreCase("class")) {
			url = "/jsp/activity.jsp";
		} else if (action.equalsIgnoreCase("contact")) {
			url = "/jsp/contactus.jsp";
		} else if (action.equalsIgnoreCase("register")) {
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
			mem.setAge(Integer.parseInt(age));
			
			url = "/jsp/regConfirm.jsp";
		} else if (action.equalsIgnoreCase("contact_submit")) {
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
			} catch (MessagingException e){
				System.out.println("Error sending out an email.");
			}
				
			session.setAttribute("feedbackFirstName", fName);
		
			url = "/jsp/confirmContact.jsp";
		} else if (action.substring(0,3).equalsIgnoreCase("add")) {
			mem.addToCart(action.substring(9));
			url = "/jsp/activity.jsp";
		} else if (action.substring(0,6).equalsIgnoreCase("remove")) {
			mem.removeFromCart(action.substring(14));
			url = "/jsp/cart.jsp";
		
			// send confirmation email
			String to = mem.getEmail();
			String from = "noreply";
			String subject = "Confirmation -- Fab Fit Fun";
			
			// create email body
			String body = "You registered the following activity.\n";
			boolean isBodyHTML = false;
			
			try {
				MailUtil.sendMail(to, from, subject, body, isBodyHTML);
			} catch (MessagingException e){
				System.out.println("Error sending out an email.");
			}
			url = "/jsp/confirm.jsp";
		} else if (action.equalsIgnoreCase("goToCart")) {
			url = "/jsp/cart.jsp";
		}
		
		// forward request and response objects to JSP page
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
		dispatcher.forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, 
                          HttpServletResponse response)
                          throws ServletException, IOException 
    {
        doPost(request, response);
    }

}
