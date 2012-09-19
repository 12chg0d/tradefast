<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Add Categories Page</title>
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
  			<li><a href="welcome">HOME</a></li>
  			<li><a href="TableController">CATALOG</a></li>
  			<li><a href="search">FAQ</a></li>
  			<li><a href="ShowCatController"></a>Add Item</li>
  		</ul>
  	
  		
  		<div id="right_menu">
  			<a href="account"><img src="resources/img/account_icon.png" height = "50"/></a>
  			<a href="cart"><img src="resources/img/cart_icon.png" height = "50"/></a>
  			<%
  			UserBean ub = (UserBean)session.getAttribute("user");
  			if(ub != null) {
  			%>
  				<a href="LogoutController"><img src="resources/img/login_icon02.png" height = "50"/></a>
  			<% } 
			else if(ub == null) { %>
				<a href="login"><img src="resources/img/login_icon.png" height = "50"/></a>
  			<% } %>	</div>
  		
  	</div>
  	
	<!--end of header -->
	<!-- This part will receive the data from user and then send it to RegisterCotroller. -->
    <form action="AddCatController" class="uniForm" method="post">
      <% String dupC = (String)session.getAttribute("dupC");	
      	if(dupC != null && dupC.equalsIgnoreCase("no")){	
      	%>
	        <div id="errorMsg">
	        <h4>This category is exist!</h4>
	      	</div>
	      	
	      <% 
      	  		session.setAttribute("dupC", null);  	
      		} 
      		
      			
	      %>  
	     <!--  String Iname = request.getParameter("Iname");
			String description = request.getParameter("description");
			int price_money_only = Integer.parseInt(request.getParameter("price_money_only"));
			int price_point_only = Integer.parseInt(request.getParameter("price_point_only"));
			int price_point_couple = Integer.parseInt(request.getParameter("price_point_couple"));
			int price_money_couple = Integer.parseInt(request.getParameter("price_money_couple"));
			int remaining_number = Integer.parseInt(request.getParameter("remaining_number"));
			int minimum_numbers = Integer.parseInt(request.getParameter("minimum_numbers"));;
			String catID = request.getParameter("catID");
			String cat=null;
			String picture = request.getParameter("picture");
		-->    
      <fieldset>
        <h3>Hi, Sir.</h3>
        <div class="ctrlHolder">
	    	<label for=""><em>*</em> Category Name</label>
	         <input name="Catname" id="Catname" data-default-value="Category Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Description</label>
	        	<textarea name="description" id="description" data-default-value="Description" rows="50" cols="25" class="textInput"></textarea> 
	        <p class="formHint"></p>
		</div>
		     </fieldset>
      <div class="buttonHolder">
        <button type="submit" id="link" name="link" class="primaryAction">Finished!</button>
      </div>
    </form>

    <div id="footer">
    </div>
	<!-- JavaScript and JQuery part -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="resources/js/uni-form-validation.jquery.js" charset="utf-8"></script>
      </body>
</html>
