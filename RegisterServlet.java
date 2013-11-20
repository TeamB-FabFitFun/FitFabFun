
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.Member;

/**
 *
 * @author Tongbai Meng
 */
public class RegisterServlet extends HttpServlet {
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
		
		String url = "/fabfitfun/index.jsp";
		
//		String action = request.getParameter("action");
//		if (action.equalsIgnoreCase("register"))
			url = "/fabfitfun/regConfirm.jsp";
		
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
