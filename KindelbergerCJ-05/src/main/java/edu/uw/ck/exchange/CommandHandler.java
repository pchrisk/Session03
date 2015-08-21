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
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.MarketBuyOrder;
import edu.uw.ext.framework.order.MarketSellOrder;
import edu.uw.ext.framework.order.Order;
import static edu.uw.ck.exchange.ProtocalConstants.*;


public class CommandHandler implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(CommandHandler.class);

	private Socket socket;
	
	private StockExchange realExchange;
	
	public CommandHandler(Socket socket, StockExchange realExchange) {
		
		this.socket = socket;
		this.realExchange = realExchange;
		
	}

	@Override
	public void run() {
		
		InputStream is = null;
		OutputStream os = null;
		
		
		try {
			is = socket.getInputStream();
			Reader isReader = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isReader);

			os = socket.getOutputStream();
			Writer osWriter = new OutputStreamWriter(os);
			PrintWriter pw = new PrintWriter(osWriter, true);

			String msg = br.readLine();
			if (msg == null) {
				msg = "";
			}

			logger.info("Message recieved: ", msg);

			String[] msgParts = msg.split(ELEMENT_DELIMETER);
			String cmd = msgParts[CMD_ELEMENT];

			logger.info("Process cmd: ", cmd);
			int price = 0;
			String ticker = null;

			switch (cmd) {
				case GET_TICKERS_CMD:
					String[] tickers = realExchange.getTickers();
					StringBuilder buffer = new StringBuilder();
					for (String t : tickers) {
						buffer.append(t + ELEMENT_DELIMETER);
					}
					pw.println(buffer);
					break;
				case GET_STATE_CMD:
					String buffer1 = realExchange.isOpen() ? OPEN_STATE : CLOSED_STATE;
					pw.println(buffer1);
					break;
				case GET_QUOTE_CMD:
					ticker = msgParts[QUOTE_CMD_TICKER_ELEMENT];
					StockQuote sQuote = realExchange.getQuote(ticker);
					if (sQuote == null) {
						price = INVALID_STOCK;
					} else {
						price = sQuote.getPrice();
					}
					pw.print(price);
					break;
				case EXE_TRADE_CMD:
					String ordType = msgParts[EXE_TRADE_CMD_TYPE_ELEMENT];
					String accountID = msgParts[EXE_TRADE_CMD_ACCOUNT_ELEMENT];
					ticker = msgParts[EXE_TRADE_CMD_TICKER_ELEMENT];
					String shares = msgParts[EXE_TRADE_CMD_SHARES_ELEMENT];
					
					int sharesQty = Integer.parseInt(shares);
					
					Order order = null;
					
					switch (ordType) {
					case SELL_ORDER:
						order = new MarketBuyOrder(accountID, sharesQty, ticker);
						break;
					case BUY_ORDER:
						order = new MarketSellOrder(accountID, sharesQty, ticker);
						break;
					}
					
					price = realExchange.executeTrade(order);
					pw.println(price);
				
					
					break;

			}
		} catch (IOException e) {
			logger.error("Issues with Streams", e);
		}
			
			
		
//		inputstream
//		reader
//		buffered reader
//		
//		outputstream
//		writer
//		printwriter
//		
//		split msg
//		
//		switch (cmd) {
//		case get_state_cmd
//		realexchagne.isopne() ?OPEN_STATE
//								: 
//									
//		case get_quote_cmd
//		get price from ticker
//		prntwrtr.prinln(price);
//		
//		case get_ticker_cmd:
//			
//			String[] tickers = realExchagne
//			
//		case execute_trade_cmd:
//			ordertype = elements[pc.execute]
//		}

	}

}
