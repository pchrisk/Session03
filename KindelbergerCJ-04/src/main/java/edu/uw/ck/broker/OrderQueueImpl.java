package edu.uw.ck.broker;

import java.util.Comparator;
import java.util.TreeSet;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.broker.OrderProcessor;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;

public class OrderQueueImpl<T extends Order> implements OrderQueue<T>, Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(OrderQueueImpl.class);
	
	private OrderDispatchFilter<?, T> dispatchFilter;
	
	private TreeSet<T> orderQueue;
	
	private OrderProcessor orderProcessor;
	
	private final Lock lock = new ReentrantLock();
	
	private final Condition dispatchCondition = lock.newCondition();

	private Thread dispatchThread;
	
	

	public OrderQueueImpl(OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>();
		startDispatchThread();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);				
	}	

	public OrderQueueImpl(Comparator<T> compare, OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>(compare);
		startDispatchThread();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);				
	}
	
	private void startDispatchThread() {
		dispatchThread = new Thread(this);
		dispatchThread.setDaemon(true);
		dispatchThread.start();		
	}

	public void run() {
		while (true) {
			lock.lock();

			Order order = null;
			try {
				while ((order = dequeue()) == null) {

					try {
						dispatchCondition.await();
					} catch (InterruptedException e) {
						logger.error("Problem waiting on thread", e);
					}
				}

			} finally {
				lock.unlock();
			}
			OrderProcessor orderProc = orderProcessor;
			if (orderProc != null) {
				orderProc.process(order);
			}
		}
	}
	
	@Override
	public T dequeue() {
		T order = null;
		lock.lock();
		try {
			if (!orderQueue.isEmpty()) {
				order = orderQueue.first();
				if (dispatchFilter.check(order)) {
					orderQueue.remove(order);
				} else {
					order = null;
				}
			}
		} finally {
			lock.unlock();
		}

		return order;
	}

	@Override
	public void dispatchOrders() {
		lock.lock();
		try {
			dispatchCondition.signal();
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public void enqueue(T order) {
		lock.lock();
		try {
			orderQueue.add(order);
		} finally {
			lock.unlock();
		}
		dispatchOrders();
	}

	@Override
	public void setOrderProcessor(OrderProcessor proc) {
		orderProcessor = proc;		
	}	
}
