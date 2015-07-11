package edu.uw.ck;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;

public class AccountFactoryImpl implements AccountFactory {

	@Override
	public Account newAccount(String accountName, byte[] hashedPassword, int initialBalance) {
		Account acct = null;
		
		try {
//			if(accountName != null && accountName.length() >= 8 && initialBalance >= 100000)  {
				acct = new AccountImpl(accountName, hashedPassword, initialBalance);
//				acct.setName(accountName);
//				acct.setPasswordHash(hashedPassword);
//				acct.setBalance(initialBalance);
//			}
				
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return acct;
	}

}
