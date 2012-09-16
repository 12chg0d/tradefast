package servlet; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class LogoutController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			session.invalidate();
	    	response.sendRedirect("login");
	}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}