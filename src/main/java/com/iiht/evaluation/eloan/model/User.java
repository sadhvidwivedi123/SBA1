package com.iiht.evaluation.eloan.model;

public class User {
	
	private String userId;
	private String password;
	private String name;
	private String phoneNo;
	private String email;
	private String role;
	
	public User()
	{
		
	}
	public User(String userid, String password, String name, String phone_no, String email, String role) {
		super();
		this.userId = userid;
		this.password = password;
		this.phoneNo = phone_no;
		this.email = email;
		this.role = role;
	}
	
	public String getUserid() {
		return userId;
	}
	public void setUserid(String userid) {
		this.userId = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phone_no) {
		this.phoneNo = phone_no;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
