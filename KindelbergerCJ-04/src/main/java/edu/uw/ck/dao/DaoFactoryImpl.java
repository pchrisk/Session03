package edu.uw.ck.dao;

import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;

public final class DaoFactoryImpl implements DaoFactory {

	@Override
	public AccountDao getAccountDao() throws DaoFactoryException {
		
			try {
				return new AccountDaoImpl();
			} catch (AccountException e) {
				throw new DaoFactoryException("AccountDaoImpl failed", e);
			}

		
	}

}
