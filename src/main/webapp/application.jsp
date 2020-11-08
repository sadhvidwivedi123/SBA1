<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Loan Application Form</title>
</head>
<body onload="myFunction()">
<script>  
function validateform(){  
var loanName=document.applier.loanName.value;
var address=document.applier.address.value;
var phone=document.applier.phone.value;
var email=document.applier.email.value;
if(loanName.length>100){
	  alert("Loan description length cannot be more than 100");  
	  return false;  
	  }
if(address.length>200 || address.length<5){
	  alert("Address length cannot be more than 200 and less than 5");  
	  return false;  
	  }
if(phone.length!=10){
	  alert("phone number length should be equal to 10 digits");  
	  return false;  
	  }
if(email.length>50){
	  alert("Email id length cannot be more than 50");  
	  return false;  
	  }
}
</script>
<!--
	write the html code to accept laon info from user and send to placeloan servlet
-->
<jsp:include page="header.jsp"/>
<div align="right"><a href="logout">Logout</a></div>
<h2 style="color:blue">eLoan Application</h2>
<div>
	<form action="placeloan" method="post" name="applier" onsubmit="return validateform()">
		<div>
			<div><strong><label for="loanName">Enter Loan Description</label></strong></div>
			<div><input type="text" id="loanName" name="loanName" required > </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanAmountRequested">Enter Required Loan Amount(INR) </label></strong></div>
			<div><input type="number" step="any" id="loanAmountRequested" name="loanAmountRequested" required> </div>
		</div>
		<br>
		<div>
			<div><strong><label for="loanApplicationDate">Loan Application Date</label></strong></div>
			<div><input type="date" id="loanApplicationDate" name="loanApplicationDate" readonly value=<%=LocalDate.now()%>></div>
		</div>
		<br>
		<div>
			<div><strong><label for="businessStructure">Select Business Structure</label></strong></div>
			<input type="radio" id="individual" name="businessStructure" value="individual" required>
  			<label for="individual">Individual</label>
  			<input type="radio" id="organization" name="businessStructure" value="organisation">
  			<label for="organization">Organization</label>
		</div>
		<br>
		<div>
			<div><strong><label for="billingIndicator">Select Billing Indicator</label></strong></div>
			<input type="radio" id="salaried" name="billingIndicator" value="salaried" required>
  			<label for="salaried">Salaried</label>
  			<input type="radio" id="nonSalaried" name="billingIndicator" value="non-salaried">
  			<label for="nonSalaried">Non-Salaried</label>
		</div>
		<br>
		<div>
			<div><strong><label for="taxIndicator">Select Tax Indicator</label></strong></div>
			<input type="radio" id="taxPayer" name="taxIndicator" value="tax-payer" required>
  			<label for="taxPayer">Tax-Payer</label>
  			<input type="radio" id="nontaxPayer" name="taxIndicator" value="non-tax-payer">
  			<label for="nontaxPayer">Non-Tax Payer</label>
		</div>
		<br>
		<div>
			<div><strong><label for="address">Enter Address</label></strong></div>
			<div><input type="text" id="address" name="address" required></div>
		</div>
		<br>
		<div>
			<div><strong><label for="phone">Enter Phone no.</label></strong></div>
			<div><input type="number" id="phone" name="phone" required></div>
		</div>
		<br>
		<div>
			<div><strong><label for="email">Enter Email id</label></strong></div>
			<div><input type="email" id="email" name="email" required></div>
		</div>
		<br>
		<div>
			<div><input type="submit" value="Apply Loan"></div>
		</div>
	</form>
	</div>


<jsp:include page="footer.jsp"/>
</body>
</html>