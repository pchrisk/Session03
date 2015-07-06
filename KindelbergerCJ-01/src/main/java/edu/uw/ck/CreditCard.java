package edu.uw.ck;

import java.io.Serializable;

public interface CreditCard extends Serializable {
	
	/**
	 * Gets the card account number.
	 * @return the account number
	 */
	String getAccountNumber();
	
	
	/**
	 * Gets the card expiration date.
	 * @return the expiration date
	 */
	String getExpirationDate();
	
	
	/**
	 * Gets the card holder's name.
	 * @return the card holders name
	 */
	String getHolder();
	
	
	/**
	 * Gets the card issuer.
	 * @return the card issuer
	 */
	String getIssuer();
	
	
	/**
	 * Gets the card type.
	 * @return the card type
	 */
	String getType();
	
	
	/**
	 * Sets the card account number.
	 * @param accountNumber - the account number
	 */
	void setAccountNumber(String accountNumber);
	
	
	/**
	 * Sets the card expiration date.
	 * @param expDate - the expiration date
	 */
	void setExpirationDate(String expDate);
	
	
	/**
	 * Sets the card holder's name.
	 * @param name - the card holders name
	 */
	void setHolder(String name);
	
	
	/**
	 * Sets the card issuer.
	 * @param issuer - the card issuer
	 */
	void setIssuer(String issuer);
	
	
	/**
	 * Sets the card type.
	 * @param type - the card type
	 */
	void setType(String type);
	 


}
