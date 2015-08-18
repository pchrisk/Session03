package edu.uw.ck.exchange;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.StockExchange;

public class ExchangeNetworkAdapter implements ExchangeAdapter {
	
	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkAdapter.class);

	
	

	private StockExchange origExchange = null;
	
	private DatagramPacket dgPacket = null;
	
	private MulticastSocket socket = null;
	
	private CommandListner cmdListner = null;
	 
	
	public ExchangeNetworkAdapter(StockExchange exchng, String multicastIP, 
			int multicastPort, int commandPort) 
					throws UnknownHostException, SocketException {
		origExchange = exchng;
		
		
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
