package edu.uw.ck;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountManagerImpl implements AccountManager {
	
	private AccountDao dao = null;
	
	public AccountManagerImpl(AccountDao dao) {
		this.dao = dao;
//		AccountFactoryImpl acct = new AccountFactoryImpl();
	}

	@Override
	public void close() throws AccountException {
		dao.close();

	}

	@Override
	public Account createAccount(String accountName, String password, int balance)
			throws AccountException {
		byte[] hashedPassword = writeHash(password);
		System.out.println(password);
		System.out.println(hashedPassword);
		
		Account acct = new AccountFactoryImpl().newAccount(accountName, hashedPassword, balance);
		
		return acct;
	}

	private byte[] writeHash(String password) {
		MessageDigest md;
		byte[] hashedPassword = null;
		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(password.getBytes());
			hashedPassword = md.digest();		
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return hashedPassword;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccount(String accountName) throws AccountException {
		dao.deleteAccount(accountName);

	}

	@Override
	public Account getAccount(String accountName) throws AccountException {
		Account acct = dao.getAccount(accountName);
		return acct;
	}

	@Override
	public void persist(Account account) throws AccountException {
		dao.setAccount(account);

	}

	@Override
	public boolean validateLogin(String accountName, String password)
			throws AccountException {
		Boolean isValid = false;
		
		
		Account acct = getAccount(accountName);
		byte[] passwd1 = writeHash(password);
		byte[] passwd2 = acct.getPasswordHash();
		
		if (passwd1.equals(passwd2)) {
			isValid = true;
		}
			
		
		return isValid;
	}

}
