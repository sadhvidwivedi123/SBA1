package com.iiht.evaluation.eloan.model;

import java.sql.Date;
import java.time.LocalDate;

public class LoanInfo {
	private String applno;
	 private String purpose;
	 private Double amtrequest;
	 private LocalDate doa;
	 private String bstructure;
	 private String bindicator;
	 private String tindicator;
	 private String address;
	 private String email;
	 private String mobile;
	 private String status;
	 private String userId;
	
	

	 @Override
	public String toString() {
		return "LoanInfo [applno=" + applno + ", purpose=" + purpose + ", amtrequest=" + amtrequest + ", doa=" + doa
				+ ", bstructure=" + bstructure + ", bindicator=" + bindicator + ", tindicator=" + tindicator
				+ ", address=" + address + ", email=" + email + ", mobile=" + mobile + ", status=" + status
				+ ", userId=" + userId + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTindicator() {
		return tindicator;
	}

	public void setTindicator(String tindicator) {
		this.tindicator = tindicator;
	}

	public LoanInfo() {
		 
	 }
	 public LoanInfo(String applno, String purpose, Double amtrequest, LocalDate doa, String bstructure, String bindicator,
			String address, String email, String mobile,String status) {
		super();
		this.applno = applno;
		this.purpose = purpose;
		this.amtrequest = amtrequest;
		this.doa = doa;
		this.bstructure = bstructure;
		this.bindicator = bindicator;
		this.address = address;
		this.email = email;
		this.mobile = mobile;
		this.status=status;
	}

	public String getApplno() {
		return applno;
	}
	public void setApplno(String applno) {
		this.applno = applno;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public Double getAmtrequest() {
		return amtrequest;
	}
	public void setAmtrequest(Double amtrequest) {
		this.amtrequest = amtrequest;
	}
	public LocalDate getDoa() {
		return doa;
	}
	public void setDoa(LocalDate localDate) {
		this.doa = localDate;
	}
	public String getBstructure() {
		return bstructure;
	}
	public void setBstructure(String bstructure) {
		this.bstructure = bstructure;
	}
	public String getBindicator() {
		return bindicator;
	}
	public void setBindicator(String bindicator) {
		this.bindicator = bindicator;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public void setStatus(String status) {
		this.status=status;
	}
	public String getStatus() {
		return status;
	}

}
