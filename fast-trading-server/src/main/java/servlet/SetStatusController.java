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
public class SetStatusController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			//Connection
			Connection conn=null;	
			PrintWriter out = response.getWriter();
			//Getting the parameters from registration page.
			//For validations, I use jQuery for this purposes. 
			String[] setStatus = (String[])request.getParameterValues("setStatus");
			try {
				//Setting the connection in database by using prepared statement.
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager
				.getConnection("jdbc:mysql://localhost/user_register?"
						+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				//Creating the row which has the username and password by using sql symbol "insert".
				String sql = "update tableTest set status = 'yes' where ID = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				//Using preparedstatement by set the parameter related to "?" symbol.
				for(int i = 0; i < setStatus.length; i++) {
					pst.setString(1, setStatus[i]);
					pst.executeUpdate();
				}
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
					response.sendRedirect("TableController");
					
				}
				catch (SQLException ignored){
					out.println(ignored);
					
				}
			}
	}
}
