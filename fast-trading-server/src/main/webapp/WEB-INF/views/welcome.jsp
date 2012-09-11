<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="servlet.*" %>
<%@ page import="bean.*" %>
<!doctype html>
<html lang="en-us" dir="ltr">
  <head>
  <meta charset="utf-8">
    <title>Welcome to our world.</title>
    <!-- This search page will deal with “SearchBean” in order to collect the result from “Top10Results” and “Top10HybridResults” class
     (“Top10HybridResults”  is for searching by ID and Top10Results is for searching by other fields.) 
     It will check with “SearchBean” and “UserBean” in order to show the result to users. -->
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
 	<%
 		//There are two cases. The first is "You have never indexed any document.", it will not show the "Go to search page" button.
 		//Another case is "You have ever indexed some documents.", it will show the "Go to search page" button.
		UserBean ub = (UserBean)session.getAttribute("user");
		if(ub != null) {
	%>
		<form class="uniForm">
			<div class="header">
        		<h3>Welcome <b><%= ub.getUserName()%>!!!</b></h3>
        		<h3>First Name : <b><%= ub.getFirstName()%>!!!</b></h3>
        		<h3>Last Name : <b><%= ub.getLastName()%>!!!</b></h3>
        		<h3>Address : <b><%= ub.getAddress()%>!!!</b></h3>
        		<h3>Mobile Phone : <b><%= ub.getTelNo()%>!!!</b></h3>
        		<h3>E-mail : <b><%= ub.getEmail()%>!!!</b></h3>
      		</div>
      		<div id="okMsg">
        		<p>
         			Congratulation!!! </p>
    		 </div>
		</form>
	<%
		}
	%>
    <div id="footer">
    </div>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4/jquery.min.js"></script>
    <script type="text/javascript" src="./js/uni-form-validation.jquery.js" charset="utf-8"></script>
  </body>
</html>
