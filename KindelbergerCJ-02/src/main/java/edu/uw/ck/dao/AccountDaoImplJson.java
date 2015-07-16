package edu.uw.ck.dao;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.uw.ck.account.AccountFactoryImpl;
import edu.uw.ck.account.AccountImpl;
import edu.uw.ck.account.AddressImpl;
import edu.uw.ck.account.CreditCardImpl;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.dao.AccountDao;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class AccountDaoImplJson implements AccountDao {

	String ACCOUNT_FILES = "target/accounts/";
	String ACCOUNT_FILENAME = "account.bin";
	String ADDRESS_FILENAME = "address.prop";
	String CREDITCARD_FILENAME = "creditcard.prop";
	
	ObjectMapper mapper = new ObjectMapper();

	public AccountDaoImplJson() {
		File dir = new File(ACCOUNT_FILES);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	@Override
	public void close() throws AccountException {

	}

	@Override
	public void deleteAccount(String accountName) throws AccountException {

		File file = new File(ACCOUNT_FILES, accountName + ".json");

		if (file.exists()) {
			file.delete();
		}
	}

	@Override
	public Account getAccount(String accountName) {

		String acctName = accountName;

		AccountFactory accountFactory = new AccountFactoryImpl();
		Account account = null;
		Address address = new AddressImpl();
		CreditCard cc = new CreditCardImpl();

		/**
		 * Read Account file
		 */

		String dirName = ACCOUNT_FILES;
		File dir = new File(dirName);
		if (dir.exists()) {
			File file = new File(dirName, acctName + ".json");
			
			SimpleModule module = new SimpleModule();
			module.addAbstractTypeMapping(Account.class, AccountImpl.class);
			module.addAbstractTypeMapping(Address.class, AddressImpl.class);
			module.addAbstractTypeMapping(CreditCard.class, CreditCardImpl.class);
			
			mapper.registerModule(module);
			
			try {
				account = mapper.readValue(file, AccountImpl.class);
			} catch (JsonParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else {
			return null;
		}

		return account;
	}

	@Override
	public void reset() throws AccountException {
		File dir = new File(ACCOUNT_FILES);
		if (!dir.isDirectory()) {
			try {
				throw new IOException("Not a directory " + dir);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			File file = files[i];

			if (file.isDirectory()) {
				deleteAccount(file.getName());
			} else {
				boolean deleted = file.delete();
				if (!deleted) {
					try {
						throw new IOException("Unable to delete file" + file);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	@Override
	public void setAccount(Account account) throws AccountException {

		/**
		 * Write Account file
		 */

		String acctName = account.getName();
		
		String dirName = ACCOUNT_FILES;
		
		deleteAccount(account.getName());
		
		File file = new File(dirName + acctName + ".json");
		
		try {
			mapper.writeValue(file, account);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			

	}

}
