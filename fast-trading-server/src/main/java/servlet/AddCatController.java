package servlet;

import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

import bean.CatBean;
import bean.ItemBean;

/**
 * This servlet is used to deal with the registration request
 * and bring you to the welcome page.
 * It's all about JDBC(SQL) stuff.
 * Version 0.9.0 by Phoonlarp :)  
 */
public class AddCatController extends HttpServlet{
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
			String cID = "0000000000";
			String Catname = request.getParameter("Catname");
			String description = request.getParameter("description");
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
				strQuery="select Catname from category where Catname=\'"+Catname+"\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				//This while loop is used to set the parameters in user bean.
				while(rs.next()) {
					rs.getString(1);
					c++;
				}
				if(c == 0) {
					strQuery = "select cID from category order by cast(cID as signed) desc";
					st = conn.createStatement();
					rs = st.executeQuery(strQuery);
					//This while loop is used to set the parameters in user bean.
					while(rs.next()) {
						if(rs.getString(1) != null) cID = Integer.toString(Integer.parseInt(rs.getString(1)) + 1);
						break;
					}
					String sql = "insert into category values (?,?,?)";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, cID);
					pst.setString(2, Catname);
					pst.setString(3, description);
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
						CatBean catB = new CatBean();
						catB.setcID(cID);
						catB.setCatname(Catname);
						catB.setDescription(description);
						session.setAttribute("catB", catB);
						response.sendRedirect("catDesc");
					} else {
						String dupC = "no";
				    	session.setAttribute("dupC", dupC);
				    	response.sendRedirect("addCat");
					}
				}
				catch (SQLException ignored){
					out.println(ignored);
					
				}
			}
	}
}
