package edu.uw.ck.exchange;

import edu.uw.ext.framework.exchange.NetworkExchangeProxyFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class ExchangeNetworkProxyFactoryImpl implements
		NetworkExchangeProxyFactory {

	public ExchangeNetworkProxyFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExchangeNetworkProxy newProxy(String multicastIP, int multicastPort,
			String commandIP, int commandPort) {
		ExchangeNetworkProxy proxy = null;
		proxy = new ExchangeNetworkProxy(multicastIP, multicastPort, commandIP, commandPort);
		
		return proxy;
	}

}
