package edu.uw.ck;

import edu.uw.ext.framework.account.Address;

public class AddressImpl implements Address {

	private String city;
	private String state;
	private String streetAddress;
	private String zipCode;

	@Override
	public String getCity() {		
		return city;
	}

	@Override
	public String getState() {
		return state;
	}

	@Override
	public String getStreetAddress() {
		return streetAddress;
	}

	@Override
	public String getZipCode() {
		return zipCode;
	}

	@Override
	public void setCity(String city) {
		this.city = city;

	}

	@Override
	public void setState(String state) {
		this.state = state;

	}

	@Override
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;

	}

	@Override
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;

	}

}
