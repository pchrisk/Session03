package edu.uw.ck;

import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;

public class DaoFactoryImpl implements DaoFactory {

	@Override
	public AccountDao getAccountDao() throws DaoFactoryException {
		new AccountDaoImpl();
		
		return null;
	}

}
