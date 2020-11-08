<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>eLoan Application</title>
</head>
<body>
	<!-- write the html code to read user credentials and send it to validateservlet
	    to validate and user servlet's registernewuser method if create new user
	    account is selected
	-->
	<jsp:include page="header.jsp"></jsp:include>
	<hr>
	<div align="center">
	<h2 style=color:blue>eLoan Login</h2>
	<c:choose>
	<c:when test = "${error}">
	<p style="color:red">Incorrect Credentials</p>
	</c:when>
	<c:when test = "${logout}">
	<p style="color:red">User logged off Successfully</p>
	</c:when>
	</c:choose>
	<c:choose>
	<c:when test = "${message}">
	<p style="color:green">User Registered successfully! Please login to apply loan</p>
	</c:when>
	</c:choose>
	<form action="validate" method="post">
	<div>
	<div>
		<div><label for="userId">User ID:</label></div>
		<div><input type="text" name="userId" autocomplete="off" value ="" required></div>
	</div>
	<br>
	<div>
		<div><label for="password">Password:</label></div>
		<div><input type="password" name="password" autocomplete="off" required></div>
	</div>
	<br>
	<div><button type="submit">Login</button></div>
	<br>
	</div>
	</form>
	<div align="center">
	<label>Not a Existing User? </label>
	<a href="register.jsp">Register!</a>
	</div>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
</html>