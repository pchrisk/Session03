package edu.uw.ck.broker;

import java.util.Comparator;

import edu.uw.ext.framework.order.StopBuyOrder;

public class StopBuyOrderComparator implements Comparator<StopBuyOrder> {

	public StopBuyOrderComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(StopBuyOrder o1,  StopBuyOrder o2) {
		int compared = Integer.compare(o1.getPrice(), o2.getPrice());
		if (compared == 0) {
			compared = o1.compareTo(o2);
		}
		return compared;
	}

}
