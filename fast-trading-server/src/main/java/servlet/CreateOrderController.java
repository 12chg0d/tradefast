package servlet;

import java.io.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import javax.servlet.*;
import javax.servlet.http.*;

import bean.CartBean;
import bean.CreateOrderBean;
import bean.UserBean;

public class CreateOrderController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			CartBean cartB = (CartBean)session.getAttribute("cartB");
			CreateOrderBean cOrB = (CreateOrderBean)session.getAttribute("cOrB");
			UserBean ub = (UserBean)session.getAttribute("user");
			if(cartB == null||cOrB == null) {
				String haveCart = "no";
				session.setAttribute("haveCart", haveCart);
				response.sendRedirect("cart");
			}else if(ub == null) {
				String haveLogin = "no";
				session.setAttribute("haveLogin", haveLogin);
				response.sendRedirect("cart");
			} else {
			ArrayList<String[]> arr = cartB.getArr();
			int size = arr.size();
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
				//Delete
				strQuery="delete from cart_item_mapping where mID=\'"+mID+"\'";
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				rs.close();
				int sum_point = cOrB.getSum_point();
				int sum_money = cOrB.getSum_money();
				int item_amount = cOrB.getItem_amount();
				strQuery = "select max(oID) from order_record";
				ResultSet rs2;
				String oID = "0000000000";
				String address = ub.getAddress();
				rs2 = st.executeQuery(strQuery);
				while(rs2.next()) {
					if(rs2.getString(1) != null) oID = Integer.toString(Integer.parseInt(rs2.getString(1)) + 1);
				}
				rs2.close();
				ResultSet rs3;
				String str = null;
				int amount = 0;
				ArrayList<String> forDelete = cOrB.getForDelete();
				String forDe = null;
				for(int i = 0; i < size; i++) {
					str = arr.get(i)[0];
					strQuery = "select remaining_numbers from item where iID=\'"+str +"\'";
					rs3 = st.executeQuery(strQuery);
					while(rs3.next()) {
						amount = rs3.getInt(1);
					}
					forDe = Integer.toString(amount - Integer.parseInt(forDelete.get(i)));
					String sql = "update item set remaining_numbers = ? where iID=?";
					PreparedStatement pst = conn.prepareStatement(sql);
					//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, forDe);
					pst.setString(2, str);
					pst.close();	
				}
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
				Date date = new Date();
				String order_date = dateFormat.format(date);		
				String sql = "insert into order_record values (?,?,?,?,?,'wait','wait',?,?)";
				PreparedStatement pst = conn.prepareStatement(sql);
				
						//Using preparedstatement by set the parameter related to "?" symbol.
					pst.setString(1, oID);
					pst.setInt(2, sum_money);
					pst.setInt(3, sum_point);
					pst.setInt(4, item_amount );
					pst.setString(5, address);
					pst.setString(6, order_date);
					pst.setString(7, mID);
					pst.executeUpdate();
					pst.close();
					rs.close();
				
					
				
				response.sendRedirect("HomeController");
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