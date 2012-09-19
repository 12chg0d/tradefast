package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;


import bean.SearchBean;

//import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;
public class SearchController extends HttpServlet{ 
	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			SearchBean sb = new SearchBean();	
			response.setContentType("text/html");
			request.setCharacterEncoding("UTF-8");
			//Creating the parameters which will be used later.
			//Connection
			Connection conn = null;
			String strQuery= ""; 
			Statement st=null;
			//ResultSet
			ResultSet rs=null;
			String search = request.getParameter("itemName");	
			//String search = "chill";
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
				strQuery="select iID, picture, Iname, description  from item where Iname like \'%" + search + "%\'";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				ArrayList<String[]> arr = new ArrayList<String[]>();
				String[] str = new String[4];
				int count=0;
				//This while loop is used to set the parameters.
				while(rs.next()) {
					str[0] = rs.getString(1);
					str[1] = rs.getString(2);
					str[2] = rs.getString(3);
					str[3] = rs.getString(4);
					arr.add(str);
					str = new String[4];
					count++;
				}
				sb.setArr(arr);
				rs.close();
				if(count>0){
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					session.setAttribute("searchResult", sb);
					response.sendRedirect("search");
				} else {
					String haveTableS = "no";
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					session.setAttribute("haveTableS", haveTableS);
				   	//dispatcher.forward(request, response);
				   	response.sendRedirect("search");
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