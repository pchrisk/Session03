package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.exchange.StockExchange;

public class StockTraderOrderProcessor implements OrderProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(StockTraderOrderProcessor.class);
	
	private AccountManager acctMgr;
	private StockExchange exchange;

	public StockTraderOrderProcessor(AccountManager acctMgr, StockExchange exchange) {
		this.acctMgr = acctMgr;
		this.exchange = exchange;
		
		logger.info("Order Processor created for " + acctMgr + " and Exchange " + exchange);
		
	}

	@Override
	public void process(Order order) {
		int sharePrice = exchange.executeTrade(order);
		String accountName = order.getAccountId();
		
		try {
			Account account = acctMgr.getAccount(accountName);
			account.reflectOrder(order, sharePrice);
			logger.info(order + " reflected against account " + account.getName() + " at a price of " + sharePrice);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
