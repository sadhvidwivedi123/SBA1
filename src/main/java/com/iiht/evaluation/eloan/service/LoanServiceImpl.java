package com.iiht.evaluation.eloan.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.dao.ILoanDao;
import com.iiht.evaluation.eloan.dao.LoanDaoImpl;
import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;
import com.iiht.evaluation.eloan.model.User;

import exception.LoanException;

public class LoanServiceImpl implements ILoanService{
	
	private ILoanDao loanDao;
	
	public LoanServiceImpl() {
		
		this.loanDao = new LoanDaoImpl();
	}
	
	
	private void validateLoan(LoanInfo loan) throws LoanException{
		List<String> errMsgs = new ArrayList<String>();
		
		if(!isValidPurpose(loan.getPurpose())) {
			errMsgs.add("Loan Purpose cannot be more than 100 characters");
		}
		if(!isValidAmount(loan.getAmtrequest())){
			errMsgs.add("loan amount cannot be negative");
		}
		if(!isValidDate(loan.getDoa())) {
			errMsgs.add("Date should be current date");
		}
		/*if(!isValidEmail(loan.getBstructure())) {
			errMsgs.add("");
		}
		if(!isValidBillingInd(loan.getBindicator())) {
			errMsgs.add("Email length cannot be more than 30 alphanumeric characters");
		}
		if(!isValidTaxInd(loan.getTindicator())) {
			errMsgs.add("Email length cannot be more than 30 alphanumeric characters");
		}*/
		if(!isValidAddress(loan.getAddress())) {
			errMsgs.add("Address cannot be more than 200 characters");
		}
		if(!isValidPhoneNumber(loan.getMobile())) {
			errMsgs.add("Phone number should be 10 didgits only");
		}
		if(!isValidEmail(loan.getEmail())) {
			errMsgs.add("Email cannot be more than 50 characters");
		}
		if(!isValidStatus(loan.getStatus())) {
			errMsgs.add("Status should be Pending only for a submitted loan application");
		}
		if(!errMsgs.isEmpty()) {
			throw new LoanException(errMsgs.toString());
		}
	}
	private void validateUpdateLoan(LoanInfo loan) throws LoanException{
		List<String> errMsgs = new ArrayList<String>();
		
		if(!isValidPurpose(loan.getPurpose())) {
			errMsgs.add("Loan Purpose cannot be more than 100 characters");
		}
		if(!isValidAmount(loan.getAmtrequest())){
			errMsgs.add("loan amount cannot be negative");
		}
		
		/*if(!isValidEmail(loan.getBstructure())) {
			errMsgs.add("");
		}
		if(!isValidBillingInd(loan.getBindicator())) {
			errMsgs.add("Email length cannot be more than 30 alphanumeric characters");
		}
		if(!isValidTaxInd(loan.getTindicator())) {
			errMsgs.add("Email length cannot be more than 30 alphanumeric characters");
		}*/
		if(!isValidAddress(loan.getAddress())) {
			errMsgs.add("Address cannot be more than 200 characters");
		}
		if(!isValidPhoneNumber(loan.getMobile())) {
			errMsgs.add("Phone number should be 10 didgits only");
		}
		if(!isValidEmail(loan.getEmail())) {
			errMsgs.add("Email cannot be more than 50 characters");
		}
		
		if(!errMsgs.isEmpty()) {
			throw new LoanException(errMsgs.toString());
		}
	}
		
		
		/*private boolean isValidBillingInd(String bindicator) {
		// TODO Auto-generated method stub
		return false;
	}*/


	/*private boolean isValidTaxInd(String tindicator) {
		// TODO Auto-generated method stub
		return false;
	}*/


		private boolean isValidAddress(String address) {
		return address.length()<=200;
	}


		private boolean isValidPhoneNumber(String mobile) {
		return mobile.length()==10;
	}


		private boolean isValidEmail(String email) {
		return email.length()<=50;
	}


		private boolean isValidStatus(String status) {
		return status.equals("pending");
	}


		private boolean isValidDate(LocalDate localDate) {
		return localDate.equals(LocalDate.now());
	}

		
		private boolean isValidAmount(Double amtRequest) {
		return amtRequest>0;
	}


		private boolean isValidPurpose(String purpose) {
		return purpose.length()<=100;
	}


	



	@Override
	public LoanInfo applyLoan(LoanInfo loan) throws LoanException {
		
		validateLoan(loan);
		
		return loanDao.applyLoan(loan);
	}


	@Override
	public String getLoanNo() throws LoanException {
		
		return loanDao.getLoanNo();
	}


	@Override
	public LoanInfo getLoanDetails(LoanInfo loan,HttpSession session) {
		
		try {
			if(validateUser(loan))
			{
			loan= loanDao.getLoanDetails(loan);
			if(loan.getStatus().equals("pending")||session.getAttribute("userId").toString().equals("admin"))
			{
				return loan;
			}
			else
			{
				return null;
				
			}
			}
			else
			{
				return null;
			}
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}


	private boolean validateUser(LoanInfo loan) {
		// TODO Auto-generated method stub
		try {
			String s=loanDao.getLoanUser(loan.getApplno());
			if(!s.equals(""))
			{
				if(loan.getUserId().equals(s)||loan.getUserId().equals("admin"))
					return true;
			}
		} catch (LoanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public void updateLoan(LoanInfo loan) throws LoanException {
		// TODO Auto-generated method stub
		validateUpdateLoan(loan);
		loanDao.updateLoan(loan);
	}


	@Override
	public List<LoanInfo> listAll() throws LoanException, SQLException {
		// TODO Auto-generated method stub
		return loanDao.listAll();
	}


	@Override
	public boolean updateStatus(String status, String applicationNo) throws LoanException {
		// TODO Auto-generated method stub
		return loanDao.updateStatus(status,applicationNo);
		
	}


	


	

	@Override
	public void sanctionLoan(ApprovedLoan sLoan) throws LoanException {
		
		sLoan.setLcd(LocalDate.parse(sLoan.getPsd()).plusYears(sLoan.getLoanterm()).toString());
		sLoan.setTermpayment(sLoan.getAmotsanctioned()*(1+sLoan.getRate()/100)*sLoan.getLoanterm());
		sLoan.setEmi(sLoan.getTermpayment()/(sLoan.getLoanterm()*12));
		if(loanDao.ifLoanExists(sLoan.getApplno()).equals("noRecords"))
				{
		loanDao.sanctionLoan(sLoan);
				}
		else
		{
			loanDao.updateSanctionLoan(sLoan);	
		}
	}


	@Override
	public ApprovedLoan getProcessedLoanDetails(String applno) throws LoanException {
		// TODO Auto-generated method stub
		return loanDao.getProcessedLoanDetails(applno);
	}


	@Override
	public void updateRejectedLoan(String appNo) throws LoanException {
		// TODO Auto-generated method stub
		loanDao.updateRejectedLoan(appNo);
	}

}
