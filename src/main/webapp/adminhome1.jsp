<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin home</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div align="right">
		<a href="logout">Logout</a>
	</div>
	<h2 style="color: blue">Administrator Dash Board</h2>
	<c:choose>
		<c:when test="${rejected}">
			<p style="color: red">
				Loan rejected! Application No: <Strong style="color: blue">"${applicationNo}"</Strong>
			</p>
		</c:when>
		<c:when test="${approved}">
			<p style="color: red">
				Loan approved Successfully!! Application No: <Strong
					style="color: blue">"${applicationNo}"</Strong>
			</p>
			<p style="color: blue">
				<Strong>Loan Details:</Strong>
			</p>

			<style>
table, th, td {
	border: 1px solid blue;
	border-collapse: collapse;
}

table.center {
	margin-left: auto;
	margin-right: auto;
}
</style>
<table class="center">
<thead>
<tr><th>Amount Sanctioned</th><th>Term of Loan</th><th>Payment Start Date</th><th>Loan Closure Date</th><th>Monthly Payment</th><th></th></tr>
</thead>
<tbody>
<tr><td>${sanctionedAmt}</td><td>${termOfLoan}</td><td>${psd}</td><td>${lcd}</td><td>${String.format("%.2f",emi)}</td></tr>
</tbody>
</table>
</c:when>
</c:choose>
	<br>
	<a href="listall">List All</a>
	<br>
	<a href="process.jsp">Process Loan</a>
	<br>
	<jsp:include page="footer.jsp" />
</body>
</html>