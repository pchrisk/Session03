package edu.uw.ck.rmibroker;

import java.rmi.Remote;
import java.rmi.RemoteException;

import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.exchange.StockQuote;

// TODO: Auto-generated Javadoc
/**
 * The Interface RemoteBrokerSession.
 */
public interface RemoteBrokerSession extends Remote {

	/**
	 * Gets the balance.
	 *
	 * @return the balance
	 */
	int getBalance() throws RemoteException;

	// should return the balance of session's account

	/**
	 * Delete account.
	 */
	void deleteAccount() throws RemoteException, BrokerException;

	// should delete the session's account
	
	/**
	 * Request quote.
	 *
	 * @return the stock quote
	 */
	StockQuote requestQuote(String ticker) throws RemoteException, BrokerException;

	// obtains the current price of a stock and returns a StockQuote object
	
	/**
	 * Place market buy order.
	 */
	void placeMarketBuyOrder(String ticker, int numberOfShares) throws RemoteException, BrokerException;

	// places a market buy order with the broker
	
	/**
	 * Place market sell order.
	 */
	void placeMarketSellOrder(String ticker, int numberOfShares) throws RemoteException, BrokerException;

	// places a market sell order with the broker
	
	/**
	 * Place stop buy order.
	 */
	void placeStopBuyOrder(String ticker, int numberOfShares, int price) throws RemoteException, BrokerException;

	// places a stop buy order with the broker
	
	/**
	 * Place stop sell order.
	 */
	void placeStopSellOrder(String ticker, int numberOfShares, int price) throws RemoteException, BrokerException;

	// places a stop sell order with the broker
	
	/**
	 * Close.
	 */
	void close() throws RemoteException;
	
	// closes the session and releases any resources

}
