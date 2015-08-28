package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.rmibroker.RemoteBrokerGateway;
import edu.uw.ck.rmibroker.RemoteBrokerSession;
import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.exchange.StockQuote;

public class RmiBrokerClient {
	
	private static Logger logger = LoggerFactory.getLogger(RmiBrokerClient.class);
	
	
	public static final int ORDER_SHARES_345 = 345;
	public static final int ORDER_SHARES_175 = 175;
	public static final int ORDER_SHARES_45 = 45;
	public static final int ORDER_SHARES_15 = 15;
	public static final int PRICE_CHANGE = 5;
	
	public static final String USER_ACCT_CKENT = "clarkkent1";
	public static final String USER_PASSWORD = "superman";
	public static final int INITIAL_BALANCE = 100000;
	public static final String STOCK_TICKER_BA = "BA";
	public static final String STOCK_TICKER_F = "F";
	
	
	

	public RmiBrokerClient() {
		// TODO Auto-generated constructor stub
	}

	
	public static void main(final String[] args) throws IOException, RemoteException, NotBoundException {
		
		String url = String.format("//%s:%d/%s", RmiBrokerServer.RMI_SERVER_NAME, RmiBrokerServer.RMI_SERVICE_PORT, RmiBrokerServer.BROKER_NAME);
		
		logger.info(url);
		Remote remoteObject = Naming.lookup(url);
		RemoteBrokerGateway rGateway = (RemoteBrokerGateway) remoteObject;
		
		try {
			RemoteBrokerSession rSession = rGateway.createAccount(USER_ACCT_CKENT, USER_PASSWORD, INITIAL_BALANCE);
			
			logger.info("Account created for " + USER_ACCT_CKENT);
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			rSession.placeMarketSellOrder(STOCK_TICKER_F, ORDER_SHARES_345);
			rSession.placeMarketBuyOrder(STOCK_TICKER_F, ORDER_SHARES_175);
			
			
			logger.info("Placed Market Orders");
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			StockQuote quote = rSession.requestQuote(STOCK_TICKER_BA);
			int currantPrice = quote.getPrice();
			
			logger.info("Currant Price for " + STOCK_TICKER_BA + " is " + currantPrice);
			
			rSession.placeStopBuyOrder(STOCK_TICKER_BA, ORDER_SHARES_15, currantPrice - PRICE_CHANGE);
			rSession.placeStopSellOrder(STOCK_TICKER_BA, ORDER_SHARES_45, currantPrice - PRICE_CHANGE);
			
			logger.info("Placed Stop Orders BA");
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			StockQuote quote2 = rSession.requestQuote(STOCK_TICKER_F);
			int currantPrice2 = quote2.getPrice();
			
			logger.info("Currant Price for " + STOCK_TICKER_F + " is " + currantPrice2);
			
			rSession.placeStopBuyOrder(STOCK_TICKER_F, ORDER_SHARES_15, currantPrice2 - PRICE_CHANGE);
			rSession.placeStopSellOrder(STOCK_TICKER_F, ORDER_SHARES_45, currantPrice2 - PRICE_CHANGE);
			
			logger.info("Placed Stop Orders F");
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			rSession.close();
			rSession = rGateway.login(USER_ACCT_CKENT, USER_PASSWORD);
			
			logger.info("Login in to account");
			
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			InputStream is = System.in;
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			System.out.println("***** Press Enter to after waiting a bit *****");
			br.readLine();
			
			logger.info(String.format("%s balance: %d", USER_ACCT_CKENT, rSession.getBalance()));
			
			rSession.deleteAccount();
			
			
		} catch (BrokerException e) {
			logger.error("Debug", e);
			
		}
		
		try {
			rGateway.login(USER_ACCT_CKENT, USER_PASSWORD);
			logger.error("Login should not have succeeded");
		} catch (BrokerException e) {
			logger.info("Expected result, account was deleted");
		}
		
		
		
		
	}
}
