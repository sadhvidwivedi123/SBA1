function validateform(){  
var loginId=document.register.loginId.value;  
var password=document.register.password.value;  
var mob=document.register.phone.value;
var name=document.register.userName.value;
var email=document.register.email.value;
if(loginId.length<6){  
	  alert("User ID must be at least 6 characters long. and unique, Please try again");  
	  return false;  
	  }  
if(password.length<6){  
  alert("Password must be at least 6 characters long.");  
  return false;  
  }  
if(mob.length!=10){  
	  alert("Phone no must have 10 digits");  
	  return false;  
	  }  
if(email.length>50){  
	  alert("Email length cannot be greater than 30");  
	  return false;  
	  }  
if(loginId.length>20 || password.length>20 || name.length>30){  
	  alert("LoginId, password, name length cannot be greater than 20");  
	  return false;  
	  }  
}

function validateCalEMIform(amtRequested)
{  
var amountSactioned=document.sanctioner.amountSactioned.value;
if(amountSactioned>amtRequested){
	  alert("Sanction amount cannot be greater than the requested amount");  
	  return false;  
	  }
}

function validateEditLoanform(){  
var loanName=document.editor.loanName.value;
var address=document.editor.address.value;
var phone=document.editor.phone.value;
var email=document.editor.email.value;
var loanAmountRequested=document.editor.loanAmountRequested.value;
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
if(loanAmountRequested.length<0){
	  alert("Amount requested cannot be negative");  
	  return false;  
	  }
}

function validateApplicationForm(){  
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

