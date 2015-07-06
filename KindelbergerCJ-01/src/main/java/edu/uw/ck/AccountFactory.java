package edu.uw.ck;

public interface AccountFactory {
	
	/**
	 * Instantiates a new account instance.
	 * @param accountName - the account name
	 * @param hashedPassword - the password hash
	 * @param initialBalance - the balance
	 * @return the newly instantiated account, or null if unable to instantiate the account
	 */
	Account newAccount(String accountName, byte[] hashedPassword, int initialBalance);
	 

}
