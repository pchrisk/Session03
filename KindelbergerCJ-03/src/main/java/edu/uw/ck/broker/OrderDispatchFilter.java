package edu.uw.ck.broker;

import edu.uw.ext.framework.order.Order;

public class OrderDispatchFilter implements Order {

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAccountId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumberOfShares() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getOrderId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getStockTicker() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isBuyOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int valueOfOrder(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}
