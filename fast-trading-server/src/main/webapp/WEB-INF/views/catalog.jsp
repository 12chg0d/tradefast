<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %><!-- Test -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Catalog</title>
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
  			<li><a href="ShowCatControllerForCat">CATALOG</a></li>
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
	</div>
	<!--end of header -->
	
	
	<div id="container">	
		<% ShowCatBean scbForCat = (ShowCatBean)session.getAttribute("scbForCat"); 
		if(scbForCat != null) { 
        	ArrayList<String[]> arr = scbForCat.getArr();
        	int size = arr.size();
    		String[] str = new String[2];
        	if(arr != null&& size > 0) {
        %>
		        <div>
		          <label for=""><em>*</em> Category</label>
		          <select name="catID" id="catID">
		<%		for(int i = 0; i < size; i++) {
        			str = arr.get(i);
        			//System.out.println("str[0]");
        %>
		            <option value= <%=str[0] %> ><%=str[1] %></option>
		<%
		         }
		%>
					<option value="all">All</option> 
				 </select>
				 </div>
		<%			
			}
		}					
		 	CatalogBean cb = (CatalogBean)session.getAttribute("cb");
		 	int p = 0;
		 	String pageNum = (String)request.getParameter("pageNum");
		 	if(pageNum != null) p = Integer.parseInt(pageNum)-1;
		 	if(cb != null) {
		 		ArrayList<String[][]> arr = cb.getArr();
		 		int size = arr.size();
		 		if(arr != null && size > 0) {
		 			String[][] str = arr.get(p);
					for(int i = 0; i < 12; i++) {
		%>
				<div>
		<%				if(str[i][0] != null) {
							for(int j = 0; j < 7; j++) {
							
		%>
								<p><%=str[i][j] %></p>
		<%	
							
							}
		%>
							<a href="AddToCartController?iID="+<%=str[i][0]%> >Add to Cart</a>
		<%
						} else {
							break;
						}
		%>		
				</div>
		<%
		 			}
		 		}
		 		for(int i = 0; i < size; i++) {
					String pTemp = Integer.toString(i+1);
		%>
			<a href="catalog?pageNum=<%=pTemp %>"><%=pTemp %></a>
		<%	
					}
		 	}
		 %>
		 
		
	</div>
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
	<script src="http://code.jquery.com/jquery-latest.js" type="text/javascript"></script>    
	<script>
	    $(function(){
	      // bind change event to select
	      $('#catID').bind('change', function () {
	    	  var catID = $(this).val();
	          window.location.href= "CatalogController?catID="+catID;
	      });
	    });
	</script>
</body>
</html>