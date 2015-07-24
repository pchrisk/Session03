package edu.uw.ck.broker;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.StopBuyOrder;

public class StopBuyOrderDispatchFilter extends OrderDispatchFilter<Integer, StopBuyOrder> {

	@Override
	public boolean check(StopBuyOrder order) {
		int price = 0;
		int threshold = 0;
		boolean exec = false;
		
		price = order.getPrice();
		threshold = getThreshold();
		
		exec = price <= threshold;
		return exec;
	}

}
