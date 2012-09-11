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
			request.setCharacterEncoding("UTF-8");
			//Connection
			Connection conn=null;	
			PrintWriter out = response.getWriter();
			//Getting the parameters from registration page.
			//For validations, I use jQuery for this purposes. 
			String firstName = request.getParameter("firstName");
			System.out.print(firstName);
			String lastName = request.getParameter("lastName");
			//String sex = request.getParameter("sex");
			//String birthDate = request.getParameter("birthDate");
			//String age = request.getParameter("Age");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String telNo = request.getParameter("telNo");
			String userName = request.getParameter("userName");
			String password = request.getParameter("password");
			//เหลือ time stamp เรื่องราว การเพิ่มของ ID
			Statement st=null;
			//ResultSet
			ResultSet rs=null;
			String strQuery=null;
			int c = 0;
			try {
				//Setting the connection in database by using prepared statement.
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager
				.getConnection("jdbc:mysql://localhost/user_register?"
						+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				strQuery="select * from userregister where username=\'"+userName+"\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				//This while loop is used to set the parameters in user bean.
				while(rs.next()) {
					System.out.print(rs.getString(1));
					c++;
				}
				System.out.println(c);
				if(c == 0) {
					//Creating the row which has the username and password by using sql symbol "insert".
					String sql = "insert into userregister values (?,?,?,?,?,?,MD5(?))";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, firstName);
					pst.setString(2, lastName);
					pst.setString(3, address);
					pst.setString(4, email);
					pst.setString(5, telNo);
					pst.setString(6, userName);
					pst.setString(7, password);
					pst.executeUpdate();
					pst.close();
				}
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
					if(c == 0) {
						//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
						UserBean ub = new UserBean();
						ub.setAddress(address);
						ub.setFirstName(firstName);
						ub.setLastName(lastName);
						ub.setTelNo(telNo);
						ub.setEmail(email);
						ub.setUserName(userName);
						session.setAttribute("user", ub);
						response.sendRedirect("welcome");
					} else {
						String dup = "no";
				    	session.setAttribute("dup", dup);
				    	response.sendRedirect("register");
					}
				}
				catch (SQLException ignored){
					out.println(ignored);
					
				}
			}
	}
}
