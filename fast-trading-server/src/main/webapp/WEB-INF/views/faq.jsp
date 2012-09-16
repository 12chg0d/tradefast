<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %><!-- Test -->
<!doctype html>
<html lang="th" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Search Page</title>
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
				  <input type="image" src="resources/img/search_icon.png" class="primaryAction" width="20">
		    </form>
		</div>
			
			<%
			SearchBean sb = (SearchBean)session.getAttribute("searchResult");
		    String haveTableS = (String)session.getAttribute("haveTableS");
			if(sb != null) {
				ArrayList<String[]> arr = sb.getArr();	
				int size = arr.size();
				String[] str = new String[2];
				if(arr != null && size > 0) {
						
			%>
			<div class="center">
			<table id="newspaper-a" summary="test-table">
			   <colgroup>
			    	<col class="oce-first" />
			    </colgroup>
			    <thead>
			    	<tr>
			        	<th scope="col" align= "center" >Search Result</th>
			        </tr>
			    </thead>
			    </table>
			    
			    <table id="newspaper-a" summary="test-table">
			   <colgroup>
			    	<col class="oce-first" />
			    </colgroup>
			    <thead>
			    	<tr>
						<th scope="col">ID</th>        	
						<th scope="col">Name</th>
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
			else if(haveTableS != null && haveTableS.equalsIgnoreCase("no")) {
			%>
					<div class="center">
					<table id="newspaper-a" summary="test-table">
					   <colgroup>
					    	<col class="oce-first" />
					    </colgroup>
					    <thead>
					    	<tr>
					        	<th scope="col" align= "center" >This item does not exist!</th>
					        </tr>
					    </thead>
					    </table>
					 </div>
			<%			
				}
			session.setAttribute("haveTableS", null);
			session.setAttribute("searchResult", null );
			%>
  		
  		
  		
  		
  		
  		
  	</div>
  	
	<!--end of header -->
	
	
		

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



