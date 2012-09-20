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

public class EditItemController extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException{
			//I use "session" in order to throws the object named user bean.
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			String iID = (String)request.getParameter("iID");
			String r_n = (String)request.getParameter(iID+"0");
			String m_n = (String)request.getParameter(iID + "1");
			//String idx = (String)request.getParameter("idx");
			Connection conn = null;
			try {
				//Getting the connection from database.
				Class.forName("com.mysql.jdbc.Driver");
				/*conn = DriverManager
						.getConnection("jdbc:mysql://localhost/se?"
								+ "user=root");*/
				conn = DriverManager
						.getConnection("jdbc:mysql://localhost/user_register?"
								+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				String sql ="update item set remaining_numbers=? ,minimum_numbers=?  where iID=?";
				PreparedStatement pst = conn.prepareStatement(sql);
				//Using preparedstatement by set the parameter related to "?" symbol.
				pst.setInt(1, Integer.parseInt(r_n));
				pst.setInt(2, Integer.parseInt(m_n));
				pst.setString(3, iID);
				pst.executeUpdate();
				pst.close();
				response.sendRedirect("ShowItemControllerForEditing");
				} catch (Exception e) {
				e.printStackTrace();
			}
		}					
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}