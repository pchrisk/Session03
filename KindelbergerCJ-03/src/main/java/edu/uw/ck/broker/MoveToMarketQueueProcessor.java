package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;

public class MoveToMarketQueueProcessor implements OrderProcessor {
	
	private static Logger logger = LoggerFactory.getLogger(MoveToMarketQueueProcessor.class);
	
	private OrderQueue<Order> marketQueue;

	public MoveToMarketQueueProcessor(OrderQueue<Order> marketQueue) {
		this.marketQueue = marketQueue;
		logger.info("MoveToMarketQueue Processor created");
	}

	@Override
	public void process(Order order) {
		marketQueue.enqueue(order);
		
		logger.info("Processed order");

	}

}
