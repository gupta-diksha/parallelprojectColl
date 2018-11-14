package com.cg.pwa.service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Pattern;


import com.cg.pwa.exception.PaymentAppException;
import com.cg.pwa.dao.IPaymentAppDao;
import com.cg.pwa.dao.PaymentAppDaoImpl;
import com.cg.pwa.dto.Account;
import com.cg.pwa.dto.Customer;
//import com.cg.pwa.exception.PaymentAppException;

public class PaymentAppServiceImpl implements IPaymentAppService {

	IPaymentAppDao pwaDao=null;
	
	
	public PaymentAppServiceImpl() {
		pwaDao=new PaymentAppDaoImpl();
	}


	@Override
	public boolean validateUser(Long uname, String password)
	{
		HashSet<Customer> custSet = pwaDao.getAll();
		//System.out.println(custSet);
		Iterator<Customer> it = custSet.iterator();
		//System.out.println(uname);
		//System.out.println(password);
		boolean flag=false;
		while(it.hasNext())
		{
			System.out.println("hifromwhile");
			Customer cc =it.next();
			if((cc.getMobileNum().equals(uname)) && (cc.getPassword().equals(password)))
			{
				//System.out.println("hifromif");
				flag=true;
			}
			
		}
		return flag;
	}
	
	
	@Override
	public void createAccount(Customer cust) {
		
		pwaDao.createAccount(cust);
		//return cust.getAccount();
	}

	@Override
	public double showBalance(Long mobile) {
		
		return pwaDao.showBalance(mobile);
	}

	@Override
	public double deposit(Long mobile, double depamnt) {
		
		return pwaDao.deposit(mobile, depamnt);
	}

	@Override
	public double withdraw(Long mobile, double wdamnt) {
		
		return pwaDao.withdraw(mobile, wdamnt);
	}

	@Override
	public double fundsTranfer(Long mobile,Long mobileReceiver, double transferAmnt) {
		
		return pwaDao.fundsTranfer(mobile, mobileReceiver, transferAmnt);
	}

	@Override
	public Map<String, LocalDate> printTransactions() {
		
		return null;
	}


	@Override
	public boolean validateMobile(Long mobnum) throws PaymentAppException
	{
		String tempmob= mobnum.toString();
		String mobilePattern="[7-9][0-9]{9}";
		if(Pattern.matches(mobilePattern,tempmob))
			return true;
		else
			return false;
	}


	@Override
	public boolean validateName(String name) throws PaymentAppException
	{
		
		String namePattern= "[A-Z][a-z]+";
		if (Pattern.matches(namePattern,name))
		{
			return true;
		}
		else {
			throw new PaymentAppException("Invalid input"+"Only characters are allowed and should start with capital letter for eg Diksha!");
		}
		
		
	}


	@Override
	public boolean validateMail(String mail)throws PaymentAppException
	{
		//String mailPattern="^[(a-zA-Z-0-9-\\\\_\\\\+\\\\.)]+@[(a-z-A-z)]+\\\\.[(a-zA-z)]{2,3}$";
		//if(Pattern.matches(mailPattern,mail))
			return true;
		//else
			//throw new PaymentAppException("Invalid mail-Id"+"example: user@sample.com");
	}


	@Override
	public boolean validateAmount(double amnt)throws PaymentAppException {
		
		if(amnt>0)
			return true;
		else
			throw new PaymentAppException("Invalid amount!! Cannot be negative!");
	}


	@Override
	public boolean validateBalanceLimit(double wdAmnt)throws PaymentAppException {
		
		return true;
	}


	@Override
	public boolean validatePassword(String pass) {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public HashSet<Account> getAccount(Long mobile) {
		HashSet<Account> accSet=pwaDao.getAccount(mobile);
		
	
		return accSet;
	}


	@Override
	public HashSet<Account> getAccountByMobile(Long mobile) {
		
		return pwaDao.getAccountByMobile(mobile);
	}
	

	
}
