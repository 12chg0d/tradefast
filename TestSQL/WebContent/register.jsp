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
	<!-- This part will receive the data from user and then send it to RegisterCotroller. -->
    <form action="RegistrationController" class="uniForm" method="post">
            
      <fieldset>
        <h3>ยินดีต้อนรับ สมาชิกใหม่</h3>
        
        <div class="ctrlHolder">
	          <label for=""><em>*</em> Name</label>
	          	<input name="name" id="name" data-default-value="ชื่อ" size="35" maxlength="50" type="text" class="textInput required"/>
	    	<p class="formHint"></p>
	    </div>
	    <div class="ctrlHolder">
	    
	          <label for=""><em>*</em> Surname</label>
	          	<input name="name" id="surName" data-default-value="นามสกุล" size="35" maxlength="50" type="text" class="textInput required"/>
        		<p class="formHint"></p>
        </div>
        <div id="line"> </div>
        <div class="ctrlHolder">
          
	          <label for=""><em>*</em> User Name</label>
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
        <button type="submit" class="primaryAction">สมัครสมาชิก</button>
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
