package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.StopSellOrder;

public class StopSellOrderDispatchFilter extends OrderDispatchFilter<Integer, StopSellOrder> {
	
	private static Logger logger = LoggerFactory.getLogger(StopSellOrderDispatchFilter.class);

	public StopSellOrderDispatchFilter(int price) {
		setThreshold(price);
		
		logger.info("Threshold set to " + price);
	}

	@Override
	public boolean check(StopSellOrder order) {
		int price = 0;
		int threshold = 0;
		boolean exec = false;
		
		price = order.getPrice();
		threshold = getThreshold();
		
		exec = price >= threshold;
		
		logger.info(price + ": when the threshold is " + threshold);
		return exec;
	}

}
