<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Details</title>
</head>
<body>
	<!-- write the code to display the loan status information 
	     received from usercontrollers' displaystatus method
	-->

<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
	<h2 style="color:blue">Your Loan Details</h2>
	<jsp:include page="displayStatus.jsp"/>
	<br>
	<br>
		<div>
			<div><strong><label for="applicationNo">Loan Application No</label></strong></div>
			<div><input type="text" id="loanName" name="loanName" value="${loan.getApplno()}" readonly> </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanName">Enter Loan Description</label></strong></div>
			<div><input type="text" id="loanName" name="loanName" value="${loan.getPurpose()}" readonly> </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanAmountRequested">Loan Amount Required(INR) </label></strong></div>
			<div><input type="number" step="any" id="loanAmountRequested" name="loanAmountRequested" value="${String.format('%.2f',loan.getAmtrequest())}" readonly> </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanApplicationDate">Loan Application Date</label></strong></div>
			<div><input type="date" id="loanApplicationDate" name="loanApplicationDate" value="${loan.getDoa() }" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="businessStructure">Business Structure</label></strong></div>
			<div><input type="text" id="businessStructure" name="businessStructure" value="${loan.getBstructure() }" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="billingIndicator">Billing Indicator</label></strong></div>
			<div><input type="text" id="billingIndicator" name="billingIndicator" value="${loan.getBindicator() }" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="taxIndicator">Tax Indicator</label></strong></div>
			<div><input type="text" id="taxIndicator" name="taxIndicator" value="${loan.getTindicator()}" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="address">Address</label></strong></div>
			<div><input type="text" id="address" name="address" size="30" value="${loan.getAddress()}" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="phone">Phone no.</label></strong></div>
			<div><input type="number" id="phone" name="phone" value="${loan.getMobile()}" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="email">Email id</label></strong></div>
			<div><input type="email" id="email" size="30" name="email" value="${loan.getEmail()}" readonly></div>
		</div>
		<br>
<jsp:include page="footer.jsp"/>
</body>
</html>