package edu.uw.ck.exchange;

import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.Order;

public class ExchangeNetworkProxy implements StockExchange {

	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkProxy.class);
	
	String commandIpAddress
	int comandPort
	NetEventProcessor eventProcessor;
	
	public ExchangeNetworkProxy() {
		commandIpAddress = cmdIpAddress;
		
		
		Executors.newSingleThreadExecutor()
	}

	@Override
	public boolean isOpen() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String[] getTickers() {
		// TODO Auto-generated method stub
		return tickers;
	}

	@Override
	public StockQuote getQuote(String ticker) {
		// TODO Auto-generated method stub
		
		quote = new StockQuote(ticker, price);
		
		
		return quote;
	}

	@Override
	public void addExchangeListener(ExchangeListener l) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeExchangeListener(ExchangeListener l) {
		// TODO Auto-generated method stub
		eventProcessor.re

	}

	@Override
	public int executeTrade(Order order) {
		// TODO Auto-generated method stub
		
		
		return executionPrice;
	}
	
	private String sendTcpCmd(String cmd) {
		inputstram
		reader
		bufferedreader
		
		outputstream
		writer
		prntwrtr = new printwriter
		
		prntWrtr.println(cmd);
		resonse = br.readline();
		
		return response;
		
		
	}

}
