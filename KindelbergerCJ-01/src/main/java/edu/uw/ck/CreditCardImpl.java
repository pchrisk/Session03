package edu.uw.ck;

import edu.uw.ext.framework.account.CreditCard;

public class CreditCardImpl implements CreditCard {
	
	
	private static final long serialVersionUID = 1L;
	private String issuer;
	private String type;
	private String name;
	private String accountNumber;
	private String expDate;

	public CreditCardImpl(){
		
	}
	
	@Override
	public String getAccountNumber() {
		return this.accountNumber;
	}

	@Override
	public String getExpirationDate() {
		return this.expDate;
	}

	@Override
	public String getHolder() {
		return this.name;
	}

	@Override
	public String getIssuer() {
		return this.issuer;
	}

	@Override
	public String getType() {
		return this.type;
	}

	@Override
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	@Override
	public void setExpirationDate(String expDate) {
		this.expDate = expDate;
	}

	@Override
	public void setHolder(String name) {
		this.name = name;
	}

	@Override
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	@Override
	public void setType(String type) {
		this.type = type;
	}

}
