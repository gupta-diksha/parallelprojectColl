package com.cg.pwa.dto;

import java.util.HashSet;

public class Customer {

	private Long mobileNum;
	private String custName;
	//private String custEmail;
	private double accBalance;
	
	private String password;
	private HashSet<Account> account;
	

	public Customer() {
		
	}

	
	

	
	
	
	public Customer(Long mobileNum, String custName, double accBalance, String password, HashSet<Account> account) {
		super();
		this.mobileNum = mobileNum;
		this.custName = custName;
		this.accBalance = accBalance;
		this.password = password;
		this.account = account;
	}







	@Override
	public String toString() {
		return "Customer [mobileNum=" + mobileNum + ", custName=" + custName + ", accBalance=" + accBalance
				+ ", password=" + password + ", account=" + account + "]";
	}







	public HashSet<Account> getAccount() {
		return account;
	}
	public void setAccount(HashSet<Account> account) {
		this.account = account;
	}




	public Long getMobileNum() {
		return mobileNum;
	}
	public void setMobileNum(Long mobileNum) {
		this.mobileNum = mobileNum;
	}

	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}

	/*public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}*/

	public double getAccBalance() {
		return accBalance;
	}
	public void setAccBalance(double accBalance) {
		this.accBalance = accBalance;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}



}
