package servlet; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bean.UserBean;

//import javax.sql.*;
import java.sql.*;

public class LogoutController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession(true);
			session.invalidate();
	    	System.out.println("test............................................................");
			response.sendRedirect("login");
	}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}