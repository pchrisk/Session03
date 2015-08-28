package edu.uw.ck.account;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountManagerImpl implements AccountManager {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountManagerImpl.class);

	private static final String ENCODING = "UTF-8";
	private static final String ALGORITHM = "SHA1";
	
	private AccountDao dao = null;
	private AccountFactory accountFactory;

	public AccountManagerImpl(AccountDao dao) {
		this.dao = dao;
		this.accountFactory = new AccountFactoryImpl();
		
	}

	@Override
	public void close() throws AccountException {
		dao.close();

	}

	@Override
	public Account createAccount(String accountName, String password,
			int balance) throws AccountException {
		if (dao.getAccount(accountName) != null) {
			throw new AccountException("Account already exists");
		} else {
			byte[] hashedPassword = writeHash(password);
			logger.debug("Atempting to create Account: " + accountName + password + balance + " ****HASH = " + hashedPassword);
			Account acct = new AccountFactoryImpl().newAccount(accountName,
					hashedPassword, balance);
			acct.registerAccountManager(this);
			persist(acct);
			return acct;
		}
			

		
	}

	private byte[] writeHash(String password) throws AccountException {
		MessageDigest md;
		byte[] hashedPassword = null;
		try {
			md = MessageDigest.getInstance(ALGORITHM);
			md.update(password.getBytes(ENCODING));
			hashedPassword = md.digest();

		} catch (NoSuchAlgorithmException e) {
			throw new AccountException("Unable to find hash algorithm", e);
		} catch (UnsupportedEncodingException e) {
			throw new AccountException(String.format("Unable to find character encoding: %s", ENCODING), e);
		}

		return hashedPassword;

	}

	@Override
	public void deleteAccount(String accountName) throws AccountException {
		final Account acct = dao.getAccount(accountName);
		if (acct != null) {
			dao.deleteAccount(accountName);
		}
	}

	@Override
	public Account getAccount(String accountName) throws AccountException {
		Account acct = dao.getAccount(accountName);
		if (acct != null) {
			acct.registerAccountManager(this);
		}
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
