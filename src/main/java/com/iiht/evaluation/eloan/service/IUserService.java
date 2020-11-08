package com.iiht.evaluation.eloan.service;

import com.iiht.evaluation.eloan.model.User;

import exception.LoanException;

public interface IUserService {
	
	String validate(User User) throws LoanException;

	User addUser(User user) throws LoanException;

	boolean userIdExistsAlready(String userid) throws LoanException;

}
