<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Registration</title>
 <link rel="shortcut icon" href="#">
</head>
<body>

<jsp:include page="header.jsp"/>
<hr/>
<div align=center>
	<h2 style=color:blue>eLoan Register</h2>
	<c:choose>
	<c:when test = "${message}">
	<p style="color:red">UserID Already exists! Please give unique userID</p>
	</c:when>
	</c:choose>
	<form action="registernewuser" method="post" name="register" onsubmit="return validateform()">
		<div>
			<div><label for="loginId">Enter login Id</label> </div>
			<div><input type="text" id="loginId" name="loginId" required > </div>
		</div>
		<br>
		<div>
			<div><label for="password">Enter password</label> </div>
			<div><input type="password" id="password" name="password" required> </div>
		</div>
		<br>
		<div>
			<div><label for="name">Enter Name of User</label> </div>
			<div><input type="text" id="text" name="userName" required> </div>
		</div>
		<br>
		<div>
			<div><label for="phone">Enter Phone no.</label> </div>
			<div><input type="number" id="phone" name="phone" required> </div>
		</div>
		<br>
		<div>
			<div><label for="email">Enter Email id</label> </div>
			<div><input type="email" id="email" name="email" required> </div>
		</div>
		<br>
		<div>
			<div><input type="submit" value="Register"> </div>
		</div>
	</form>
</div>

<jsp:include page="footer.jsp"/>
<script src="js/validateForm.js"></script>
</body>
</html>