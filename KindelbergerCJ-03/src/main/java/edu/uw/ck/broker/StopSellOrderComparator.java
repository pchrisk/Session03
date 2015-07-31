package edu.uw.ck.broker;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.order.StopSellOrder;

public class StopSellOrderComparator implements Comparator<StopSellOrder> {
	
	private static Logger logger = LoggerFactory.getLogger(StopSellOrderComparator.class);

	public StopSellOrderComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(StopSellOrder o1, StopSellOrder o2) {
		logger.info(o1 + " compared to " + o2);
		if (o1 == o2) {
			return 0;
		}
		int compared = Integer.compare(o2.getPrice(), o1.getPrice());
		if (compared == 0) {
			compared = o1.compareTo(o2);
		}
		return compared;
	}

}
