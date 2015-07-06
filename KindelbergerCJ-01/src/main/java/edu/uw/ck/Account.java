package edu.uw.ck;

import java.io.Serializable;

import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.order.Order;

public interface Account extends Serializable {
	/**
	 * Gets the account address.
	 * @return the accounts address
	 */
	Address getAddress();
	
	/**
	 * Gets the account balance, in cents.
	 * @return the current balance of the account
	 */
	int getBalance();
	
	/**
	 * Gets the account credit card.
	 * @return the credit card
	 */
	CreditCard getCreditCard();
	
	/**
	 * Gets the email address.
	 * @return email - the email address
	 */
	String getEmail();
	
	/**
	 * Gets the full name of the account holder.
	 * @return the account holders full name
	 */
	String getFullName();
	
	/**
	 * Get the account name.
	 * @return the name of the account
	 */
	String getName();
	
	/**
	 * Gets the hashed password.
	 * @return the hashed password
	 */
	byte[] getPasswordHash();
	
	/**
	 * Gets the phone number.
	 * @return the phone number
	 */
	String getPhone();
	
	/**
	 * Incorporates the effect of an order in the balance.
	 * @param order - the order to be reflected in the account
	 * @param executionPrice - the price the order was executed at
	 */
	void reflectOrder(Order order, int executionPrice);
	
	/**
	 * Sets the account manager responsible for persisting/managing this account.
	 * @param m - the account manager
	 */
	void registerAccountManager(AccountManager m);
	
	/**
	 * Sets the account address.
	 * @param address - the address for the account
	 */
	void setAddress(Address address);
	
	/**
	 * Sets the account balance.
	 * @param balance - the value to set the balance to in cents
	 */
	void setBalance(int balance);
	
	/**
	 * Sets the account credit card.
	 * @param card - the value to be set for the credit card
	 */
	void setCreditCard(CreditCard card);
	
	/**
	 * Sets the account email address.
	 * @param email - the email address
	 */
	void setEmail(String email);
	
	
	/**
	 * Sets the full name of the account holder.
	 * @param fullName - the account holders full name
	 */
	void setFullName(String fullName);
	
	
	/**
	 * Sets the account name.
	 * @param acctName - the value to be set for the account name
	 * @throws AccountException - if the account name is unacceptable
	 */
	void setName(String acctName) throws AccountException;
	
	
	/**
	 * Sets the hashed password.
	 * @param passwordHash - the value to be st for the password hash
	 */
	void setPasswordHash(byte[] passwordHash);
	
	
	/**
	 * Sets the account phone number.
	 * @param phone - value for the account phone number
	 */
	void setPhone(String phone);
	 


}
