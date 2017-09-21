<%-- 
    Document   : index
    Created on : Dec 28, 2016, 9:16:31 AM
    Author     : user
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html >
<head>
  <meta charset="UTF-8">
  <title>MeTL-Attendance</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
  <br>
  <center><img src="METLLogo.jpg" height="100" width="200"></center>

  
  
  <div class="wrapper">
	
	<div class="container">
		
		<form class="form" action="LoginServlet" method="post">
			<input type="text" placeholder="Username" name="Username">
			<input type="password" placeholder="Password" name="Password">
			<button type="submit">Login</button>
		</form>
	</div>
	

</div>
  <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

    <script src="js/index.js"></script>

</body>
</html>
