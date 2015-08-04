package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerFactory;
import edu.uw.ext.framework.exchange.StockExchange;

public class BrokerFactoryImpl implements BrokerFactory {
	
	private static Logger logger = LoggerFactory.getLogger(BrokerFactoryImpl.class);

	@Override
	public Broker newBroker(String name, AccountManager acctMngr, StockExchange exch) {
		Broker broker = new BrokerImpl(name, acctMngr, exch);
		logger.info("BrockerFactory used to create broker " + name);
		return broker;
	}

}
