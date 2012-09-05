<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="bean.*" %>
<%@ page import="servlet.*"%>  
<!DOCTYPE html>
<html>
<head>
<title>Database Search</title>
<!-- This page is used to deal with “UserBean” and forward to “welcome.jsp” page. -->
</head>
<body>
	<%
		//Receive the data from the last page and the send it by using LoginBean. 
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
	%>
	<jsp:useBean id="db" scope="request" class="bean.LoginBean" >
  		<jsp:setProperty name="db" property="userName" value="<%=userName %>"/>
  		<jsp:setProperty name="db" property="password" value="<%=password %>"/>
 	</jsp:useBean>
	<jsp:forward page="LoginController">
  		<jsp:param name="username" value="<%=db.getUserName()%>" />
  		<jsp:param name="password" value="<%=db.getPassword()%>" />
	</jsp:forward> 
</body>
</html>