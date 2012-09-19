<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %><!-- Test -->
<%@ page import="java.util.LinkedHashMap" %>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<!doctype html>
<html lang="th" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Cart Page</title>
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
  			<li><a href="home">HOME</a></li>
  			<li><a href="catalog">CATALOG</a></li>
  			<li><a href="faq">FAQ</a></li>
  		</ul>
  		
  		<div id="right_menu">
  			<a href="account"><img src="resources/img/account_icon.png" height = "50"/></a>
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
		
	
	<!-- It will receive the username and password here and then send it to loginbean -->
 <form action="CheckOrderController" class="uniForm" >
 	<%
 		CheckOrderBean cob = (CheckOrderBean)session.getAttribute("cob");
 		if(cob != null) {
 			ArrayList<String> err = cob.getArr();
 			if(err != null && err.size() >0) {
 				int size = err.size();
 	%>
 				<div id="errorMsg">			        
 				<h3>Sorry, fill again.</h3>
 				<ol>
 	<%		for(int e = 0; e < size; e++) {
 					String temp = err.get(e);
 	%>	
 			            <li><%=temp%></li>
 	<% 		       			      
 				}
 	%>
 				</ol>
 			      </div>
 	<%			
 			}
 		}
 	%>
 
		<%
	CartBean cartB = (CartBean)session.getAttribute("cartB");
    String haveCart = (String)session.getAttribute("haveCart");
	String haveLogin = (String)session.getAttribute("haveLogin");
	if(cartB != null) {
		ArrayList<String[]> arr = cartB.getArr();	
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
	        	<th scope="col" align= "center" >My Cart</th>
	        </tr>
	    </thead>
	    </table>
	    
	    <table id="newspaper-a" summary="test-table">
	   <colgroup>
	    	<col class="oce-first" />
	    </colgroup>
	    <thead>
	    	<tr>
				<th scope="col">Picture</th>        	
				<th scope="col">Name</th>
				<th scope="col">Amount</th>
				<th scope="col">Money Only</th>
				<th scope="col">Point Only</th>
				<th scope="col">Point/Money<th>
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
	        	<td><%=str[8] %></td>
	        	<td><%=str[3] %></td>
	        	<%String n = str[0]; %>
	        	<td>
	    	<div class="ctrlHolder">
	        		<input name="<%=n + "0" %>" id="<%=n + "0" %>" type = "text" data-default-value="<%=str[1] %>"  size="10" class="textInput required validateInteger validateMin val-0" />
	        		<p class="formHint"></p>
	       </div>
	        	
	        	</td>
	        	
	        	<td>
	        	
        	<div class="ctrlHolder">
        			<label><%=str[4] %></label>
	        		<%String s = "textInput required validateInteger validateMin val-0";%>	     
	        		<input name="<%=n + "1" %>" id="<%=n + "1" %>" type = "text" size="10" data-default-value="0" class="<%=s %>" />
	        		<p class="formHint"></p>
	       </div>
	       
	        	</td>
	        	<td>
	        	<div class="ctrlHolder">
	        		<label><%=str[5] %></label>
	        		<input name="<%=n + "2" %>" id="<%=n + "2" %>" type = "text" size="10" data-default-value="0" class="<%=s %>" />
	        		<p class="formHint"></p>
	       </div>
	        	</td>
	        	<td>
	        	<div class="ctrlHolder">
	        		<label><%=str[6] +"/"+str[7]%></label>
            		<input name="<%=n + "3" %>" id="<%=n + "3" %>" type = "text" size="10" data-default-value="0" class="<%=s %>"/>
	        		<p class="formHint"></p>
	       </div>
	        	</td>
	<%				
					}
	%>
	        </tr>
	    </tbody>
	    </table>
	    </div>
	<%
		}
	}
	else if(haveCart != null && haveCart.equalsIgnoreCase("no")) {
	%>
			<div class="center">
			<table id="newspaper-a" summary="test-table">
			   <colgroup>
			    	<col class="oce-first" />
			    </colgroup>
			    <thead>
			    	<tr>
			        	<th scope="col" align= "center" >No cart anymore!</th>
			        </tr>
			    </thead>
			    </table>
			 </div>
	<%			
		}
	else if(haveLogin != null && haveLogin.equalsIgnoreCase("no")) {
		%>
		<div class="center">
		<table id="newspaper-a" summary="test-table">
		   <colgroup>
		    	<col class="oce-first" />
		    </colgroup>
		    <thead>
		    	<tr>
		        	<th scope="col" align= "center" >Please log in to the system.</th>
		        </tr>
		    </thead>
		    </table>
		 </div>
<%			
	} else {
%>
		<div class="center">
		<table id="newspaper-a" summary="test-table">
		   <colgroup>
		    	<col class="oce-first" />
		    </colgroup>
		    <thead>
		    	<tr>
		        	<th scope="col" align= "center" >Please log in to the system.</th>
		        </tr>
		    </thead>
		    </table>
		 </div>		
<%		
	}
	session.setAttribute("haveCart", null);
	session.setAttribute("haveLogin", null);
	session.setAttribute("cob", null);

	%>	
	      <div class="buttonHolder">
		<button type="submit" class="primaryAction">Order!</button>
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



