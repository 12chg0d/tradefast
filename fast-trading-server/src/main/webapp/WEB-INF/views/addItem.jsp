<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.ArrayList" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Add Item Page</title>
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
    <form action="AddItemController" class="uniForm" method="post">
      <% String haveCat = (String)session.getAttribute("haveCat");
      	 String dupI = (String)session.getAttribute("dupI");	
      	if(dupI != null && dupI.equalsIgnoreCase("no")){	
      	%>
	        <div id="errorMsg">
	        <h4>มี item ดังกล่าวแล้วในระบบ</h4>
	          <ol>
	            <li>ลองเลือก ที่ edit an item จ้า.</li>
	          </ol>
	      	</div>
	      	
	      <% 
      	  		session.setAttribute("dupI", null);  	
      		} 
      		if(haveCat != null && haveCat.equalsIgnoreCase("no")){	
          	%>
    	        <div id="errorMsg">
    	        <h4>ยังไม่มี category เลย</h4>
    	          <ol>
    	            <li>ลองเลือก ที่ add a category จ้า.</li>
    	          </ol>
    	      	</div>
    	      	
    	      <% 
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
        <% ShowCatBean scb = (ShowCatBean)session.getAttribute("scb"); 
        if(scb != null) { 
        	ArrayList<String[]> arr = scb.getArr();
        	int size = arr.size();
    		String[] str = new String[2];
        	if(arr != null&& size > 0) {

        %>
		        <div class="ctrlHolder">
		          <label for=""><em>*</em> Category</label>
		          <select name="catID" id="catID" class="required">
		<%		for(int i = 0; i < size; i++) {
        			str = arr.get(i);
        %>
		            <option value= <%=str[0] %> ><%=str[1] %></option>
		<%
		            }
		 %>
		          </select>
		          <p class="formHint"></p>
		        </div>
		        
		<% 
			} 
        }
		%>
        <div class="ctrlHolder">
	    	<label for=""><em>*</em> Item Name</label>
	         <input name="Iname" id="Iname" data-default-value="Item Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Description</label>
	        	<textarea name="description" id="description" data-default-value="Description" rows="50" cols="25" class="textInput"></textarea> 
	        <p class="formHint"></p>
		</div>
		<th>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Price Money Only</label>
	        	<input name="price_money_only" id="price_money_only" data-default-value="Price Money Only" size="35" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Price Point Only</label>
	        	<input name="price_point_only" id="price_point_only" data-default-value="Price Point Only" size="35" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Price Money Couple</label>
	        	<input name="price_money_couple" id="price_money_couple" data-default-value="Price Money Couple" size="35" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Price Point Couple</label>
	        	<input name="price_point_couple" id="price_point_couple" data-default-value="Price Point Couple" size="35" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Remaining Number</label>
	        	<input name="remaining_numbers" id="remaining_numbers" data-default-value="Remaining Number" size="10" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em>  Minimum Number</label>
	        	<input name= "minimum_numbers" id=" minimum_numbers" data-default-value=" Minimum Number" size="10" maxlength="50" type="text" class="textInput required validateInteger validateMin val-0"/>
			<p class="formHint"></p>
		</div>
        
	    
         <div class="ctrlHolder">
      		<label for="">Picture upload</label>
      		  <input type="file" id="picture" name="picture" size="35" class="fileUpload" onchange="upload(this.files[0])">
      		
    	</div>    		
      </fieldset>
      <% session.setAttribute("scb", null); %>
      <div class="buttonHolder">
        <button type="submit" id="link" name="link" class="primaryAction">Finished!</button>
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
    
    <script type="text/javascript">
	    function upload(file) {
	    	   // file is from a <input> tag or from Drag'n Drop
	    	   // Is the file an image?
	    	   if (!file || !file.type.match(/image.*/)) return;
	
	    	   // It is!
	    	   // Let's build a FormData object
	    	   var fd = new FormData();
	    	   fd.append("image", file); // Append the file
	    	   fd.append("key", "5abe7b5d0d5ea160341fe204e476bbbe"); 
	    	   // Create the XHR (Cross-Domain XHR FTW!!!)
	    	   var xhr = new XMLHttpRequest();
	    	   xhr.open("POST", "http://api.imgur.com/2/upload.json"); // Boooom!
	    	   xhr.onload = function() {
	    	      // Big win!
	    	      // The URL of the image is:
	               document.querySelector("#link").value = JSON.parse(xhr.responseText).upload.links.original;
	    	   }
	
	    	   // Ok, I don't handle the errors. An exercice for the reader.
	    	   // And now, we send the formdata
	    	   xhr.send(fd);
	    	}
    </script>
  </body>
</html>
