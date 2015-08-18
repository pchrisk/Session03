package edu.uw.ck.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.ExchangeEvent;

public class ExchangeNetworkAdapter implements ExchangeAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkAdapter.class);

	public ExchangeNetworkAdapter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exchangeOpened(ExchangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void exchangeClosed(ExchangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void priceChanged(ExchangeEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void close() {
		// TODO Auto-generated method stub

	}

}
