package edu.uw.ck.exchange;

import java.net.SocketException;
import java.net.UnknownHostException;

import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.NetworkExchangeAdapterFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class ExchangeNetworkAdapterFactoryImpl implements NetworkExchangeAdapterFactory{


	public ExchangeNetworkAdapterFactoryImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ExchangeNetworkAdapter newAdapter(StockExchange exchange,
			String multicastIP, int multicastPort, int commandPort) {
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
