package edu.uw.ck;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {
	
	String ACCOUNT_FILES = "target/accounts/";
	
	
	
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
		System.out.println("******* Inside DeleteAccount ********");
		File dir = new File(ACCOUNT_FILES + accountName);
		System.out.println(dir.getAbsolutePath());
		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				System.out.println(file.getAbsolutePath());
				file.delete();
			}
			dir.delete();
		}

	}

	@Override
	public Account getAccount(String accountName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() throws AccountException {
		File dir = new File(ACCOUNT_FILES);
		if (!dir.isDirectory()) {
			try {
				throw new IOException("Not a directory " + dir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			if (file.isDirectory()) {
				deleteAccount(file.getName());
				// deleteDir(file);
			} else {
				boolean deleted = file.delete();
				if (!deleted) {
					try {
						throw new IOException("Unable to delete file" + file);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}

		dir.delete();
		  

	}

	@Override
	public void setAccount(Account account) throws AccountException {
		
		// write account.bin
		// write address.txt
		// write creditcard.txt
		
		
		String dirName =  ACCOUNT_FILES + account.getName();
		File dir = new File(dirName);
		if(!dir.exists()){
			dir.mkdir();
		}
		
		File file = new File(dirName +"/" + "account.bin");
		try {
			DataOutputStream output = new DataOutputStream(
			        new FileOutputStream(file));
			
			output.writeUTF(account.getName());
//			output.write
			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
