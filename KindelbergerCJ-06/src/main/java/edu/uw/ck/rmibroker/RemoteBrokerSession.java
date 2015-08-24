package edu.uw.ck.rmibroker;

import java.rmi.Remote;

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
	int getBalance();

	// should return the balance of session's account

	/**
	 * Delete account.
	 */
	void deleteAccount();

	// should delete the session's account
	
	/**
	 * Request quote.
	 *
	 * @return the stock quote
	 */
	StockQuote requestQuote();

	// obtains the current price of a stock and returns a StockQuote object
	
	/**
	 * Place market buy order.
	 */
	void placeMarketBuyOrder();

	// places a market buy order with the broker
	
	/**
	 * Place market sell order.
	 */
	void placeMarketSellOrder();

	// places a market sell order with the broker
	
	/**
	 * Place stop buy order.
	 */
	void placeStopBuyOrder();

	// places a stop buy order with the broker
	
	/**
	 * Place stop sell order.
	 */
	void placeStopSellOrder();

	// places a stop sell order with the broker
	
	/**
	 * Close.
	 */
	void close();
	
	// closes the session and releases any resources

}
