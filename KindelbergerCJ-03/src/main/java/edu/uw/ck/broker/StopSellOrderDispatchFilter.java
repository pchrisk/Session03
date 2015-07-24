package edu.uw.ck.broker;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.order.StopSellOrder;

public class StopSellOrderDispatchFilter extends OrderDispatchFilter<Integer, StopSellOrder> {

	@Override
	public boolean check(StopSellOrder order) {
		int price = 0;
		int threshold = 0;
		boolean exec = false;
		
		price = order.getPrice();
		threshold = getThreshold();
		
		exec = price >= threshold;
		return exec;
	}

}
