package edu.uw.ck.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.exchange.NetworkExchangeProxyFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class ExchangeNetworkProxyFactoryImpl implements
		NetworkExchangeProxyFactory {
	
	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkProxyFactoryImpl.class);

	public ExchangeNetworkProxyFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExchangeNetworkProxy newProxy(String multicastIP, int multicastPort,
			String commandIP, int commandPort) {
		logger.info("Creating new ExchangeNetworkProxy");
		ExchangeNetworkProxy proxy = null;
		proxy = new ExchangeNetworkProxy(multicastIP, multicastPort, commandIP, commandPort);
		
		return proxy;
	}

}
