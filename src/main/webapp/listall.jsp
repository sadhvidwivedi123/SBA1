<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display All Loans</title>
</head>
<body>
	<!-- write code to display all the loan details 
             which are received from the admin controllers' listall method
	--> 
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h3 align="center">List All Loan Applications</h3>
<c:choose>
<c:when test="${(loansList==null) || loansList.isEmpty()}">
<p> No Loans Found </p>
</c:when>
<c:otherwise>
<style>
table,th,td {
border: 1px solid blue;
border-collapse: collapse;
}
table.center{
margin-left: auto;
margin-right: auto;
}
</style>

<table class="center">
<thead>
<tr><th>Application No</th><th>Application Status</th><th>Loan Description</th><th>Amount Requested</th><th>Application Date</th></tr>
</thead>
<tbody>
<c:forEach var="l" items="${loansList }">
<tr><td>${l.applno}</td><td>${l.status }</td><td>${l.purpose}</td><td>${String.format("%.2f",l.amtrequest)}</td><td>${l.doa}</td></tr>

</c:forEach>
</tbody>
</table>

</c:otherwise>

</c:choose>
<jsp:include page="footer.jsp"/>
</body>
</html>