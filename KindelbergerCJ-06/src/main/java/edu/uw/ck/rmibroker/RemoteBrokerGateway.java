package edu.uw.ck.rmibroker;

import java.rmi.Remote;

public interface RemoteBrokerGateway extends Remote {
	
	/**
	 * operation is used to create an account
	 * 
	 * @return a reference to a remote session
	 */
	
	RemoteBrokerSession createAccount();
	
	
	/**
	 * operation is used to login to an existing account
	 * 
	 * @return a reference to a remote session
	 */
	RemoteBrokerSession login();

}
