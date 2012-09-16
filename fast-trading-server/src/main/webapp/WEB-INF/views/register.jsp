<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="bean.*" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Registration Page</title>
    <!-- This page is used to deal with registration. 
    It will receive the username and password/confirm password 
    and then it will call “RegistrationController” to put data into database using JDBC connector 
    and then redirect you to go the “welcome.jsp” page. -->
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
    <form action="RegistrationController" class="uniForm" method="post">
      <% String dup = (String)session.getAttribute("dup");
      		if(dup != null && dup.equalsIgnoreCase("no")){	
      	%>
	        <div id="errorMsg">
	        <h4>มี username ดังกล่าวแล้วในระบบ</h4>
	          <ol>
	            <li>ลองเลือก  username ใหม่จ้า.</li>
	          </ol>
	      	</div>
	      	
	      <% 
      	  		session.setAttribute("dup", null);  	
      		}
	      %>      
      <fieldset>
        <h3>Hi, new member.</h3>
        
        <div class="ctrlHolder">
	    	<label for=""><em>*</em> First Name</label>
	         <input name="Fname" id="Fname" data-default-value="First Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Last Name</label>
	        	<input name="Lname" id="Lname" data-default-value="Last Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
          <label for=""><em>*</em> Sex</label>
          <select name="sex" id="sex" class="required">
            <option value="male">Male</option>
			<option value="female">Female</option>
          </select>
          <p class="formHint"></p>
        </div>
        <div class="ctrlHolder">
          <label><em>*</em>Birth Date</label>
	        	<input name="birthdate" id="birthdate" data-default-value="Birth Date" size="15" type="date" class="textInput required"/>
		  <p class="formHint"></p>
        </div>
	    <div class="ctrlHolder">	
	    	<label for=""> Address</label>
	        	<textarea name="address" id="address" data-default-value="Address" rows="50" cols="25" class="textInput"></textarea> 
	        <p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> E-mail</label>
	        	<input name="email" id="email" data-default-value="E-mail" size="35" maxlength="50" type="email" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Mobile </label>
	        	<input name="tel_no" id="tel_no" data-default-value="Mobile Phone" size="35" maxlength="50" type="tel" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> User Name</label>
	         <input name="username" id="username" data-default-value="User Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
			<label for=""><em>*</em> Password</label>
	        	<input name="password" id="password" data-default-value="password" size="35" maxlength="50" type="password" class="textInput required"/>
			<p class="formHint"></p>
		</div>	          
        <div class="ctrlHolder">
        	<label for=""><em>*</em> Confirm Password</label>
          		<input name="password_confirm" id="password_confirm" data-default-value="password" size="35" maxlength="50" type="password" class="textInput required validateSameAs password"/>
          	<p class="formHint"></p>
         </div>
         <div class="ctrlHolder">
      		<label for="">File upload</label>
      		  <input type="file" id="" name="" size="35" class="fileUpload" onchange="upload(this.files[0])">
      		<a id="link">Test</a>
    	</div>    		
      </fieldset>
      
      <div class="buttonHolder">
        <button type="submit" class="primaryAction">Finished!</button>
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
	               document.querySelector("#link").href = JSON.parse(xhr.responseText).upload.links.imgur_page;
	    	   }
	
	    	   // Ok, I don't handle the errors. An exercice for the reader.
	    	   // And now, we send the formdata
	    	   xhr.send(fd);
	    	}
    </script>
    
  </body>
</html>
