package edu.uw.ck.exchange;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeAdapter;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.StockExchange;

import static edu.uw.ck.exchange.ProtocalConstants.*;

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
		InetAddress mcastGroup = InetAddress.getByName(multicastIP);
		byte[] buffer = {};
		try {
			socket = new MulticastSocket(multicastPort);
			socket.setSoTimeout(1);
			logger.info("Sending Multicasts to " + multicastIP + ":" + multicastPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		cmdListner = new CommandListner(commandPort, origExchange);
		Executors.newSingleThreadExecutor().execute(cmdListner);
		
		origExchange.addExchangeListener(this);
	}

	@Override
	public void exchangeOpened(ExchangeEvent event) {
		logger.info("Open Event");
		try {
			byte[] buffer = OPEN_EVNT.getBytes(ENCODING);
			dgPacket.setData(buffer);
			dgPacket.setLength(buffer.length);
			socket.send(dgPacket);
			
		} catch (IOException e) {
			logger.error("exchange open error", e);
		}
		
		

	}

	@Override
	public void exchangeClosed(ExchangeEvent event) {
		byte[] buffer;
		logger.info("Close Event");
		try {
			buffer = CLOSED_EVNT.getBytes(ENCODING);
			dgPacket.setData(buffer);
			dgPacket.setLength(buffer.length);
			socket.send(dgPacket);
		} catch (IOException e) {
			logger.error("exchange close error", e);
		}
		

	}

	@Override
	public void priceChanged(ExchangeEvent event) {
		logger.info("Price Change Event");
		byte[] buffer;
		
		int price = event.getPrice();
		String tickerSymbol = event.getTicker();
		
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(PRICE_CHG_EVNT);
		sBuilder.append(ELEMENT_DELIMETER);
		sBuilder.append(tickerSymbol);
		sBuilder.append(ELEMENT_DELIMETER);
		sBuilder.append(price);
		
		try {
			buffer = sBuilder.toString().getBytes(ENCODING);
			dgPacket.setData(buffer);
			dgPacket.setLength(buffer.length);
			socket.send(dgPacket);
		} catch (IOException e) {
			logger.error("Price Change error", e);
		}
						

	}

	@Override
	public void close() {
		origExchange.removeExchangeListener(this);
		cmdListner.terminate();
		socket.close();

	}

}
