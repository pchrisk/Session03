package edu.uw.ck.account;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;

public final class AccountFactoryImpl implements AccountFactory {
	
	private static final Logger logger = LoggerFactory.getLogger(AccountFactoryImpl.class);

	@Override
	public Account newAccount(final String accountName, 
								final byte[] hashedPassword,
								final int initialBalance) {
		Account acct = null;

		try {

			acct = new AccountImpl(accountName, hashedPassword, initialBalance);
			
			if (logger.isInfoEnabled()) {
				logger.info("Created account: '%s', balance = %d", 
						accountName, initialBalance);
			}

		} catch (AccountException e) {
			final String msg = String.format("Account creation failed for, account '%s, balance = %d", 
					accountName, initialBalance);
			logger.warn(msg, e);
			
		}

		return acct;
	}

}
