<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Loan Application</title>
</head>
<body>
	<!-- read the application number to edit from user and send to 
	     user controller to edit info
	-->
	<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h3 align="center">Edit Loan Application</h3>
<c:choose>
	<c:when test = "${messageIncorrectTrackId}">
	<p style="color:red" align="center"><strong>This Application id does not exist. Please try again</Strong></p>
	</c:when>
	<c:when test = "${nonEditable}">
	<p style="color:red" align="center"><strong>This Application id cannot be edited as it is already processed. Please go to Track Loan link to track the status</Strong></p>
	</c:when>
	<c:when test = "${invalidAppliNoForCurrentUser}">
	<p style="color:red" align="center"><strong>Invalid Application No. Please try again</Strong></p>
	</c:when>
</c:choose>
<div align="center">
<form action="editloan" method="post">
			<div><label for="applicationNo">Enter Application No</label> </div>
			<div><input type="number" id="applicationNo" name="applicationNo" required> </div><br>
			<div><button type="submit">Submit</button></div>
</form>
</div>
<p style="color:blue" align="center"><strong>Note:</strong> Loan details cannot be edited if approved</p>
<jsp:include page="footer.jsp"/>

</body>
</html>