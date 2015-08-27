package edu.uw.ck.rmibroker;

import java.rmi.NoSuchObjectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.MarketBuyOrder;
import edu.uw.ext.framework.order.MarketSellOrder;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

public class RemoteBrokerSessionImpl extends UnicastRemoteObject implements RemoteBrokerSession {

	
	private Account account;
	private Broker broker;
	
	public RemoteBrokerSessionImpl(Broker broker, Account account) throws RemoteException {
		this.broker = broker;
		this.account = account;
		
	}

	@Override
	public int getBalance() {
		// TODO Auto-generated method stub
		return account.getBalance();
	}	

	@Override
	public void deleteAccount() throws RemoteException, BrokerException {
		broker.deleteAccount(account.getName());
		account = null;
		
	}

	@Override
	public StockQuote requestQuote(String ticker) throws RemoteException,
			BrokerException {
		return broker.requestQuote(ticker);
	}

	@Override
	public void placeMarketBuyOrder(String ticker, int numberOfShares)
			throws RemoteException, BrokerException {
		MarketBuyOrder order = new MarketBuyOrder(account.getName(), numberOfShares, ticker);
		broker.placeOrder(order);
		
	}

	@Override
	public void placeMarketSellOrder(String ticker, int numberOfShares)
			throws RemoteException, BrokerException {
		MarketSellOrder order = new MarketSellOrder(account.getName(), numberOfShares, ticker);
		broker.placeOrder(order);
		
	}

	@Override
	public void placeStopBuyOrder(String ticker, int numberOfShares, int price)
			throws RemoteException, BrokerException {
		StopBuyOrder order = new StopBuyOrder(account.getName(), numberOfShares, ticker, price);
		broker.placeOrder(order);
		
	}

	@Override
	public void placeStopSellOrder(String ticker, int numberOfShares, int price)
			throws RemoteException, BrokerException {
		StopSellOrder order = new StopSellOrder(account.getName(), numberOfShares, ticker, price);
		broker.placeOrder(order);
		
	}
	
	@Override
	public void close() throws RemoteException {
		UnicastRemoteObject.unexportObject(this, true);
	}

}
