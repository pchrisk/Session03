package edu.uw.ck.rmibroker;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import app.RmiBrokerClient;
import edu.uw.ck.broker.BrokerImpl;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerException;

public class RemoteBrokerGatewayImpl extends UnicastRemoteObject implements RemoteBrokerGateway {
	
	private static Logger logger = LoggerFactory.getLogger(RemoteBrokerGatewayImpl.class);
	
	private Broker broker;

	public RemoteBrokerGatewayImpl(Broker broker) throws RemoteException {
		this.broker = broker;
	}

	

	@Override
	public RemoteBrokerSession createAccount(String userName, String password,
			int balance) throws RemoteException, BrokerException {
		logger.debug("Atempting to create Account: " + userName + password + balance);
		Account account = broker.createAccount(userName, password, balance);
		RemoteBrokerSession rbs = new RemoteBrokerSessionImpl(broker, account);
		return rbs;
	}

	@Override
	public RemoteBrokerSession login(String userName, String password)
			throws RemoteException, BrokerException {
		Account account = broker.getAccount(userName, password);
		RemoteBrokerSession rbs = new RemoteBrokerSessionImpl(broker, account);
		return rbs;
	}

}
