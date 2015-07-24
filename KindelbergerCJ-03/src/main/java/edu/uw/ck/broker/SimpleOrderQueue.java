package edu.uw.ck.broker;

import java.util.Comparator;
import java.util.TreeSet;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;

public class SimpleOrderQueue<T extends Order> implements OrderQueue<T> {
	
	private OrderDispatchFilter<?, T> dispatchFilter;
	
	private TreeSet<T> orderQueue;
	
	private OrderProcessor orderProcessor;
	

	public SimpleOrderQueue(OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<T>();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
	}	

	public SimpleOrderQueue(Comparator<T> compare, OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>(compare);
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
	}


	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void dispatchOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueue(T arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOrderProcessor(OrderProcessor arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueue(E order) {
		// TODO Auto-generated method stub
		
	}

}
