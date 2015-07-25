package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.account.AccountImpl;
import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.broker.OrderManager;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

public class OrderManagerImpl implements OrderManager {
	
	private static Logger logger = LoggerFactory.getLogger(OrderManagerImpl.class);
	
	private String stockTickeSymbol;
	private OrderQueue<StopSellOrder> stopSellOrderQueue;
	private OrderQueue<StopBuyOrder> stopBuyOrderQueue;
	private OrderDispatchFilter<Integer, StopBuyOrder> stopBuyOrderFilter;
	private OrderDispatchFilter<Integer, StopSellOrder> StopSellOrderFilter;
	
	

	public OrderManagerImpl(String stockTickerSymbol, int price) {
		this.stockTickeSymbol = stockTickerSymbol;
	}

	@Override
	public void adjustPrice(int price) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getSymbol() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void queueOrder(StopBuyOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void queueOrder(StopSellOrder order) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setOrderProcessor(OrderProcessor arg0) {
		// TODO Auto-generated method stub

	}

}
