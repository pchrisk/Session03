package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.Order;

public class MarketDispatchfilter extends OrderDispatchFilter<Boolean, Order> {
	
	private static Logger logger = LoggerFactory.getLogger(MarketDispatchfilter.class);

	public MarketDispatchfilter(boolean initState) {
		setThreshold(initState);
	}

	@Override
	public boolean check(Order order) {
		return getThreshold();
	}

}
