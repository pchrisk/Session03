package edu.uw.ck.exchange;

import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.NetworkExchangeAdapterFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class ExchangeNetworkAdapterFactoryImpl implements NetworkExchangeAdapterFactory{
	
	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkAdapterFactoryImpl.class);


	public ExchangeNetworkAdapterFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExchangeNetworkAdapter newAdapter(StockExchange exchange,
			String multicastIP, int multicastPort, int commandPort) {
		
		logger.info("Creating new ExchangeNetworkAdapter");
		ExchangeNetworkAdapter adapter = null;
		try {
			adapter = new ExchangeNetworkAdapter(exchange, multicastIP, multicastPort,
					commandPort);
		} catch (UnknownHostException | SocketException e) {
			
			e.printStackTrace();
		}
		return adapter;
	}

}
