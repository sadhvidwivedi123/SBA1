<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sanction Loan</title>
</head>
<body>
<script>  
function validateform(){  
var amountSactioned=document.sanctioner.amountSactioned.value;
var rate=document.sanctioner.rate.value;
if(amountSactioned>${String.format('%.2f',loan.getAmtrequest())}){
	  alert("Sanction amount cannot be greater than the requested amount");  
	  return false;  
	  }
}
</script> 
 <!--
     Read the values from the admin servlet and cal emi and other details and send to
     to the same admin servlet to update the values in the database 
  -->  
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h2 style="color:blue">Sanction Loan</h2>

<c:choose>
	<c:when test = "${processed}">
	<h3 style="color:red"><strong>This loan is already processed , any changes will modify the loan </strong></h3>
	</c:when>
</c:choose>
<div>
			<div><strong><label for="status">Loan Application Status</label></strong></div>
			<div><label for="status">${loan.getStatus()}</label></div>
		</div>
<div>
			<div><strong><label for="applicationNo">Loan Application No</label></strong></div>
			<div><input type="number" id="applicationNo" name="applicationNo" value="${loan.getApplno()}" readonly></div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanName">Enter Loan Description</label></strong></div>
			<div><input type="text" id="loanName" name="loanName" value="${loan.getPurpose()}" readonly> </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanAmountRequested">Loan Amount Required(INR) </label></strong></div>
			<div><input type="number" step="any" id="loanAmountRequested" name="loanAmountRequested" value="${String.format('%.2f',loan.getAmtrequest()) }" readonly> </div>
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
<hr>
<form action="updatestatus" method="post" name="sanctioner" onsubmit="return validateform()">
        <div>
			<div><strong><label for="amountSactioned">loan Amount Sanctioned</label></strong></div>
			<div><input type="number" step="any" id="amountSactioned" name="amountSactioned" value="${aloan.getAmotsanctioned() }" required></div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanTerm">Term Of Loan (Years)</label></strong></div>
			<div><input type="number" id="loanTerm" name="loanTerm" value="${aloan.getLoanterm() }" required></div>
		</div>
		<br>
		<div>
			<div><strong><label for="rate">Interest Rate</label></strong></div>
			<div><input type="number" step="any" id="rate" name="rate" value="${aloan.getRate() }" required></div>
		</div>
		<br>
		<div>
			<div><strong><label for="startDate">Payment Start Date</label></strong></div>
			<div><input type="Date" id="startDate" name="startDate" value="${aloan.getPsd() }" required></div>
		</div>
		<br>
		<div>
			<div><input type="submit" value="Approve" name="approve"></div>
		</div>
</form>
<br>
<form action="updatestatus" method="post">
        <div>
			<button name="reject">Reject</button>
		</div>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>