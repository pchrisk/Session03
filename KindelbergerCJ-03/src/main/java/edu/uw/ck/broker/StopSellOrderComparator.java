package edu.uw.ck.broker;

import java.util.Comparator;

import edu.uw.ext.framework.order.StopSellOrder;

public class StopSellOrderComparator implements Comparator<StopSellOrder> {

	public StopSellOrderComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(StopSellOrder o1, StopSellOrder o2) {
		int compared = Integer.compare(o2.getPrice(), o1.getPrice());
		if (compared == 0) {
			compared = o1.compareTo(o2);
		}
		return compared;
	}

}
