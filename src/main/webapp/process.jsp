<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Process Loan</title>
</head>
<body>
	<!-- write the code to read application number, and send it to admincontrollers
	     callemi method to calculate the emi and other details also provide links
	     to logout and admin home page
	-->
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<div align="center">
<c:choose>
	<c:when test = "${message}">
	<p style="color:red">Unable to process the loan!!Application No: <Strong style="color:blue">"${applicationNo}"</Strong></p>
	</c:when>
	</c:choose>
<form action="process" method="post">
			<div><label for="applicationNo">Enter Application No</label> </div>
			<div><input type="number" id="applicationNo" name="applicationNo" required> </div><br>
			<div><button type="submit">Submit</button></div>
</form>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>