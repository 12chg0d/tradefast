
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %><!-- Test -->
<!doctype html>
<html lang="th" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Manager</title>
	<link href="resources/css/uni-form.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/default.uni-form.css" title="Default Style" media="screen" rel="stylesheet"/>
	<link href="resources/css/demo.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/style.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/header_style.css" media="screen" rel="stylesheet"/>

    
    <!--[if lte ie 7]>
      <style type="text/css" media="screen">
        /* Move these to your IE6/7 specific stylesheet if possible */
        .uniForm, .uniForm fieldset, .uniForm .ctrlHolder, .uniForm .formHint, .uniForm .buttonHolder, .uniForm .ctrlHolder ul{ zoom:1; }
      </style>
    <![endif]-->

  </head>

  <body>
  	<!-- header -->
  	<div id="header_wrapper">
  		<img id="logo" src="resources/img/logo_icon.png"/>
  		<ul>
  			<li><a href="manager_additem">Add/Delete Item</a></li>
  			<li><a href="manager_addcate">Add/Delete Category</a></li>
  			<li><a href="TableController">Change Status</a></li>
  		</ul>
  	
  		
  		<div id="right_menu">
  			
  			<%
  			UserBean ub = (UserBean)session.getAttribute("user");
  			
  			TableBean tb = (TableBean)session.getAttribute("table");
  			if(ub != null) {
  			%>
  				<a href="LogoutController"><img src="resources/img/logout_icon.png" height = "50"/></a>
  			<% } 
			else if(ub == null) { %>
				<a href="login"><img src="resources/img/login_icon.png" height = "50"/></a>
  			<% } %>	 		
  		</div>
  		
  	</div>
  	
	<!--end of header -->
	
	
	<!-- It will receive the username and password here and then send it to loginbean -->    
<form action="SetStatusController" class="uniForm" method="post">
<%
if(tb != null) {
	ArrayList<String[]> arr = tb.getArr();	
	int size = arr.size();
	String[] str = new String[9];
	if(arr != null && size > 0) {
			
%>
<div class="center">
<table id="newspaper-a" summary="test-table">
   <colgroup>
    	<col class="oce-first" />
    </colgroup>
    <thead>
    	<tr>
        	<th scope="col" align= "center" >Show Order List</th>
        </tr>
    </thead>
    </table>
    
    <table id="newspaper-a" summary="test-table">
   <colgroup>
    	<col class="oce-first" />
    </colgroup>
    <thead>
    	<tr>
			<th scope="col">Order ID</th>        	
			<th scope="col">Sum Money</th>        	
			<th scope="col">Sum Point</th>        	
			<th scope="col">Item Amount</th>
			<th scope="col">Date</th>        	
			<th scope="col">Member ID</th>  
			<th scope="col">Address</th>        	       	
			<th scope="col">Packing Status</th>  
			<th scope="col">Set Payment Status</th>               				
        </tr>
    </thead>
<%
			for(int i = 0; i < size; i++) {
%>
	<tbody>
    	<tr>
<%
				str = arr.get(i);
%>
        	<td><%=str[0] %></td>
        	<td><%=str[1] %></td>
        	<td><%=str[2] %></td>
        	<td><%=str[3] %></td>
 			<td><%=str[5] %></td>
        	<td><%=str[6] %></td>
        	<td><%=str[4] %></td>
        	<td><%=str[7] %></td>
        	<!-- <td><a href="login"><%=str[1] %></a></td> -->
        	<td><input type="checkbox" name="setStatus" value=<%=str[0] %> />
<%				
				}
%>
        </tr>
    </tbody>
    </table>
    </div>
<%
			}//have just fixed
	}
	session.setAttribute("table", null);
%>
	<div class="buttonHolder">
		<button type="submit" class="primaryAction">OK</button>
      </div>	
    </form>

    <div id="footer">
    </div>
	<!-- JavaScript and JQuery part -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/uni-form-validation.jquery.js" charset="utf-8"></script>
    <script>
      $(function(){
        $('form.uniForm').uniform({
          prevent_submit : true
        });
      });
    </script>
  </body>
</html>



