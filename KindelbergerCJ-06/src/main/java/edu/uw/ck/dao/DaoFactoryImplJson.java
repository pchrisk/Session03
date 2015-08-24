package edu.uw.ck.dao;

import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;

public class DaoFactoryImplJson implements DaoFactory {

	@Override
	public AccountDao getAccountDao() throws DaoFactoryException {
		
		
		return (new AccountDaoImplJson());
	}

}
