package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.ShowOrderBean;
import bean.UserBean;


import java.sql.*;
import java.util.ArrayList;

public class ShowOrderController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			UserBean ub = (UserBean)session.getAttribute("user");
			ShowOrderBean sob = new ShowOrderBean();
			if(ub == null) {
				String haveLoginForAccount = "no";
				session.setAttribute("haveLoginForAccount", haveLoginForAccount);
				response.sendRedirect("account");
				
			}
			else {
				String mID = ub.getmID();
				//Creating the parameters which will be used later.
				//Connection
				Connection conn = null;
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
					strQuery="select * from order_record where mID=\'"+mID+"\' order by oID desc limit 10";
					//JDBC methods!
					st = conn.createStatement();
					rs = st.executeQuery(strQuery);
					ArrayList<String[]> arr = new ArrayList<String[]>();
					String[] str = new String[7];
					//This while loop is used to set the parameters.
					while(rs.next()) {
						str[0] = rs.getString(1);
						str[1] = rs.getString(2);
						str[2] = rs.getString(3);
						str[3] = rs.getString(4);
						str[4] = rs.getString(6);
						str[5] = rs.getString(7);
						str[6] = rs.getString(8);
						arr.add(str);
						str = new String[7];
					}
					rs.close();
					sob.setArr(arr);
					session.setAttribute("sob", sob);
					response.sendRedirect("account");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}