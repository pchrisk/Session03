package edu.uw.ck;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
	
	String ACCOUNT_FILES = "target/accounts";
	
	
	
	public AccountDaoImpl() {
		File dir = new File(ACCOUNT_FILES);
		if(!dir.exists()) {
			dir.mkdirs();
		}
	}
	

	@Override
	public void close() throws AccountException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAccount(String accountName) throws AccountException {
		// TODO Auto-generated method stub

	}

	@Override
	public Account getAccount(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() throws AccountException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setAccount(Account account) throws AccountException {
		File dir = new File(ACCOUNT_FILES + "/" + account.getName());
		if(!dir.exists()){
			dir.mkdir();
			try {
				DataOutputStream output = new DataOutputStream(
				        new FileOutputStream("account"));
				output.writeUTF(account.);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		

	}

}
