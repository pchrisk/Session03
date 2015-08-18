package edu.uw.ck.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.Order;

public class ExchangeNetworkProxy implements StockExchange {
	
	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkProxy.class);

	public ExchangeNetworkProxy() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getTickers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StockQuote getQuote(String ticker) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addExchangeListener(ExchangeListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeExchangeListener(ExchangeListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public int executeTrade(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
