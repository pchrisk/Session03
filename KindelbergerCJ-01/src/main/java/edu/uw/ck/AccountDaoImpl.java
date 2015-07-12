package edu.uw.ck;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountFactory;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.dao.AccountDao;

public class AccountDaoImpl implements AccountDao {

	String ACCOUNT_FILES = "target/accounts/";
	String ACCOUNT_FILENAME = "account.bin";
	String ADDRESS_FILENAME = "address.prop";
	String CREDITCARD_FILENAME = "creditcard.prop";

	public AccountDaoImpl() {
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

		File dir = new File(ACCOUNT_FILES + accountName);

		if (dir.exists()) {
			File[] files = dir.listFiles();
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				file.delete();
			}
			dir.delete();
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

		String dirName = ACCOUNT_FILES + acctName;
		File dir = new File(dirName);
		if (dir.exists()) {
			File file = new File(dirName + "/" + ACCOUNT_FILENAME);
			try {
				DataInputStream input = new DataInputStream(
						new FileInputStream(file));

				String aName = input.readUTF();
				int passwordLen = input.readInt();
				byte[] password = new byte[passwordLen];
				input.readFully(password);
				int balance = input.readInt();

				account = accountFactory.newAccount(aName, password, balance);

				String fullName = input.readUTF();
				String email = input.readUTF();
				String phone = input.readUTF();

				account.setFullName(fullName);
				account.setEmail(email);
				account.setPhone(phone);

				input.close();
			} catch (IOException e) {

				e.printStackTrace();
			}

			/**
			 * Read address file
			 */

			try {
				Properties addProp = new Properties();
				File addFile = new File(dirName + "/" + ADDRESS_FILENAME);
				if (addFile.exists()) {
					FileInputStream fileInput = new FileInputStream(addFile);
					addProp.load(fileInput);
					address.setStreetAddress(addProp
							.getProperty("streetAddress"));
					address.setCity(addProp.getProperty("city"));
					address.setState(addProp.getProperty("State"));
					address.setZipCode(addProp.getProperty("zipCode"));
					fileInput.close();
					account.setAddress(address);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			/**
			 * Read credit card file
			 */

			try {
				Properties ccProp = new Properties();
				File ccFile = new File(dirName + "/" + CREDITCARD_FILENAME);
				if (ccFile.exists()) {
					FileInputStream ccInput = new FileInputStream(ccFile);
					ccProp.load(ccInput);

					cc.setType(ccProp.getProperty("type"));
					cc.setIssuer(ccProp.getProperty("issuer"));
					cc.setHolder(ccProp.getProperty("holder"));
					cc.setAccountNumber(ccProp.getProperty("accountNumber"));
					cc.setExpirationDate(ccProp.getProperty("expirationDate"));

					ccInput.close();
					account.setCreditCard(cc);
				}

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
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

		String dirName = ACCOUNT_FILES + account.getName() + "/";
		File dir = new File(dirName);
		deleteAccount(account.getName());
		if (!dir.exists()) {
			dir.mkdir();
		}

		File file = new File(dirName + ACCOUNT_FILENAME);
		try {
			DataOutputStream output = new DataOutputStream(
					new FileOutputStream(file));

			output.writeUTF(account.getName());
			output.writeInt(account.getPasswordHash().length);
			output.write(account.getPasswordHash());
			output.writeInt(account.getBalance());

			if (account.getFullName() != null) {
				output.writeUTF(account.getFullName());
			} else {
				output.writeUTF("");
			}
			if (account.getEmail() != null) {
				output.writeUTF(account.getEmail());
			} else {
				output.writeUTF("");
			}
			if (account.getPhone() != null) {
				output.writeUTF(account.getPhone());
			} else {
				output.writeUTF("");
			}

			output.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/**
		 * Write address file
		 */

		Address address = account.getAddress();
		if (address != null) {
			try {
				Properties addProp = new Properties();
				addProp.setProperty(
						"streetAddress",
						address.getStreetAddress() != null ? address
								.getStreetAddress() : "");
				addProp.setProperty("city",
						address.getCity() != null ? address.getCity() : "");
				addProp.setProperty("State",
						address.getState() != null ? address.getState() : "");
				addProp.setProperty("zipCode",
						address.getZipCode() != null ? address.getZipCode()
								: "");

				File addFile = new File(dirName + ADDRESS_FILENAME);
				FileOutputStream fileOut = new FileOutputStream(addFile);
				addProp.store(fileOut, account.getName() + " Address");
				fileOut.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/**
		 * Write credit card file
		 */

		CreditCard cc = account.getCreditCard();
		if (cc != null) {
			try {
				Properties ccProp = new Properties();

				ccProp.setProperty("type", cc.getType() != null ? cc.getType()
						: "");
				ccProp.setProperty("issuer",
						cc.getIssuer() != null ? cc.getIssuer() : "");
				ccProp.setProperty("holder",
						cc.getHolder() != null ? cc.getHolder() : "");
				ccProp.setProperty("accountNumber",
						cc.getAccountNumber() != null ? cc.getAccountNumber()
								: "");
				ccProp.setProperty("expirationDate",
						cc.getExpirationDate() != null ? cc.getExpirationDate()
								: "");

				File ccFile = new File(dirName + CREDITCARD_FILENAME);
				FileOutputStream fileOut2 = new FileOutputStream(ccFile);
				ccProp.store(fileOut2, account.getName() + " Credit Card");
				fileOut2.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
