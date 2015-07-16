package edu.uw.ck.account;

import java.util.logging.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.expression.AccessException;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.order.Order;

public class AccountImpl implements Account {

	private static org.slf4j.Logger logger = LoggerFactory
			.getLogger(AccountImpl.class);

	private Address address;
	private int balance;
	private CreditCard card;
	private String email;
	private String fullName;
	private String acctName;
	private byte[] passwordHash;
	private String phone;
	private Order order;
	private int executionPrice;
	private AccountManager acctMgr;
	
	public AccountImpl(){
		
	}

	public AccountImpl(String accountName, byte[] hashedPassword,
			int initialBalance) throws AccountException {

		if (accountName != null && accountName.length() >= 8
				&& initialBalance >= 100000) {
			setName(accountName);
			setPasswordHash(hashedPassword);
			setBalance(initialBalance);
		} else {
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
		this.order = order;
		this.executionPrice = executionPrice;
		this.setBalance(this.getBalance() + this.executionPrice);
		try {
			this.acctMgr.persist(this);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void registerAccountManager(AccountManager m) {
		if (this.acctMgr != null) {
			this.acctMgr = m;
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
