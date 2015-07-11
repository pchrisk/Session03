package edu.uw.ck;

import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.AccountManagerFactory;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountManagerFactoryImpl implements AccountManagerFactory {

	@Override
	public AccountManager newAccountManager(AccountDao dao) {
		
		return (new AccountManagerImpl(dao));
	}

}
