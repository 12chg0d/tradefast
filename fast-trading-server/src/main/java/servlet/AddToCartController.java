package servlet;

import java.io.*;
import java.sql.*;


import javax.servlet.*;
import javax.servlet.http.*;

import bean.UserBean;

public class AddToCartController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			String iID = (String)request.getParameter("iID");
			UserBean ub = (UserBean)session.getAttribute("user");
			if(ub == null) {
				String haveLogin = "no";
				session.setAttribute("haveLogin", haveLogin);
				response.sendRedirect("cart");
			}
			else {
			String mID = (String)ub.getmID();
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
				strQuery="select total_amount from cart_item_mapping where mID=\'"+mID+"\'and iID=\'"+iID+"\'";
				int c=0;
				int amount = 0;
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				while(rs.next()) {
					amount = rs.getInt(1);
					c++;
				}
				if(c > 0) {
					amount = amount + 1;
					String sql = "update cart_item_mapping set total_amount = ? where mID=? and iID=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setInt(1, amount);
					pst.setString(2, mID);
					pst.setString(3, iID);
					pst.executeUpdate();
					pst.close();			
					
				} else {
					strQuery = "select Iname, price_money_only, price_point_only, price_point_couple, price_money_couple, picture from item where iID=\'"+iID+"\'";
					rs = st.executeQuery(strQuery);
					String[] str = new String[6];
					while(rs.next()) {
						str[0] = rs.getString(1);
						str[1] = rs.getString(2);
						str[2] = rs.getString(3);
						str[3] = rs.getString(4);
						str[4] = rs.getString(5);
						str[5] = rs.getString(6);
					}
					String sql = "insert into cart_item_mapping values (?,?,?,?,?,?,?,?,?)";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, iID);
					pst.setInt(2, 1);
					pst.setString(3, mID);
					pst.setString(4, str[0]);
					pst.setInt(5, Integer.parseInt(str[1]));
					pst.setInt(6, Integer.parseInt(str[2]));
					pst.setInt(7, Integer.parseInt(str[3]));
					pst.setInt(8, Integer.parseInt(str[4]));
					pst.setString(9, str[5]);
					pst.executeUpdate();
					pst.close();
					rs.close();
				}
				response.sendRedirect("ShowCartController");
			}catch (Exception e) {
				e.printStackTrace();
			}
			}
		}					
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}