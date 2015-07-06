package edu.uw.ck;

import edu.uw.ext.framework.dao.AccountDao;

public interface AccountManagerFactory {
	
	/**
	 * Instantiates a new account manager instance.
	 * @param dao - the data access object to be used by the account manager
	 * @return a newly instantiated account manager
	 */
	AccountManager newAccountManager(AccountDao dao);

	
}
