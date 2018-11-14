package com.cg.pwa.dao;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import com.cg.pwa.util.CollectionUtilAccount;
import com.cg.pwa.util.CollectionUtilCustomer;
import com.cg.pwa.dto.Account;
import com.cg.pwa.dto.Customer;
import com.cg.pwa.exception.PaymentAppException;

public class PaymentAppDaoImpl  implements IPaymentAppDao{

	@Override
	public void createAccount(Customer cust) 
	{
		
		
		
		HashSet<Account> tempset= new HashSet<Account>();
		System.out.println(cust.getMobileNum());
		
				tempset=getAccountByMobile(cust.getMobileNum());
				//System.out.println(tempset);
				cust.setAccount(tempset);
				CollectionUtilCustomer.addCustomer(cust);
				
			
			
			
		
		
				
				
	}

	@Override
	public double showBalance(Long mobile) 
	{
		HashSet<Customer> tempset = CollectionUtilCustomer.getAllCustomers();
		double currbalnc=0;
		Iterator<Customer> it = tempset.iterator();
		boolean flag=false;
		while(it.hasNext())
		{
			Customer custobj =it.next();
			if((custobj.getMobileNum()==mobile))
			{
				currbalnc=custobj.getAccBalance();
			}
			
		}
		return currbalnc;
		
		
	}

	@Override
	public double deposit(Long mobile, double depamnt) 
	{
		double updatedAccB=0, updatedCusB=0;
		HashSet<Account> accSet= CollectionUtilAccount.getAccount();
		HashSet<Customer> cusSet = CollectionUtilCustomer.getAllCustomers();
		Iterator<Account> accit = accSet.iterator();
		Iterator<Customer> cusit = cusSet.iterator();
		while(cusit.hasNext())
		{
			System.out.println("DEposit while");
			Customer cusObj = cusit.next();
			while(accit.hasNext())
			{
			Account accObj=accit.next();
			
			if(accObj.getMobileNumber().equals(cusObj.getMobileNum()))
			{
				System.out.println("in if of deposit");
				updatedAccB=accObj.getBalance()-depamnt;
				accObj.setBalance(updatedAccB);
				updatedCusB=cusObj.getAccBalance()+depamnt;
				cusObj.setAccBalance(updatedCusB);
				break;
			}
		}
		}
		return updatedCusB;
	}

	@Override
	public double withdraw(Long mobile, double wdamnt)
	{
		double updatedBal=0;
		HashSet<Customer> custSet= CollectionUtilCustomer.getAllCustomers();
		Iterator<Customer> cusit = custSet.iterator();
		while(cusit.hasNext())
		{
			Customer cusObj = cusit.next();
			if(cusObj.getMobileNum().equals(mobile) )
			{
				updatedBal=cusObj.getAccBalance()-wdamnt;
				cusObj.setAccBalance(updatedBal);
			}
		}
			return updatedBal;
		
	}

	@Override
	public double fundsTranfer(Long mobile,Long mobileReceiver, double transferAmnt) 
	{
		double updatedBal;
		HashSet<Customer> custSet= CollectionUtilCustomer.getAllCustomers();
		Iterator<Customer> cusit = custSet.iterator();
		while(cusit.hasNext())
		{
			Customer cusObj = cusit.next();
			if(cusObj.getMobileNum().equals(mobileReceiver) )
			{
				updatedBal=cusObj.getAccBalance()+transferAmnt;
				cusObj.setAccBalance(updatedBal);
			}
		}
		return withdraw(mobile,transferAmnt); 
		
		
		
	}

	@Override
	public Map<String, LocalDate> printTransactions() {
		
		return null;
	}

	@Override
	public HashSet<Customer> getAll() {
		HashSet<Customer> tempset=CollectionUtilCustomer.getAllCustomers();
		
		return tempset;
		
	}

	@Override
	public HashSet<Account> getAccount(Long mobile) {
		// TODO Auto-generated method stub
		return CollectionUtilAccount.getAccount();
	}

	@Override
	public HashSet<Account> getAccountByMobile(Long mobile) {
		
		HashSet<Account> accSet= CollectionUtilAccount.getAccount();
		HashSet<Account> tempset= new HashSet<Account>();
		Iterator<Account> accit = accSet.iterator();
		while(accit.hasNext()) {
			//System.out.println("hi from while getaccountby mobile");
			Account acc = accit.next();
			//System.out.println("Account mobile "+acc.getMobileNumber());
			//System.out.println("Mobile sent by service "+mobile);
			if(mobile.equals(acc.getMobileNumber()) ){
				//System.out.println("hi from if getaccountby mobile");
				tempset.add(acc);
				
				
			}
			}
		//System.out.println(tempset);
		return tempset;
	}

}
