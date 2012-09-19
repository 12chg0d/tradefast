package servlet;

import java.io.*;
import java.sql.*;


import javax.servlet.*;
import javax.servlet.http.*;

import bean.ItemBean;

public class ShowItemController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			String iID = (String)request.getParameter("iID");
			ItemBean ib = new ItemBean();
			Connection conn = null;
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
				strQuery="select iID,Iname,description,price_money_only,price_point_only,price_point_couple,price_money_couple,catID,picture from item where iID=\'"+iID+"\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				while(rs.next()) {
					ib.setiID(rs.getString(1));
					ib.setIname(rs.getString(2));
					ib.setDescription(rs.getString(3));
					ib.setPrice_money_only(rs.getString(4));
					ib.setPrice_point_only(rs.getString(5));
					ib.setPrice_point_couple(rs.getString(6));
					ib.setPrice_money_only(rs.getString(7));
					ib.setCat(rs.getString(7));
					ib.setPicture(rs.getString(8));
				}
				session.setAttribute("ib", ib);
				response.sendRedirect("itemDesc");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}					
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}