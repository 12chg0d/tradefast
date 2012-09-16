package servlet; 
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


import bean.UserBean;

//import javax.sql.*;
import java.sql.*;
/**
 * This servlet is used to deal with the authentication request
 * and bring you to the next page according to your input.
 * It's all about JDBC(SQL) stuff.
 * Version 0.9.0 by Phoonlarp :)  
 */
public class LoginController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			UserBean ub = new UserBean();	
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			//Creating the parameters which will be used later.
			//Connection
			Connection conn = null;
			String username="";
			String userpass="";
			String strQuery= ""; 
			Statement st=null;
			//ResultSet
			ResultSet rs=null;
				
			try {
				//Getting the connection from database.
				Class.forName("com.mysql.jdbc.Driver");
				/*conn = DriverManager
						.getConnection("jdbc:mysql://localhost/se?"
								+ "user=root");*/
				conn = DriverManager
						.getConnection("jdbc:mysql://localhost/user_register?"
								+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				//Getting the parameters from login page.
				//Actually, this condition is done by using JQuery.
				if(request.getParameter("username")!=null &&
					request.getParameter("username")!="" && request.getParameter("password")!=null &&
					request.getParameter("password")!="")
				{	//Checking username and password from database.
					username = request.getParameter("username").toString();
					userpass = request.getParameter("password").toString();
					//Getting the exact username and password from database by using sql symbol "select".
					strQuery="select * from member where username=\'"+username+"\'and  password=MD5(\'"+userpass+"\')";
					//JDBC methods!
					st = conn.createStatement();
					rs = st.executeQuery(strQuery);
					int count=0;
					//This while loop is used to set the parameters in user bean.
					while(rs.next()) {
						if(rs.getString(2) != null) ub.setFname(rs.getString(2));
						if(rs.getString(3) != null) ub.setLname(rs.getString(3));
						if(rs.getString(4) != null) ub.setUsername(rs.getString(4));
						if(rs.getString(8) != null) ub.setAddress(rs.getString(8));
						if(rs.getString(9) != null) ub.setTel_no(rs.getString(9));
						if(rs.getString(10) != null) ub.setEmail(rs.getString(10));
						count++;
					}
					//If you type his username and password correctly, it will bring you to welcome page.
					if(count>0){
						//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
						session.setAttribute("user", ub);
						response.sendRedirect("welcome");
					} else {
						//Setting the parameter named "isHas" in order to show the error message at login page.
						String isHas = "no";
						//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
						//RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
				    	session.setAttribute("isHas", isHas);
				    	//dispatcher.forward(request, response);
				    	response.sendRedirect("login");
					}
				} else {
					//Setting the parameter named "isHas" in order to show the error message at login page.
					String isHas = "no";
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					//RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			    	session.setAttribute("isHas", isHas);
			    	//dispatcher.forward(request, response);
			    	response.sendRedirect("login");
				}
				//System.out.println("Connected to the database"); //For checking
				//Don't forget to the connection.
				conn.close();
				//System.out.println("Disconnected from database"); //For checking
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
}