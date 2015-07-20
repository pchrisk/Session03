package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.account.AccountImpl;
import edu.uw.ext.framework.broker.OrderManager;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

public class OrderManagerImpl implements OrderManager {
	
	private static Logger logger = LoggerFactory.getLogger(OrderManagerImpl.class);

	@Override
	public void adjustPrice(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void queueOrder(StopBuyOrder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void queueOrder(StopSellOrder arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrderProcessor(OrderProcessor arg0) {
		// TODO Auto-generated method stub

	}

}
