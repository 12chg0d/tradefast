package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.ItemBean;

/**
 * This servlet is used to deal with the registration request
 * and bring you to the welcome page.
 * It's all about JDBC(SQL) stuff.
 * Version 0.9.0 by Phoonlarp :)  
 */
public class AddItemController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named item bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			
			//Connection
			Connection conn=null;	
			PrintWriter out = response.getWriter();
			//Getting the parameters from registration page.
			//For validations, I use jQuery for this purposes.
			/*
			* 
			  `iID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
			  `Iname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
			  `description` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
			  `price_money_only` int(20) NOT NULL,
			  `price_point_only` int(10) NOT NULL,
			  `price_point_couple` int(10) NOT NULL,
			  `price_money_couple` int(20) NOT NULL,
			  `remaining_numbers` int(10) NOT NULL,
			  `minimum_numbers` int(10) NOT NULL,
			  `catID` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
			  `picture` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
			 */
			
			String Iname = request.getParameter("Iname");
			String description = request.getParameter("description");
			String price_money_only = request.getParameter("price_money_only");
			String price_point_only = request.getParameter("price_point_only");
			String price_point_couple = request.getParameter("price_point_couple");
			String price_money_couple = request.getParameter("price_money_couple");
			String remaining_number = request.getParameter("remaining_numbers");
			String minimum_numbers = request.getParameter("minimum_numbers");
			String catID = request.getParameter("catID");
			String cat=null;
			String picture = request.getParameter("link");
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
				strQuery="select Iname from item where Iname=\'"+Iname+"\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				//This while loop is used to set the parameters in user bean.
				while(rs.next()) {
					rs.getString(1);
					c++;
				}
				if(c == 0) {
					String iID = "0000000000";
					strQuery = "select iID from item order by cast(iID as signed)";
					st = conn.createStatement();
					rs = st.executeQuery(strQuery);
					//This while loop is used to set the parameters in user bean.
					while(rs.next()) {
						if(rs.getString(1) != null) iID = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
						break;
					}
					strQuery = "select Catname from category where cID = \'"+catID+"\'";
					rs = st.executeQuery(strQuery);
					while(rs.next()) {
						if(rs.getString(1) != null) cat =rs.getString(1);
					}
					String sql = "insert into item values (?,?,?,?,?,?,?,?,?,?,?, ?)";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					
					pst.setString(1, iID);
					pst.setString(2, Iname);
					pst.setString(3, description);
					pst.setInt(4,Integer.parseInt(price_money_only));
					pst.setInt(5,Integer.parseInt(price_point_only));
					pst.setInt(6,Integer.parseInt(price_point_couple));
					pst.setInt(7,Integer.parseInt(price_money_couple));
					pst.setInt(8,Integer.parseInt(remaining_number));
					pst.setInt(9,Integer.parseInt(minimum_numbers));
					pst.setString(10, cat);
					pst.setString(11, picture);
					pst.setInt(12,0);
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
					rs.close();
					if (conn != null) conn.close();
					if(c == 0) {
						//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
						ItemBean ib = new ItemBean();
						ib.setCat(cat);
						ib.setDescription(description);
						ib.setIname(Iname);
						ib.setPicture(picture);
						ib.setPrice_money_couple(price_money_couple);
						ib.setPrice_money_only(price_money_only);
						ib.setPrice_point_couple(price_point_couple);
						ib.setPrice_point_only(price_point_only);
						session.setAttribute("ib", ib);
						response.sendRedirect("itemDesc");
					} else {
						String dupI = "no";
				    	session.setAttribute("dupI", dupI);
				    	response.sendRedirect("addItem");
					}
				}
				catch (SQLException ignored){
					out.println(ignored);
					
				}
			}
	}
}
