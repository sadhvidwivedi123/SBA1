package com.iiht.evaluation.eloan.dao;

import com.iiht.evaluation.eloan.model.User;

import exception.LoanException;

public interface IUserDao {
	String validate(User user) throws LoanException;
	User addUser(User user) throws LoanException;
	String getUserID(String userid) throws LoanException;

}
