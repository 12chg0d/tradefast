package servlet;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;


import javax.servlet.*;
import javax.servlet.http.*;

import bean.EditItemBean;
import bean.ItemBean;

public class ShowItemControllerForEditing extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			EditItemBean eib = new EditItemBean();
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
				strQuery="select iID, Iname, remaining_numbers, minimum_numbers  from item";
				//JDBC methods!
				ArrayList<String[]> arr = new ArrayList<String[]>();
				String[] str = new String[4];
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				while(rs.next()) {
					str[0] = rs.getString(1);
					str[1] = rs.getString(2);
					str[2] = rs.getString(3);
					str[3] = rs.getString(4);
					arr.add(str);
					str = new String[4];
				}
				eib.setArr(arr);
				session.setAttribute("eib", eib);
				response.sendRedirect("EditItem");
			}catch (Exception e) {
				e.printStackTrace();
			}
		}					
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}