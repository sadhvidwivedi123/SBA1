<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Track Loan</title>
</head>
<body>
	<!-- write html code to read the application number and send to usercontrollers'
             displaystatus method for displaying the information
	-->
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h3 align="center">Track Loan Application</h3>
<c:choose>
	<c:when test = "${messageIncorrectTrackId}">
	<p style="color:red"><strong>Incorrect Application No. Please try again</Strong></p>
	</c:when>
</c:choose>
<div align="center">
<form action="trackloan" method="post">
			<div><label for="applicationNo">Enter Application No</label> </div>
			<div><input type="number" id="applicationNo" name="applicationNo" required> </div><br>
			<div><button type="submit">Submit</button></div>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>