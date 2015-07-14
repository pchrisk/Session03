package edu.uw.ck;

import java.io.UnsupportedEncodingException;
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
	}

	@Override
	public void close() throws AccountException {
		dao.close();

	}

	@Override
	public Account createAccount(String accountName, String password,
			int balance) throws AccountException {
		if (dao.getAccount(accountName) != null)
			throw new AccountException("Account already exists");

		byte[] hashedPassword = writeHash(password);
		Account acct = new AccountFactoryImpl().newAccount(accountName,
				hashedPassword, balance);
		acct.registerAccountManager(this);
		persist(acct);
		return acct;
	}

	private byte[] writeHash(String password) {
		MessageDigest md;
		byte[] hashedPassword = null;
		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(password.getBytes("UTF-8"));
			hashedPassword = md.digest();

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hashedPassword;

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
		if (acct == null) {
			return isValid;
		}

		byte[] passwd1 = writeHash(password);
		byte[] passwd2 = acct.getPasswordHash();

		isValid = MessageDigest.isEqual(passwd1, passwd2);

		return isValid;
	}

}
