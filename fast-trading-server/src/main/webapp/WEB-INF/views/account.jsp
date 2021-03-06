<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="servlet.*" %>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Welcome to our world.</title>
    <!-- This search page will deal with “SearchBean” in order to collect the result from “Top10Results” and “Top10HybridResults” class
     (“Top10HybridResults”  is for searching by ID and Top10Results is for searching by other fields.) 
     It will check with “SearchBean” and “UserBean” in order to show the result to users. -->
    <link href="resources/css/uni-form.css" media="screen" rel="stylesheet"/>
    <link href="resources/css/default.uni-form.css" title="Default Style" media="screen" rel="stylesheet"/>
    <link href="resources/css/demo.css" media="screen" rel="stylesheet"/>
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
  			<li><a href="home">HOME</a></li>
  			<li><a href="catalog">CATALOG</a></li>
  			<li><a href="faq">FAQ</a></li>
  		</ul>
  	
  		
  		<div id="right_menu">
  			<a href="account"><img src="resources/img/account_icon.png" height = "50"/></a>
  			<a href="cart"><img src="resources/img/cart_icon.png" height = "50"/></a>
  		<%
  			UserBean ub = (UserBean)session.getAttribute("user");
  			if(ub != null) {
  			%>
  				<a href="LogoutController"><img src="resources/img/logout_icon.png" height = "50"/></a>
  			<% } 
			else if(ub == null) { %>
				<a href="login"><img src="resources/img/login_icon.png" height = "50"/></a>
  			<% } %></div>
  		
  	</div>
  	
	<!--end of header -->
 	<%
 		if(ub != null) {
	%>
		<form class="uniForm">
			<div class="header">
        		<h3>Welcome <b><%= ub.getUsername()%>!!!</b></h3>
        		<h3>First Name : <b><%= ub.getFname()%>!!!</b></h3>
        		<h3>Last Name : <b><%= ub.getLname()%>!!!</b></h3>
        		<h3>Address : <b><%= ub.getAddress()%>!!!</b></h3>
        		<h3>Mobile Phone : <b><%= ub.getTel_no()%>!!!</b></h3>
        		<h3>E-mail : <b><%= ub.getEmail()%>!!!</b></h3>
      		</div>
      				<%
	ShowOrderBean sob = (ShowOrderBean)session.getAttribute("sob");
	if(sob != null) {
		ArrayList<String[]> arr = sob.getArr();	
		int size = arr.size();
		String[] str = new String[8];
		if(arr != null && size > 0) {
				
	%>
	<div class="center">
	<table id="newspaper-a" summary="test-table">
	   <colgroup>
	    	<col class="oce-first" />
	    </colgroup>
	    <thead>
	    	<tr>
	        	<th scope="col" align= "center" >Your Order</th>
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
				<th scope="col">Order Date</th>
				<th scope="col">Payment Status</th>
				<th scope="col">Packing Status</th>
	        </tr>
	    </thead>
	<%
				for(int i = 0; i < size; i++) {
	%>
		<tbody>
	    	<tr>
	<%
					str = arr.get(i);
				for(int j = 0; j < 7; j++) {
					
	%>
	        	<td><%=str[j] %></td>
	<%				
					}
				}
	%>
	        </tr>
	    </tbody>
	    </table>
	    </div>
	<%
		}
	}			
     %> 		
      	</form>
	<%
		} else {
	%>
		<div id="errorMsg">
	        <h4>Please Log in to the system!</h4>
	     </div>
		
	<%
		}
	%>
    <div id="footer">
    </div>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./js/uni-form-validation.jquery.js" charset="utf-8"></script>
  </body>
</html>
