package edu.uw.ck.broker;

import java.util.Comparator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.order.StopBuyOrder;

public class StopBuyOrderComparator implements Comparator<StopBuyOrder> {
	
	private static Logger logger = LoggerFactory.getLogger(StopBuyOrderComparator.class);

	public StopBuyOrderComparator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(StopBuyOrder o1,  StopBuyOrder o2) {
		logger.info(o1 + " compared to " + o2);
		if (o1 == o2) {
			return 0;
		}
		int compared = Integer.compare(o1.getPrice(), o2.getPrice());
		if (compared == 0) {
			compared = o1.compareTo(o2);
		}
		return compared;
	}

}
