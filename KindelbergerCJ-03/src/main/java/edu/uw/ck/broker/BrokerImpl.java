package edu.uw.ck.broker;

import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.MarketBuyOrder;
import edu.uw.ext.framework.order.MarketSellOrder;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

public class BrokerImpl implements Broker {

	@Override
	public void close() throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public Account createAccount(String arg0, String arg1, int arg2)
			throws BrokerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAccount(String arg0) throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public Account getAccount(String arg0, String arg1) throws BrokerException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void placeOrder(MarketBuyOrder arg0) throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeOrder(MarketSellOrder arg0) throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeOrder(StopBuyOrder arg0) throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public void placeOrder(StopSellOrder arg0) throws BrokerException {
		// TODO Auto-generated method stub

	}

	@Override
	public StockQuote requestQuote(String arg0) throws BrokerException {
		// TODO Auto-generated method stub
		return null;
	}

}
