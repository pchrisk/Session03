package edu.uw.ck;

import java.io.Serializable;

public interface Address extends Serializable {
	
	String getCity();	
	
	String getState();
		 
	String getStreetAddress();
		 
	String getZipCode();
		 
	void setCity(String city);
		 
	void setState(String state);
		 
	void setStreetAddress(String streetAddress);
		 
	void setZipCode(String zip);
	

}
