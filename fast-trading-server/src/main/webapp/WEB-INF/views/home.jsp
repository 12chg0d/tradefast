<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %><!-- Test -->
<!doctype html>
<html lang="th" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Home Page</title>
    <link href="resources/css/uni-form.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/default.uni-form.css" title="Default Style" media="screen" rel="stylesheet"/>
	<link href="resources/css/demo.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/style.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/header_style.css" media="screen" rel="stylesheet"/>
	<link href="resources/css/home_style.css" media="screen" rel="stylesheet"/>

    
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
	<form method = "post" action="#">
		<div id="new_item">
		<%
			HomeBean hb = (HomeBean)session.getAttribute("hb");
		    if(hb != null) {
		    	ArrayList<String[]> arr = hb.getArrForPop();
		    	ArrayList<String[]> arr2 = hb.getArrForNew();
		    	int size = arr.size();
		    	int size2 = arr2.size();
		    	String[] str = new  String[7];
		    	String[] str2 = new  String[7];
		    	int p = hb.getCurrentPage();
		    	
		%>
			<h3>New Arrivals</h3>
			<div>
				new item here!
				<% if(arr != null && size > 0) {
					str = arr.get(p);
					for(int i = 0; i< 7; i++) {
				%>	
					<p><%=str[i] %></p>
				<%
					}
				%>
			</div>
				<a href="HomeController?pageNum=<%=Integer.toString((p+1)%4) %>">Next</a>
				<a href="HomeController?pageNum=<%=Integer.toString((p+3)%4) %>">Previous</a>
			<% } %>
		</div>
		
		
		<div id="hot_item">
			<h3>Hot items</h3>
			<div> 
			hot item here!
			<% if(arr2 != null && size > 0) {
					str = arr2.get(p);
					for(int i = 0; i< 7; i++) {
				%>	
					<p><%=str2[i] %></p>
				<%
					}
				%>
			</div>
			<% } %>
		</div>	
		<% } %>
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


