<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="servlet.*" %>

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
    
    <!--[if lte ie 7]>
      <style type="text/css" media="screen">
        /* Move these to your IE6/7 specific stylesheet if possible */
        .uniForm, .uniForm fieldset, .uniForm .ctrlHolder, .uniForm .formHint, .uniForm .buttonHolder, .uniForm .ctrlHolder ul{ zoom:1; }
      </style>
    <![endif]-->

  </head>

  <body>
	<!-- This part will receive the data from user and then send it to RegisterCotroller. -->
    <form action="RegistrationController" class="uniForm" method="post">
            
      <fieldset>
        <h3>Hi, new member.</h3>
        
        <div class="ctrlHolder">
	    	<label for=""><em>*</em> First Name</label>
	         <input name="firstName" id="firstName" data-default-value="First Name" size="35" maxlength="50" type="text" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em> Last Name</label>
	        	<input name="lastName" id="lastNname" data-default-value="Last Name" size="35" maxlength="50" type="text" class="textInput required"/>
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
	    	<label for=""><em>*</em> Mobile</label>
	        	<input name="telNo" id="telNo" data-default-value="Mobile Phone" size="35" maxlength="50" type="tel" class="textInput required"/>
			<p class="formHint"></p>
		</div>
		<div class="ctrlHolder">
	    	<label for=""><em>*</em>User Name</label>
	         <input name="userName" id="userName" data-default-value="User Name" size="35" maxlength="50" type="text" class="textInput required"/>
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
  </body>
</html>
