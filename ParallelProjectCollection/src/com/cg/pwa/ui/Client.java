package com.cg.pwa.ui;

import java.util.HashSet;
import java.util.Scanner;

import com.cg.pwa.dto.Account;
import com.cg.pwa.dto.Customer;
import com.cg.pwa.exception.PaymentAppException;
import com.cg.pwa.service.*;

public class Client 
{

	static IPaymentAppService pwaService=null;
	static Scanner sc = null;

	public static void main(String[] args) 
	{
		pwaService= new PaymentAppServiceImpl();
		sc= new Scanner(System.in);
		int choice1;
		String choice2;
		long uname;
		String password;
		boolean flag;
		System.out.println("Welcome to Payment Wallet App!!");
		do
		{
			System.out.println("1.Login");
			System.out.println("2.Singup");
			System.out.println("3.Exit");
			System.out.print("Enter your choice:");
			choice1=sc.nextInt();

			switch(choice1)
			{
			case 1: flag=true;
			System.out.print("Enter your Username/Mobile Number:");
			uname=sc.nextLong();
			System.out.println("Enter your password:");
			password=sc.next();
				if(pwaService.validateUser(uname,password))
				{
					System.out.println("hi1");
					while(flag==true)
					{
						System.out.println("What do you want to do?");
						System.out.println("A.Check Balance");
						System.out.println("B.Deposit");
						System.out.println("C.Withdraw");
						System.out.println("D.Transfer Funds");
						System.out.println("E.Print transactions");
						System.out.println("Enter your choice:");
						choice2=sc.next();

						switch(choice2)
						{
						case "A":checkBalance(uname);break;
						case "B": deposit(uname);break;
						case "C": withdraw(uname);break;
						case "D": transferFund(uname);break;
						case "E": printTransactions();break;
						default:flag=false;
						}

					}
				}
			
			break;
			case 2: createAccount();break;
			case 3: System.exit(0);
			}

		}while(true);

	}

	private static void createAccount()
	{
		long mobnum;
		String name,pass;
		HashSet<Account> tempset,tempset2;
		System.out.println("Enter your mobile number:");
		mobnum=sc.nextLong();
		try
		{
			if(pwaService.validateMobile(mobnum))
			{
				System.out.println("Enter your name:");
				name=sc.next();

				if(pwaService.validateName(name))
				{
				
						System.out.println("Enter the password:");
						pass=sc.next();
						if(pwaService.validatePassword(pass))
						{
							tempset=pwaService.getAccountByMobile(mobnum);
							//System.out.println(tempset);
							
							
							Customer cusobj = new Customer(mobnum,name,0.0,pass,tempset);
							pwaService.createAccount(cusobj);
							
							System.out.println(name+"your account has been created successfully!"+cusobj);
						}
					}
				}
			
		}catch (PaymentAppException e) {

			e.printStackTrace();
		}

	}

	private static void printTransactions() 
	{


	}

	private static void transferFund(Long mobile) 
	{
		Long mobileReceiver;
		double transferAmnt;
		System.out.println("Enter the Mobile number you wish to transfer money to:");
		mobileReceiver=sc.nextLong();
		try
		{
		if(pwaService.validateMobile(mobileReceiver))
		{
			System.out.println("Enter the amount you wish to transfer:");
			transferAmnt=sc.nextDouble();
			if(pwaService.validateAmount(transferAmnt))
			{
				pwaService.fundsTranfer(mobile, mobileReceiver, transferAmnt);
			}
		}
		}catch(PaymentAppException e)
		{
			e.printStackTrace();
		}

	}

	private static void withdraw(Long mobile) 
	{
		double wdAmnt,updatedBal;
		System.out.println("Enter the amount you wish to deduct from wallet:");
		wdAmnt=sc.nextDouble();
		try
		{
		if(pwaService.validateAmount(wdAmnt))
		{
			if(pwaService.validateBalanceLimit(wdAmnt))
			{
				updatedBal=pwaService.withdraw(mobile, wdAmnt);
				System.out.println("Your updated balance is:"+updatedBal);
			}
		}
		}catch(PaymentAppException e)
		{
			e.printStackTrace();
		}

	}

	private static void deposit(Long mobile)
	{
		double depAmnt,updatedBal;
		System.out.println("Enter the amount to be deposited into your wallet:");
		depAmnt=sc.nextDouble();
		try
		{
			if(pwaService.validateAmount(depAmnt))
			{
				updatedBal= pwaService.deposit(mobile, depAmnt);
				System.out.println("Your updated balance is:"+updatedBal);
			}
		}catch(PaymentAppException e)
		{
			e.printStackTrace();
		}

	}

	private static void checkBalance(Long mobile) 
	{
		double bal;
		bal=pwaService.showBalance(mobile);
		System.out.println("The available balance for"+mobile+" is: "+bal);

	}

}
