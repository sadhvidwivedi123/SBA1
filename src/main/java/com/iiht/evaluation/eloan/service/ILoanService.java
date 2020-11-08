package com.iiht.evaluation.eloan.service;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

import exception.LoanException;

public interface ILoanService {
	
	LoanInfo applyLoan(LoanInfo loan) throws LoanException;
	String getLoanNo() throws LoanException;
	LoanInfo getLoanDetails(LoanInfo loan,HttpSession session);
	void updateLoan(LoanInfo loan) throws LoanException;
	List<LoanInfo> listAll() throws LoanException, SQLException;
	boolean updateStatus(String status, String applicationNo) throws LoanException;
	void sanctionLoan(ApprovedLoan sLoan) throws LoanException;
	ApprovedLoan getProcessedLoanDetails(String applno) throws LoanException;
	void updateRejectedLoan(String string) throws LoanException;


}
