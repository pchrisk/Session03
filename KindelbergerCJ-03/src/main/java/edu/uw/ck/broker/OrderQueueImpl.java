package edu.uw.ck.broker;

import java.util.Comparator;
import java.util.TreeSet;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;

public class OrderQueueImpl<T extends Order> implements OrderQueue<T> {
	
	private OrderDispatchFilter<?, T> dispatchFilter;
	
	private TreeSet<T> orderQueue;
	
	private OrderProcessor orderProcessor;
	

	public OrderQueueImpl(OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<T>();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
	}	

	public OrderQueueImpl(Comparator<T> compare, OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>(compare);
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
	}


	@Override
	public T dequeue() {
		T order = null;
		if (!orderQueue.isEmpty()) {
			order = orderQueue.first();
			if (dispatchFilter.check(order)) {
				orderQueue.remove(order);
			} else {
				order = null;
			}
		}
		
		return order;
	}

	@Override
	public void dispatchOrders() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enqueue(T order) {
		orderQueue.add(order);
		dispatchOrders();
		
	}

	@Override
	public void setOrderProcessor(OrderProcessor proc) {
		// TODO Auto-generated method stub
		
	}

	
}
