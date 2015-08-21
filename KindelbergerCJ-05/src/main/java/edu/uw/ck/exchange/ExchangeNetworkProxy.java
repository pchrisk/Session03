package edu.uw.ck.exchange;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.net.Socket;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.Order;
import static edu.uw.ck.exchange.ProtocalConstants.*;

public class ExchangeNetworkProxy implements StockExchange {

	private static Logger logger = LoggerFactory.getLogger(ExchangeNetworkProxy.class);

	private String commandIpAddress;
	private int comandPort;
	private NetEventProcessor eventProcessor;

	public ExchangeNetworkProxy(String eventIpAddress, int eventPort,
			String cmdIpAddress, int cmdPort) {
		eventProcessor = new NetEventProcessor(eventIpAddress, eventPort);
		commandIpAddress = cmdIpAddress;
		comandPort = cmdPort;

		Executors.newSingleThreadExecutor().execute(eventProcessor);
	}

	@Override
	public boolean isOpen() {
		return OPEN_STATE.equals(sendTcpCmd(GET_STATE_CMD));

	}

	@Override
	public String[] getTickers() {
		return sendTcpCmd(GET_TICKERS_CMD).split(ELEMENT_DELIMETER);
	}

	@Override
	public StockQuote getQuote(String ticker) {
		StringBuilder cmd = new StringBuilder();

		cmd.append(GET_QUOTE_CMD);
		cmd.append(ELEMENT_DELIMETER);
		cmd.append(ticker);

		String priceAsString = sendTcpCmd(cmd.toString());
		int price = INVALID_STOCK;

		price = Integer.parseInt(priceAsString);

		StockQuote stockQuote = null;
		if (price >= 0) {
			stockQuote = new StockQuote(ticker, price);
		}

		return stockQuote;
	}

	@Override
	public synchronized void addExchangeListener(ExchangeListener l) {
		eventProcessor.addExchangeListener(l);

	}

	@Override
	public synchronized void removeExchangeListener(ExchangeListener l) {
		eventProcessor.removeExchangeListener(l);

	}

	@Override
	public int executeTrade(Order order) {
		String orderType = (order.isBuyOrder()) ? BUY_ORDER : SELL_ORDER;
		StringBuilder cmd = new StringBuilder();

		cmd.append(EXE_TRADE_CMD);
		cmd.append(ELEMENT_DELIMETER);
		cmd.append(orderType);
		cmd.append(ELEMENT_DELIMETER);
		cmd.append(order.getAccountId());
		cmd.append(ELEMENT_DELIMETER);
		cmd.append(order.getStockTicker());
		cmd.append(ELEMENT_DELIMETER);
		cmd.append(order.getNumberOfShares());

		String response = sendTcpCmd(cmd.toString());

		int executionPrice = Integer.parseInt(response);

		return executionPrice;
	}

	private String sendTcpCmd(String cmd) {
		
		PrintWriter pw = null;
		BufferedReader br = null;
		String response = "";
				
		try (Socket socket = new Socket(commandIpAddress, comandPort)) {
			InputStream is = socket.getInputStream();
			Reader isReader = new InputStreamReader(is);
			br = new BufferedReader(isReader);

			OutputStream os = socket.getOutputStream();
			Writer osWriter = new OutputStreamWriter(os);
			pw = new PrintWriter(osWriter, true);

			pw.println(cmd);
			response = br.readLine();
			
		} catch (final IOException e) {
			logger.error("Sending to exchange error: ", e);
		}
		
//		inputstram
//		reader
//		bufferedreader
//		
//		outputstream
//		writer
//		prntwrtr = new printwriter
//		
//		prntWrtr.println(cmd);
//		resonse = br.readline();
//		
		return response;
	}
	
		
		
		
	
}
