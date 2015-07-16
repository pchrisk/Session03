package edu.uw.ck.account;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;

public class AccountFactoryImpl implements AccountFactory {

	@Override
	public Account newAccount(String accountName, byte[] hashedPassword,
			int initialBalance) {
		Account acct = null;

		try {

			acct = new AccountImpl(accountName, hashedPassword, initialBalance);

		} catch (AccountException e) {
			
			e.printStackTrace();
		}

		return acct;
	}

}
