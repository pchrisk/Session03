package edu.uw.ck.broker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.order.Order;

public abstract class AbstractOrderImpl extends Object implements Order {
	
	private static Logger logger = LoggerFactory.getLogger(AbstractOrderImpl.class);

	

	private String accountId;
	private int numberOfShares;
	private String stockTicker;
	private int orderId;
	
	public AbstractOrderImpl(){
		
	}
	
	/**
	 * Instantiates a new abstract order impl.
	 *
	 * @param accountId the account id
	 * @param numberofShares the number of shares
	 * @param stockTicker the stock ticker
	 */
	public AbstractOrderImpl(String accountId, int numberOfShares,
			String stockTicker) {
		super();
		this.accountId = accountId;
		this.numberOfShares = numberOfShares;
		this.stockTicker = stockTicker;
		
		/*
		 * Assign orderID, sequentially as orders are created and can be used to dtermine
		 * if one order where created before or after another.
		 * 
		 * this.orderId = nextOrderId
		 */
		
	}
	
	

	@Override
	public int compareTo(Order o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAccountId() {
		return this.accountId;
	}

	@Override
	public int getNumberOfShares() {		
		return this.numberOfShares;
	}

	@Override
	public int getOrderId() {
		return this.orderId;
	}

	@Override
	public String getStockTicker() {
		return this.stockTicker;
	}

	@Override
	public boolean isBuyOrder() {
		// TODO Auto-generated method stub
		return false;
	}

	
	/* 
	 * the value of the order at the given per share price, 
	 * sell orders will yield a positive value buy orders 
	 * a negative value
	 */
	@Override
	public int valueOfOrder(int pricePerShare) {
		int value = 0;
		int sharePrice = pricePerShare;
		if(sharePrice >= 0) {
			value = this.getNumberOfShares() * sharePrice;
		}
		return value;
	}

}
