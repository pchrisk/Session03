package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private OrderDispatchFilter<Integer, StopSellOrder> stopSellOrderFilter;
	
	

	public OrderManagerImpl(String stockTickerSymbol, int price) {
		this.stockTickeSymbol = stockTickerSymbol;
		stopSellOrderFilter = new StopSellOrderDispatchFilter(price);
		stopSellOrderQueue = new OrderQueueImpl<StopSellOrder>(new StopSellOrderComparator(), stopSellOrderFilter);
		
		stopBuyOrderFilter = new StopBuyOrderDispatchFilter(price);
		stopBuyOrderQueue = new OrderQueueImpl<StopBuyOrder>(new StopBuyOrderComparator(), stopBuyOrderFilter);
		
		logger.info("Queues created for " + stockTickerSymbol);
		
	}

	@Override
	public void adjustPrice(int price) {
		int th = stopBuyOrderFilter.getThreshold();
		stopBuyOrderFilter.setThreshold(price);
		stopSellOrderFilter.setThreshold(price);
		
		logger.info(this.getSymbol() + " Price adjusted from " + th + " to " + price);

	}

	@Override
	public String getSymbol() {
		
		return this.stockTickeSymbol;
	}

	@Override
	public void queueOrder(StopBuyOrder order) {
		stopBuyOrderQueue.enqueue(order);

	}

	@Override
	public void queueOrder(StopSellOrder order) {
		stopSellOrderQueue.enqueue(order);

	}

	@Override
	public void setOrderProcessor(OrderProcessor processor) {
		stopSellOrderQueue.setOrderProcessor(processor);
		stopBuyOrderQueue.setOrderProcessor(processor);

	}

}
