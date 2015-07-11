package edu.uw.ajz.concrete.account;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.uw.ajz.concrete.dao.ConcreteAccountDao;
import edu.uw.ajz.concrete.dao.ConcreteDaoFactory;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;

public class ConcreteAccountManager implements AccountManager 
{
	
	private AccountDao dao;
	MessageDigest ga;
	
	
	public ConcreteAccountManager()
	{
	}
	
	public ConcreteAccountManager(AccountDao dao)
	{
		this.dao = dao;
	}

	@Override
	public void close() throws AccountException {
		// TODO Does account manager need a close function?
	}

	@Override
	public Account createAccount(String arg0, String arg1, int arg2)
			throws AccountException {
		AccountFactory af = new ConcreteAccountFactory();
		
		if(this.getAccount(arg0) != null) throw new AccountException("Account Already Exists.");
		
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] hashedPassword = md.digest(arg1.getBytes("UTF-8"));
			Account newAcct = af.newAccount(arg0, hashedPassword, arg2);
			
			//check if account exists
			this.dao.setAccount(newAcct);
			
			
			return newAcct;
		}
		catch (NoSuchAlgorithmException|UnsupportedEncodingException e)
		{
			throw new AccountException("Error in password encryption." + e.toString());
		}
		
	}

	@Override
	public void deleteAccount(String arg0) throws AccountException {
		
		this.dao.deleteAccount(arg0);
		
	}

	@Override
	public Account getAccount(String arg0) throws AccountException {
		Account acct = dao.getAccount(arg0);
		return acct;
	}

	@Override
	public void persist(Account arg0) throws AccountException {
		
		this.dao.setAccount(arg0);
	}

	@Override
	public boolean validateLogin(String arg0, String arg1)
			throws AccountException {
		Account acct = this.getAccount(arg0);
		if(acct==null) return false;
		
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA");
			byte[] pwCompareTo = md.digest(arg1.getBytes("UTF-8"));
			boolean truth = md.isEqual(pwCompareTo, acct.getPasswordHash());

			//if(acct.getPasswordHash().isEqual(hashedPassword)) return true;
			//else return false;
			
			return truth;
			
		}
		catch (NoSuchAlgorithmException|UnsupportedEncodingException e)
		{
			throw new AccountException("Error in password encryption." + e.toString());
		}
		
	}

}
