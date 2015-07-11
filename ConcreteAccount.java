package edu.uw.ajz.concrete.account;

import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;

public class ConcreteAccount implements Account
{
	private String accountName;
	private byte[] hashedPassword;
	private int initialBalance;
	
	private int balance;
	
	private String fullName;
	private Address address;
	private CreditCard cc;
	private String email;
	private String phoneNumber;
	private AccountManager am;
	
	public ConcreteAccount()
	{
	}
	
	public ConcreteAccount(String accountName, byte[] hashedPassword, int initialBalance)
	{
		this.accountName = accountName;
		this.hashedPassword = hashedPassword;
		this.initialBalance = initialBalance;
		this.balance = initialBalance;
	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	@Override
	public int getBalance() {
		return this.balance;
	}

	@Override
	public CreditCard getCreditCard() {
		return this.cc;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return this.fullName;
	}

	@Override
	public String getName() {
		return this.accountName;
	}

	@Override
	public byte[] getPasswordHash() {
		
		byte[] pw = hashedPassword.clone();
		return pw;
	}

	@Override
	public String getPhone() {
		return this.phoneNumber;
	}

	@Override
	public void reflectOrder(Order order, int executionPrice) {
		try
		{
			int orderAmt = order.valueOfOrder(executionPrice);
			this.setBalance(this.getBalance() + orderAmt);
			this.am.persist(this);
		}
		catch (AccountException ae)
		{
			throw new NullPointerException("Order not reflected");
		}
	}

	@Override
	public void registerAccountManager(AccountManager m) {
		
		this.am = m;
		
	}

	@Override
	public void setAddress(Address address) {
		this.address=address;
		
	}

	@Override
	public void setBalance(int balance) {
		this.balance = balance;
		
	}

	@Override
	public void setCreditCard(CreditCard card) {
		this.cc = card;
		
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
		
	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;
		
	}

	@Override
	public void setName(String acctName) throws AccountException {
		if(acctName.length() >= 8) this.accountName = acctName;	
		else throw new AccountException("User name must be at least 8 characters long.");
	}

	@Override
	public void setPasswordHash(byte[] passwordHash) {
		this.hashedPassword = passwordHash;
		
	}

	@Override
	public void setPhone(String phone) {
		this.phoneNumber = phone;
		
	}

}
