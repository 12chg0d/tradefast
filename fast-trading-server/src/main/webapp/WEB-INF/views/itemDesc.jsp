<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="servlet.*" %>
<%@ page import="bean.*" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Item Page.</title>
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
  			<li><a href="HomeController">HOME</a></li>
  			<li><a href="ShowCatControllerForCat">CATALOG</a></li>
  			<li><a href="faq">FAQ</a></li>
  		</ul>
  		
  		<div id="right_menu">
  			<a href="ShowOrderController"><img src="resources/img/account_icon.png" height = "50"/></a>
  			<a href="ShowCartController"><img src="resources/img/cart_icon.png" height = "50"/></a>
  			<%
  			UserBean ub = (UserBean)session.getAttribute("user");
  			if(ub != null) {
  			%>
  				<a href="LogoutController"><img src="resources/img/logout_icon.png" height = "50"/></a>
  			<% } 
			else if(ub == null) { %>
				<a href="login"><img src="resources/img/login_icon.png" height = "50"/></a>
  			<% } %>	 		
  		</div>
  		
  		
  		<div id="search_box">
		  		<!-- It will receive the username and password here and then send it to loginbean -->
		    <form class="search_form" action="SearchController" method="post">    
		          <label for="">Search</label>
		          <input name="itemName" id="itemName" data-default-value="Item Name" size="20" maxlength="100" type="text" class="textInput required"/>
				  <input type="image" src="resources/img/search_icon.png" class="primaryAction" width="20"></input>
		    </form>
		</div>
  		
 	
  	</div>
  	<!--end of header -->
  	<%
 		ItemBean ib = (ItemBean)session.getAttribute("ib");
		if(ib != null) {
	%>
		<form class="uniForm">
			<div class="header">
				<img alt="Item pic" src=<%=ib.getPicture()%>>
				<%System.out.println(ib.getPicture()); %>
        		<h3>Item Name :<b><%= ib.getIname()%></b></h3>
        		<h3>Cart Name :<b><%= ib.getCat()%></b></h3>
        		<h3>Description : <b><%= ib.getDescription()%></b></h3>
        		<h3>Price Money Only : <b><%= ib.getPrice_money_only()%></b></h3>
        		<h3>Price Point Only : <b><%= ib.getPrice_point_only()%></b></h3>
        		<h3>Price Money Couple : <b><%= ib.getPrice_money_couple()%></b></h3>
        		<h3>Price Point Couple : <b><%= ib.getPrice_point_couple()%></b></h3>
        		<%String temp = "AddToCartController?iID="+ib.getiID(); %>
      			<h3><a href=<%=temp %>>Add to Cart</a></h3>
      		</div>
		</form>
	<%
		}
		session.setAttribute("ib", null);
	%>
	
    <div id="footer">
    </div>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./js/uni-form-validation.jquery.js" charset="utf-8"></script>
  </body>
</html>
