package edu.uw.ck;

import edu.uw.ext.framework.account.AccountException;

public interface AccountManager {
	
	/**
	 * Release any resources used by the AccountManager implementation.
	 * @throws AccountException - if error occurs accessing accounts
	 */
	void close() throws AccountException;
	
	/**
	 * Creates an account.
	 * @param accountName - the name for account to add
	 * @param password - the password used to gain access to the account
	 * @param balance - the initial balance of the account
	 * @return the newly created account
	 * @throws AccountException - if operation fails
	 */
	Account createAccount(String accountName, String password, int balance) throws AccountException; 
		
	/**
	 * Remove the account.
	 * @param accountName - the name of the account to remove
	 * @throws AccountException - if operation fails
	 */
	void deleteAccount(String accountName) throws AccountException; 
	
	/**
	 * Lookup an account based on account name.
	 * @param accountName - the name of the desired account
	 * @return the account if located otherwise null
	 * @throws AccountException - if operation fails
	 */
	Account getAccount(String accountName) throws AccountException;
	
	/**
	 * Used to persist an account. 
	 * @param account - the account to persist
	 * @throws AccountException - if operation fails
	 */
	void persist(Account account) throws AccountException;
	
	/**
	 * Check whether a login is valid.
	 * @param accountName - name of account the password is to be validated for
	 * @param password - password is to be validated
	 * @return true if password is valid for account identified by accountName
	 * @throws AccountException - if error occurs accessing accounts
	 */
	boolean validateLogin(String accountName, String password) throws AccountException; 
	 


}
