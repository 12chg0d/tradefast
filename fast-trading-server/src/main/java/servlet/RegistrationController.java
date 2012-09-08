package servlet;

import java.io.*;
import java.sql.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.UserBean;

/**
 * This servlet is used to deal with the registration request
 * and bring you to the welcome page.
 * It's all about JDBC(SQL) stuff.
 * Version 0.9.0 by Phoonlarp :)  
 */
public class RegistrationController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			//Connection
			Connection conn=null;	
			PrintWriter out = response.getWriter();
			//Getting the parameters from registration page.
			//For validations, I use jQuery for this purposes. 
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			try {
				//Setting the connection in database by using prepared statement.
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager
				.getConnection("jdbc:mysql://localhost/user_register?"
						+ "user=sqluser&password=sqluserpw");
				//Creating the row which has the username and password by using sql symbol "insert".
				String sql = "insert into userregister values (?,?,default, default, default, default)";
				PreparedStatement pst = conn.prepareStatement(sql);
				//Using preparedstatement by set the parameter related to "?" symbol.
				pst.setString(1, userName);
				pst.setString(2, password);
				pst.executeUpdate();
				pst.close();
			}
			catch(ClassNotFoundException e){
				//In case that it cannot find the database.
				out.println("Couldn't load database driver: " + e.getMessage());
			}
			catch(SQLException e){
				//In case that it has SQL exception.
				out.println("SQLException caught: " + e.getMessage());	
			}
			catch (Exception e){
				out.println(e);
			}
			finally {
				try {
					//Don't forget to close your connection.
					if (conn != null) conn.close();
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					UserBean ub = new UserBean();
					ub.setUsername(userName);
					session.setAttribute("user", ub);
					response.sendRedirect("welcome.jsp");
				}
				catch (SQLException ignored){
					out.println(ignored);
					
				}
			}
	}
}
