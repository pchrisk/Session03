package edu.uw.ck.broker;

import java.util.HashMap;


//import org.junit.internal.runners.model.EachTestNotifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.account.AccountImpl;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerException;
import edu.uw.ext.framework.broker.OrderManager;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.ExchangeListener;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockQuote;
import edu.uw.ext.framework.order.MarketBuyOrder;
import edu.uw.ext.framework.order.MarketSellOrder;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

public class BrokerImpl implements Broker, ExchangeListener {
	
	private static Logger logger = LoggerFactory.getLogger(BrokerImpl.class);
	
	private String brokerName;
	private AccountManager acctMgr;
	private StockExchange exchg;
	
	private HashMap<String, OrderManager> orderManagers;
	
	private OrderQueue<Order> marketOrders;
	private MarketDispatchfilter marketDispatchFilter;
	
	
//	private int balance;
	

	public BrokerImpl(String brokerName, AccountManager acctMgr,
			StockExchange exchg) {
		this.brokerName = brokerName;
		this.acctMgr = acctMgr;
		this.exchg = exchg;
		
		marketDispatchFilter = new MarketDispatchfilter(exchg.isOpen());
		marketOrders = new OrderQueueImpl<Order>(marketDispatchFilter);
		marketOrders.setOrderProcessor(new StockTraderOrderProcessor(acctMgr, exchg));
		
		initializeOrderManagers();
		
		exchg.addExchangeListener(this);
		
		logger.info(" Broker " + brokerName + " created.");
		
	}
	
	protected final void initializeOrderManagers() {
		OrderManager om = null;
		orderManagers = new HashMap<String, OrderManager>();
		OrderProcessor moveToMarketProc = new MoveToMarketQueueProcessor(marketOrders);
		for (String t : exchg.getTickers()) {
			om = createOrderManager(t, exchg.getQuote(t).getPrice());
			om.setOrderProcessor(moveToMarketProc);
			orderManagers.put(t, om);
			
		}
	}
	
	protected OrderManager createOrderManager(String ticker, int InitialPrice) {
		OrderManager orderManager = new OrderManagerImpl(ticker, InitialPrice);
		return orderManager;
	}

	@Override
	public void close() throws BrokerException {
		
		try {
			exchg.removeExchangeListener(this);
			acctMgr.close();
			orderManagers = null;
		} catch (AccountException e) {
			throw new BrokerException("Could not close Broker", e);
		}
		

	}

	

	@Override
	public Account createAccount(String userName, String password, int balance)
			throws BrokerException {
		Account account = null;
		try {
			logger.debug("Attempting to create Account: " + userName + password + balance);
			account = acctMgr.createAccount(userName, password, balance);
		} catch (AccountException e) {
			throw new BrokerException("Could not create account.", e);
		}		

		return account;
	}

	@Override
	public void deleteAccount(String username) throws BrokerException {
		try {
			acctMgr.deleteAccount(username);
		} catch (AccountException e) {
			throw new BrokerException("Could not delete account.", e);
		}

	}

	@Override
	public Account getAccount(String username, String password) throws BrokerException {
		
		Account account = null;
		try {
			if (acctMgr.validateLogin(username, password)) {
				account = acctMgr.getAccount(username);
			} else {
				throw new BrokerException("User name or password not correct");
			}
			
		} catch (AccountException e) {
			throw new BrokerException("Could not get account.", e);
		}
		
		return account;
	}

	@Override
	public String getName() {
		return this.brokerName;
	}

	@Override
	public void placeOrder(MarketBuyOrder order) throws BrokerException {
		marketOrders.enqueue(order);
		logger.info("Market Buy Order placed for : " + order.getAccountId());
		

	}

	@Override
	public void placeOrder(MarketSellOrder order) throws BrokerException {
		marketOrders.enqueue(order);
		logger.info("Market Sell Order placed for : " + order.getAccountId());

	}

	@Override
	public void placeOrder(StopBuyOrder order) throws BrokerException {
		OrderManager om = orderManagers.get(order.getStockTicker());
		om.queueOrder(order);
		logger.info("Stop Buy Order placed for : " + order.getAccountId());

	}

	@Override
	public void placeOrder(StopSellOrder order) throws BrokerException {
		OrderManager om = orderManagers.get(order.getStockTicker());
		om.queueOrder(order);
		logger.info("Stop Sell Order placed for : " + order.getAccountId());
	}

	@Override
	public StockQuote requestQuote(String symbol) throws BrokerException {
		StockQuote quote = exchg.getQuote(symbol);
		if (quote != null) {
			return quote;
		} else {
			throw new BrokerException("No quote available for " + symbol);
			
		}
	}

	@Override
	public void exchangeOpened(ExchangeEvent event) {
		marketDispatchFilter.setThreshold(Boolean.TRUE);
		
		
		logger.info("The Exchange is open");
		
	}

	@Override
	public void exchangeClosed(ExchangeEvent event) {
		marketDispatchFilter.setThreshold(Boolean.FALSE);
		
		logger.info("The Exchange is closed");
		
	}

	@Override
	public void priceChanged(ExchangeEvent event) {
		
		String eTicker = event.getTicker();
		int ePrice = event.getPrice();
		
		logger.info(eTicker + " has a new price of " + ePrice);
		
		OrderManager om = orderManagers.get(eTicker);
		if (om != null) {
			om.adjustPrice(ePrice);
		}
		
		logger.info(eTicker + " has a new price of " + ePrice);
	}

}
