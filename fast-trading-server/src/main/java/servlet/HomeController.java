package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.HomeBean;


//import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;

public class HomeController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			HomeBean hb = new HomeBean();	
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
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
				strQuery="select iID, Iname, price_money_only, price_point_only, price_point_couple, price_money_couple, picture  from item order by iID desc limit 4";
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
					str[4] = rs.getString(5);
					str[5] = rs.getString(6);
					str[6] = rs.getString(7);
					arr.add(str);
					str = new String[7];
				}
				hb.setArrForNew(arr);			
				strQuery="select iID, Iname, price_money_only, price_point_only, price_point_couple, price_money_couple, picture  from item order by purchase_count desc limit 4";
				//JDBC methods!
				rs = st.executeQuery(strQuery);
				ArrayList<String[]> arr2 = new ArrayList<String[]>();
				String[] str2 = new String[7];
				//This while loop is used to set the parameters.
				while(rs.next()) {
					str2[0] = rs.getString(1);
					str2[1] = rs.getString(2);
					str2[2] = rs.getString(3);
					str2[3] = rs.getString(4);
					str2[4] = rs.getString(5);
					str2[5] = rs.getString(6);
					str2[6] = rs.getString(7);
					arr2.add(str2);
					str2 = new String[7];
				}
				rs.close();
				hb.setArrForPop(arr2);
				session.setAttribute("hb", hb);
				response.sendRedirect("home");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}