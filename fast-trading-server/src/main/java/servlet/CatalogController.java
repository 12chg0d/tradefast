package servlet;

import java.io.*;
import java.sql.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.UserBean;


public class CatalogController extends HttpServlet{
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
			String Fname = request.getParameter("Fname");
			String Lname = request.getParameter("Lname");
			System.out.println(Lname);
			String sex = request.getParameter("sex");
			String birthdate = request.getParameter("birthdate");
			String address = request.getParameter("address");
			String email = request.getParameter("email");
			String tel_no = request.getParameter("tel_no");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
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
				strQuery="select * from member where username=\'"+username+"\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				//This while loop is used to set the parameters in user bean.
				while(rs.next()) {
					rs.getString(1);
					c++;
				}
				if(c == 0) {
					strQuery = "select max(mID) from member";
					st = conn.createStatement();
					rs = st.executeQuery(strQuery);
					//This while loop is used to set the parameters in user bean.
					String mID = "0000000000";
					while(rs.next()) {
						if(rs.getString(1) != null) mID = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
					}
					DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					Date date = new Date();
					String registration_date = dateFormat.format(date);		
					String sql = "insert into member values (?,?,?,?,MD5(?),?,?,?,?,?,?,'active')";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, mID);
					pst.setString(2, Fname);
					pst.setString(3, Lname);
					pst.setString(4, username);
					pst.setString(5, password);
					pst.setString(6, sex);
					pst.setString(7, birthdate);
					pst.setString(8, address);
					pst.setString(9, tel_no);
					pst.setString(10, email);
					pst.setString(11, registration_date);
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
						ub.setEmail(email);
						ub.setFname(Fname);
						ub.setLname(Lname);
						ub.setTel_no(tel_no);
						ub.setUsername(username);
						session.setAttribute("user", ub);
						response.sendRedirect("home");
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
