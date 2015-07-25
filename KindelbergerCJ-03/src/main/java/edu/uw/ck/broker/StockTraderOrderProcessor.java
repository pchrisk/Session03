package edu.uw.ck.broker;

import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.account.Account;
import edu.uw.ext.framework.account.AccountException;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.exchange.StockExchange;

public class StockTraderOrderProcessor implements OrderProcessor {
	
	private AccountManager acctMgr;
	private StockExchange exchange;

	public StockTraderOrderProcessor(AccountManager acctMgr, StockExchange exchange) {
		this.acctMgr = acctMgr;
		this.exchange = exchange;
		
	}

	@Override
	public void process(Order order) {
		int sharePrice = exchange.executeTrade(order);
		String accountName = order.getAccountId();
		
		try {
			Account account = acctMgr.getAccount(accountName);
			account.reflectOrder(order, sharePrice);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
