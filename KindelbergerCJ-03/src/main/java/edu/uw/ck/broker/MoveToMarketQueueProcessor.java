package edu.uw.ck.broker;

import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;

public class MoveToMarketQueueProcessor implements OrderProcessor {
	
	private OrderQueue<Order> marketQueue;

	public MoveToMarketQueueProcessor(OrderQueue<Order> marketQueue) {
		this.marketQueue = marketQueue;
	}

	@Override
	public void process(Order order) {
		marketQueue.enqueue(order);

	}

}
