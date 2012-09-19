package servlet;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


import javax.servlet.*;
import javax.servlet.http.*;

import bean.CartBean;
import bean.CheckOrderBean;
import bean.CreateOrderBean;

public class CheckOrderController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			CheckOrderBean cob = new CheckOrderBean();
			CartBean cartB = (CartBean)session.getAttribute("cartB");
			CreateOrderBean cOrB = new CreateOrderBean();
			if(cartB == null) {
				String haveCart = "no";
				session.setAttribute("haveCart", haveCart);
				response.sendRedirect("cart");
			} else {
				ArrayList<String> err = new ArrayList<String>();
				ArrayList<String> forDelete = new ArrayList<String>();
				ArrayList<String[]> arr = cartB.getArr();
				int size = arr.size();
				String[] mem = new String[size];
				int[] memVal = new int[size];
				String[] memName = new String[size];
				String[] str = new String[9];
				int sum_point = 0;
				int sum_money = 0;
				int mul_money = 0;
				int mul_point = 0;
				int mul_money_couple = 0;
				int mul_point_couple = 0;
				int item_amount = 0;;
				int temp1 = 0;
				int temp2 = 0;
				int temp3 = 0;
				for(int i = 0; i < size; i++) {
					str = arr.get(i);
					int sum = Integer.parseInt(request.getParameter(str[0]+"0"));
					forDelete.add(request.getParameter(str[0]+"0"));
					mem[i] = str[0];
					memVal[i] = sum;
					item_amount += sum;
					mul_money = Integer.parseInt(str[4]);
					mul_point =Integer.parseInt(str[5]);
					mul_money_couple = Integer.parseInt(str[7]);
					mul_point_couple = Integer.parseInt(str[6]);
					temp1 = Integer.parseInt(request.getParameter(str[0]+"1"));
					temp2 = Integer.parseInt(request.getParameter(str[0]+"2"));
					temp3 = Integer.parseInt(request.getParameter(str[0]+"3"));
					sum_point += temp1*mul_money + temp3*mul_money_couple;
					sum_point += temp2*mul_point + temp3*mul_point_couple;
					memName[i] = str[3];
					if(sum != temp1+temp2 + temp3) err.add("Please fill the corresponding numbers in item named "+str[3]);
				}
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
					for(int i = 0; i < size; i++) {
						strQuery="select remaining_numbers from item where iID=\'"+mem[i]+"\'";
						int amount = 0;
						//JDBC methods!
						int c = 0;
						st = conn.createStatement();
						rs = st.executeQuery(strQuery);
						while(rs.next()) {
							amount = rs.getInt(1);
							c++;
						}
						if(c > 0) {
							if(amount < memVal[i]) err.add("The " + memName[i] + " is out, please fill the number of items again");
						}
					}
				cOrB.setItem_amount(item_amount);
				cOrB.setSum_money(sum_money);
				cOrB.setSum_point(sum_point);
				cOrB.setForDelete(forDelete);
				cob.setArr(err);
				if(err.size() > 0) {
					session.setAttribute("cob", cob);
					response.sendRedirect("ShowCartController");
				} else {
					session.setAttribute("cOrB", cOrB);
					response.sendRedirect("CreateOrderController");
				}
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