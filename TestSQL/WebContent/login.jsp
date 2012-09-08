<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Login Page</title>
    <!-- This page is about authentication page. 
    It will receive username and password from the user and then it will forward to “welcome.jsp” page by using “loginbean.jsp” 
    and “LoginBean”. This page also check the parameter “isHas” in order to show the error message. 
    This page will call “LoginController” to check username and password from database 
    and create “UserBean” to throw the user’s data to other pages. -->
	<link href="./css/uni-form.css" media="screen" rel="stylesheet"/>
	<link href="./css/default.uni-form.css" title="Default Style" media="screen" rel="stylesheet"/>
	<link href="./css/demo.css" media="screen" rel="stylesheet"/>
	

    
    <!--[if lte ie 7]>
      <style type="text/css" media="screen">
        /* Move these to your IE6/7 specific stylesheet if possible */
        .uniForm, .uniForm fieldset, .uniForm .ctrlHolder, .uniForm .formHint, .uniForm .buttonHolder, .uniForm .ctrlHolder ul{ zoom:1; }
      </style>
    <![endif]-->

  </head>

  <body>
	<!-- It will receive the username and password here and then send it to loginbean.jsp -->
    <form action="loginbean.jsp" class="uniForm" method="post">
      <div class="header">
        
      </div>      
      	<% String isHas = (String)session.getAttribute("isHas");
      		if(isHas != null && isHas.equalsIgnoreCase("no")){	
      	%>
	        <div id="errorMsg">
	        <h4>The username and/or password you supplied was not correct.</h4>
	          <ol>
	            <li>Try again.</li>
	            <li>Click "Create a new one.":)</li>
	          </ol>
	      	</div>
	      	
	      <% 
      	  		session.setAttribute("isHas", null);  	
      		}
	      %>
	    <fieldset>
        <div class="ctrlHolder">
          <label for=""><em>*</em> Username</label>
          <input name="userName" id="userName" data-default-value="Username" size="35" maxlength="50" type="text" class="textInput required"/>
          <p class="formHint">Enter your username.</p>
        </div>
        
        <div class="ctrlHolder">
          <label for=""><em>*</em> Password</label>
          <input name="password" id="password" data-default-value="password" size="35" maxlength="50" type="password" class="textInput required"/>
          <p class="formHint">Enter your password.</p>
        </div>

      </fieldset>
      
      <div class="buttonHolder">
		<a href="register.jsp" class="secondaryAction">Create a new one.</a>
        <button type="submit" class="primaryAction">Login!</button>
      </div>
    </form>

    <div id="footer">
    </div>
	<!-- JavaScript and JQuery part -->
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./js/uni-form-validation.jquery.js" charset="utf-8"></script>
    <script>
      $(function(){
        $('form.uniForm').uniform({
          prevent_submit : true
        });
      });
    </script>
  </body>
</html>



