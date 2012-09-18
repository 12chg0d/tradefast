package servlet;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.CatalogBean;


public class CatalogController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			
			CatalogBean cb = new CatalogBean();
			//Connection
			Connection conn=null;	
			//For validations, I use jQuery for this purposes.
			String catID  = (String)request.getParameter("catID");
			Statement st=null;
			//ResultSet
			ResultSet rs=null;
			String strQuery=null;
			try {
				//Setting the connection in database by using prepared statement.
				Class.forName("org.gjt.mm.mysql.Driver");
				conn = DriverManager
						.getConnection("jdbc:mysql://localhost/user_register?"
								+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				if(catID.equalsIgnoreCase("all")) {
					strQuery="select iID, Iname, price_money_only, price_point_only, price_point_couple, price_money_couple, picture from item";
				} else {
					strQuery="select iID, Iname, price_money_only, price_point_only, price_point_couple, price_money_couple, picture from item where catID=\'"+catID+"\'";
					
				}
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				//This while loop is used to set the parameters in user bean.
				ArrayList<String[][]> arr = new ArrayList<String[][]>();
				int c = 0;
				String[][] str = new String[12][7];
				//This while loop is used to set the parameters.
				while(rs.next()) {
					str[c][0] = rs.getString(1);
					str[c][1] = rs.getString(2);
					str[c][2] = rs.getString(3);
					str[c][3] = rs.getString(4);
					str[c][4] = rs.getString(5);
					str[c][5] = rs.getString(6);
					str[c][6] = rs.getString(7);
					c++;
					if(c == 12) {
						arr.add(str);
						str = new String[12][7];
						c=0;
					} 
				}
				if(c < 12) {
					arr.add(str);
				}
				cb.setArr(arr);			
				session.setAttribute("cb", cb);
				response.sendRedirect("catalog");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}
