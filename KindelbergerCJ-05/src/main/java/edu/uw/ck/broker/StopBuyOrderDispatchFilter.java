package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.StopBuyOrder;

public class StopBuyOrderDispatchFilter extends OrderDispatchFilter<Integer, StopBuyOrder> {
	
	private static Logger logger = LoggerFactory.getLogger(StopBuyOrderDispatchFilter.class);

	public StopBuyOrderDispatchFilter(int price) {
		setThreshold(price);
	}

	@Override
	public boolean check(StopBuyOrder order) {
		int price = 0;
		int threshold = 0;
		boolean exec = false;
		
		price = order.getPrice();
		threshold = getThreshold();
		
		exec = price <= threshold;
		
		logger.info(price + ": when the threshold is " + threshold);
		return exec;
	}

}
