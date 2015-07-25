package edu.uw.ck.broker;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.Order;

public class MarketDispatchfilter extends OrderDispatchFilter<Boolean, Order> {
	
	

	public MarketDispatchfilter(boolean initState) {
		setThreshold(initState);
	}

	@Override
	public boolean check(Order order) {
		return getThreshold();
	}

}
