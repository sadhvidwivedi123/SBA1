package com.iiht.evaluation.eloan.model;

public class ApprovedLoan {
	String applno;
	Double amotsanctioned;
	Integer loanterm;
	String psd;
	String lcd;
	Double emi;
	Double termpayment;
	Double rate;
	String comments;
	public Double getRate() {
		return rate;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Override
	public String toString() {
		return "ApprovedLoan [applno=" + applno + ", amotsanctioned=" + amotsanctioned + ", loanterm=" + loanterm
				+ ", psd=" + psd + ", lcd=" + lcd + ", emi=" + emi + ", termpayment=" + termpayment + ", rate=" + rate
				+ ", comments=" + comments + "]";
	}
	public ApprovedLoan()
	{
		
	}
	public ApprovedLoan(String applno, Double amotsanctioned, Integer loanterm, String psd, String lcd, Double emi,
			Double termpayment) {
		super();
		this.applno = applno;
		this.amotsanctioned = amotsanctioned;
		this.loanterm = loanterm;
		this.psd = psd;
		this.lcd = lcd;
		this.emi = emi;
		this.termpayment = termpayment;
	}
	public String getApplno() {
		return applno;
	}
	public void setApplno(String applno) {
		this.applno = applno;
	}
	public Double getAmotsanctioned() {
		return amotsanctioned;
	}
	public void setAmotsanctioned(Double amotsanctioned) {
		this.amotsanctioned = amotsanctioned;
	}
	public Integer getLoanterm() {
		return loanterm;
	}
	public void setLoanterm(Integer loanterm) {
		this.loanterm = loanterm;
	}
	public String getPsd() {
		return psd;
	}
	public void setPsd(String psd) {
		this.psd = psd;
	}
	public String getLcd() {
		return lcd;
	}
	public void setLcd(String lcd) {
		this.lcd = lcd;
	}
	public Double getEmi() {
		return emi;
	}
	public void setEmi(Double emi) {
		this.emi = emi;
	}
	public Double getTermpayment() {
		return termpayment;
	}
	public void setTermpayment(Double termpayment) {
		this.termpayment = termpayment;
	} 
	

}
