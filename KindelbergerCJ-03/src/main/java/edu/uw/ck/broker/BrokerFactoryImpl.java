package edu.uw.ck.broker;

import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class BrokerFactoryImpl implements BrokerFactory {

	@Override
	public Broker newBroker(String name, AccountManager acctMngr, StockExchange exch) {
		Broker broker = new BrokerImpl();
		
		return null;
	}

}
