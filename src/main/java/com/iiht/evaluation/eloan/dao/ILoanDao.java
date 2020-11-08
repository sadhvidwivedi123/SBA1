package com.iiht.evaluation.eloan.dao;

import java.sql.SQLException;
import java.util.List;

import com.iiht.evaluation.eloan.model.ApprovedLoan;
import com.iiht.evaluation.eloan.model.LoanInfo;

import exception.LoanException;

public interface ILoanDao {
	
	LoanInfo applyLoan(LoanInfo loan) throws LoanException;
	String getLoanNo() throws LoanException;
	LoanInfo getLoanDetails(LoanInfo loan) throws LoanException;
	void updateLoan(LoanInfo loan) throws LoanException;
	String getLoanUser(String applno) throws LoanException;
	public List<LoanInfo> listAll() throws LoanException, SQLException;
	boolean updateStatus(String status, String applicationNo) throws LoanException;
	void sanctionLoan(ApprovedLoan sLoan) throws LoanException;
	ApprovedLoan getProcessedLoanDetails(String applno) throws LoanException;
	void updateRejectedLoan(String appNo) throws LoanException;
	String ifLoanExists(String appNo) throws LoanException;
	void updateSanctionLoan(ApprovedLoan sLoan) throws LoanException;

}
