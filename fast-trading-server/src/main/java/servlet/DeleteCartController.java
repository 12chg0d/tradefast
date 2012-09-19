package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.UserBean;

//import javax.sql.*;
import java.sql.*;

public class DeleteCartController extends HttpServlet{ 


	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			UserBean ub = (UserBean)session.getAttribute("user");
			if(ub == null) {
				String haveLogin = "no";
				session.setAttribute("haveLogin", haveLogin);
				response.sendRedirect("cart");
			}else{
			String mID = ub.getmID();
			String iID = (String)request.getParameter("iID");
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
				String sql ="delete from cart_item_mapping where mID=? and iID = ?";
				PreparedStatement pst = conn.prepareStatement(sql);
				//Using preparedstatement by set the parameter related to "?" symbol.
				pst.setString(1, mID);
				pst.setString(2, iID);
				pst.executeUpdate();
				pst.close();
				response.sendRedirect("ShowCartController");
				} catch (Exception e) {
				e.printStackTrace();
			}}
		}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}