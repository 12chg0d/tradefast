package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.ShowCatBean;

//import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;

public class ShowCatControllerForCat extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			ShowCatBean scb = new ShowCatBean();
			session.setAttribute("cb", null);
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			//Creating the parameters which will be used later.
			//Connection
			Connection conn = null;
			String strQuery= ""; 
			Statement st=null;
			//ResultSet
			ResultSet rs=null;
				
			try {
				//Getting the connection from database.
				Class.forName("com.mysql.jdbc.Driver");
				/*conn = DriverManager
						.getConnection("jdbc:mysql://localhost/se?"
								+ "user=root");*/
				conn = DriverManager
						.getConnection("jdbc:mysql://localhost/user_register?"
								+ "user=sqluser&password=sqluserpw&useUnicode=true&characterEncoding=UTF-8");
				//Getting the parameters from login page.
				//Actually, this condition is done by using JQuery.
				strQuery="select cID, Catname from category";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				ArrayList<String[]> arr = new ArrayList<String[]>();
				String[] str = new String[2];
				int count=0;
				//This while loop is used to set the parameters.
				while(rs.next()) {
					str[0] = rs.getString(1);
					str[1] = rs.getString(2);
					arr.add(str);
					str = new String[2];
					count++;
				}
				scb.setArr(arr);
				if(count>0){
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					session.setAttribute("scbForCat", scb);
					response.sendRedirect("CatalogController");
				} else {
					String haveCat = "no";
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					session.setAttribute("haveCatForCat", haveCat);
				   	//dispatcher.forward(request, response);
				   	response.sendRedirect("CatalogController");
				}
				} catch (Exception e) {
				e.printStackTrace();
			}
		}
	 public void doGet(HttpServletRequest request, HttpServletResponse response)
             throws IOException, ServletException{
		 doPost(request, response);
	 }
}