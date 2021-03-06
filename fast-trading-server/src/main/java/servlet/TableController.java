package servlet; 
import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import bean.TableBean;


//import javax.sql.*;
import java.sql.*;
import java.util.ArrayList;

public class TableController extends HttpServlet{ 

	
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException,IOException{
			//I use "session" in order to throws the object named user bean.
			HttpSession session = request.getSession(true);
			TableBean tb = new TableBean();	
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
				strQuery="select * from order_record where payment_status=\'wait\' order by date desc";
				//JDBC methods!
				st = conn.createStatement();
				rs = st.executeQuery(strQuery);
				ArrayList<String[]> arr = new ArrayList<String[]>();
				String[] str = new String[9];
				int count=0;
				//This while loop is used to set the parameters.
				while(rs.next()) {
					str[0] = rs.getString(1);
					str[1] = rs.getString(2);
					str[2] = rs.getString(3);
					str[3] = rs.getString(4);
					str[4] = rs.getString(5);
					str[5] = rs.getString(6);
					str[6] = rs.getString(9);
					str[7] = rs.getString(8);
					arr.add(str);
					str = new String[9];
					count++;
				}
				rs.close();
				tb.setArr(arr);
				if(count>0){
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
					session.setAttribute("table", tb);
					response.sendRedirect("manager_checkpaid");
				} else {
					String haveTable = "no";
					//In case you don't care about the URL and throwing object, you can use RequestDispatcher. It is better performance than using sendRedirect.
				   	session.setAttribute("haveTable", haveTable);
				   	//dispatcher.forward(request, response);
				   	response.sendRedirect("manager_checkpaid");
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