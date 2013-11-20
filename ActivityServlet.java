
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import business.Member;

/**
 *
 * @author Tongbai Meng
 */
public class ActivityServlet extends HttpServlet {
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
		
		mem.addActivity("");
		
		String action = request.getParameter("action");
		
		String url;
		url = "/fabfitfun/index.jsp";
		if (action.equalsIgnoreCase("addToCart")) {
			url = "/fabfitfun/activity.jsp";
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
