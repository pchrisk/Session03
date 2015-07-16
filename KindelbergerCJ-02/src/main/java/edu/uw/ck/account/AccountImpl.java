package edu.uw.ck.account;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.AccessException;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.order.Order;

public class AccountImpl implements Account {

	private static Logger logger = LoggerFactory.getLogger(AccountImpl.class);
	
	private static final int MIN_ACCT_LEN = 8;
	private static final int MIN_ACCT_BALANCE = 100000;

	private Address address;
	private int balance;
	private CreditCard card;
	private String email;
	private String fullName;
	private String acctName;
	private byte[] passwordHash;
	private String phone;
	private AccountManager acctMgr;
	
	public AccountImpl(){
		
	}

	public AccountImpl(String accountName, byte[] hashedPassword,
			int initialBalance) throws AccountException {

		if (initialBalance >= MIN_ACCT_BALANCE) {
			setName(accountName);
			setPasswordHash(hashedPassword);
			setBalance(initialBalance);
		} else {
			final String msg = String.format("Account creation failed for account '%s', minimum balance not met, %d", 
					accountName, initialBalance); 
			logger.warn(msg);
			throw new AccountException();
		}

	}

	@Override
	public Address getAddress() {
		return this.address;
	}

	@Override
	public int getBalance() {
		return this.balance;
	}

	@Override
	public CreditCard getCreditCard() {
		return this.card;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	@Override
	public String getFullName() {
		return this.fullName;
	}

	@Override
	public String getName() {
		return this.acctName;
	}

	@Override
	public byte[] getPasswordHash() {
		return this.passwordHash;
	}

	@Override
	public String getPhone() {
		return this.phone;
	}

	@Override
	public void reflectOrder(Order order, int executionPrice) {
		
		
		try {
			balance += order.valueOfOrder(executionPrice);
			if (acctMgr != null){
				acctMgr.persist(this);
			} else {
				logger.error("Account manager has not been set.", new Exception());
			}
		} catch (AccountException e) {
			logger.error(String.format("failed to persist account %s after adjusting price, %d", acctName, executionPrice));
		}

	}

	@Override
	public void registerAccountManager(AccountManager m) {
		if (this.acctMgr == null) {
			this.acctMgr = m;
		} else {
			logger.info("Account manager was already set.");
		}

	}

	@Override
	public void setAddress(Address address) {
		this.address = address;

	}

	@Override
	public void setBalance(int balance) {
		this.balance = balance;

	}

	@Override
	public void setCreditCard(CreditCard card) {
		this.card = card;

	}

	@Override
	public void setEmail(String email) {
		this.email = email;

	}

	@Override
	public void setFullName(String fullName) {
		this.fullName = fullName;

	}

	@Override
	public void setName(String acctName) throws AccountException {

		if (acctName != null && acctName.length() >= 8) {
			this.acctName = acctName;
		} else {
			throw new AccountException(acctName
					+ " does not meet the requirements for an account name.");
		}

	}

	@Override
	public void setPasswordHash(byte[] passwordHash) {
		this.passwordHash = passwordHash;

	}

	@Override
	public void setPhone(String phone) {
		this.phone = phone;

	}

}
