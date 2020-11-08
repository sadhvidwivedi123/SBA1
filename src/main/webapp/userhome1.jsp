.<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Home</title>
</head>
<body>
	<!-- write the html code to display hyperlinks for user functionalities -->
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h2 style=color:blue>User Dash Board</h2>
	<c:choose>
	<c:when test = "${messageNewLoan}">
	<p style="color:green">Loan Applied Successfully!! Your Application No: <Strong style="color:blue">"${applicationNo}"</Strong>. Please track your application status using this number.</p>
	</c:when>
	<c:when test = "${messageEditLoan}">
	<p style="color:green">Your Loan Application id : <Strong style="color:blue">"${applicationNo}"</Strong> Updated Successfully!!</p>
	</c:when>
	</c:choose>
<a href="application.jsp">Apply Loan</a><br>
<a href="trackloan.jsp">Track Loan Application</a><br>
<a href="editloan.jsp">Edit Loan</a><br>
<jsp:include page="footer.jsp"/>
</body>
</html>