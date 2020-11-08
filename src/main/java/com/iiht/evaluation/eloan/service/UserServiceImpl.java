package com.iiht.evaluation.eloan.service;

import java.util.ArrayList;
import java.util.List;

import com.iiht.evaluation.eloan.dao.IUserDao;
import com.iiht.evaluation.eloan.dao.UserDaoImpl;
import com.iiht.evaluation.eloan.model.User;


import exception.LoanException;

public class UserServiceImpl implements IUserService{
	
private IUserDao userDao;
	
	public UserServiceImpl() {
		this.userDao = new UserDaoImpl();
	}

	private void validateUser(User user) throws LoanException{
		List<String> errMsgs = new ArrayList<String>();
		
		if(!isValidUserId(user.getUserid())) {
			errMsgs.add("UserId length cannot be more than 20 characters");
		}
		if(!isValidName(user.getName())) {
			errMsgs.add("Name maximum length is 20");
		}
		if(!isValidPhoneNo(user.getPhoneNo())) {
			errMsgs.add("Phone Number cannot be more than 10 digits");
		}
		if(!isValidEmail(user.getEmail())) {
			errMsgs.add("Email length cannot be more than 30 alphanumeric characters");
		}
		if(!errMsgs.isEmpty()) {
			throw new LoanException(errMsgs.toString());
		}
	}
	
	
	
	private boolean isValidEmail(String email) {
		return email.length()<=30;
	}

	private boolean isValidPhoneNo(String phoneNo) {
		return phoneNo.length()<=10;
	}

	private boolean isValidName(String name) {
		return name.length()<=20;
	}

	private boolean isValidPassword(String password) {
		return password.length()<=20;
	}



	private boolean isValidUserId(String userId) {
		return userId.length()<=20;
	}



	@Override
	public String validate(User user) throws LoanException {
		if(isValidUserId(user.getUserid()) && isValidPassword(user.getPassword()))
			return userDao.validate(user);
		else
			return "error";
			
	}

	@Override
	public User addUser(User user) throws LoanException {
		validateUser(user);
		return userDao.addUser(user);
	}

	@Override
	public boolean userIdExistsAlready(String userid) throws LoanException {
		
		if(userDao.getUserID(userid).equals(userid))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	

}
