package com.iiht.evaluation.eloan.service;

import java.util.List;
import com.iiht.evaluation.eloan.model.LoanInfo;

import exception.LoanException;


public interface IAdminService {

	List<LoanInfo> listAll() throws LoanException;
	
}
