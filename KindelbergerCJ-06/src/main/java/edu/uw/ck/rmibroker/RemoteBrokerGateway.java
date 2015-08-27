package edu.uw.ck.rmibroker;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.uw.ext.framework.broker.BrokerException;

public interface RemoteBrokerGateway extends Remote {
	
	/**
	 * operation is used to create an account
	 * 
	 * @return a reference to a remote session
	 */
	
	RemoteBrokerSession createAccount(String userName, String password, int balance) throws RemoteException, BrokerException;
	
	
	/**
	 * operation is used to login to an existing account
	 * 
	 * @return a reference to a remote session
	 */
	RemoteBrokerSession login(String userName, String password) throws RemoteException, BrokerException;

}
