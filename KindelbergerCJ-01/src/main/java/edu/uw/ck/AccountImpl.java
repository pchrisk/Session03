package edu.uw.ck;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.Address;
import edu.uw.ext.framework.account.CreditCard;
import edu.uw.ext.framework.order.Order;

public class AccountImpl implements Account {

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

	}

	@Override
	public void registerAccountManager(AccountManager m) {
		// TODO Auto-generated method stub

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
		this.acctName = acctName;

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
