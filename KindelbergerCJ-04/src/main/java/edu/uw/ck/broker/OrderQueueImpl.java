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
//	private BlockingQueue<T> orderQueue;
	
	private OrderProcessor orderProcessor;
	
	private final Lock lock = new ReentrantLock();
	
	private final Condition dispatchCondition = lock.newCondition();
	
//	private AtomicBoolean isQueuedToPool = new AtomicBoolean(false);

	private Thread dispatchThread;
	
	

	public OrderQueueImpl(OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>();
//		orderQueue = new PriorityBlockingQueue<T>();
		startDispatchThread();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
				
	}	

	

	public OrderQueueImpl(Comparator<T> compare, OrderDispatchFilter<?, T> filter) {
		orderQueue = new TreeSet<>(compare);
//		orderQueue = new PriorityBlockingQueue<>(10, compare);
		startDispatchThread();
		dispatchFilter = filter;
		dispatchFilter.setOrderQueue(this);
				
	}
	
	private void startDispatchThread() {
		dispatchThread = new Thread(this);
		dispatchThread.setDaemon(true);
		dispatchThread.start();
		
	}

	public void run(){
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
//				order = dequeue();
//					if (order == null) {
//						isQueuedToPool.set(false);
//						break;
//					}
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
//					try {
						orderQueue.remove(order);
//					} catch (InterruptedException e) {
//						logger.error("Error in removing from queue.", e);
//					}
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
//		T order;
		try {
			dispatchCondition.signal();
//		while ((order = dequeue()) != null) {
//			if (orderProcessor != null) {
//				orderProcessor.process(order);
//			}	
//		}
		} finally {
			lock.unlock();
		}
		
	}

	@Override
	public void enqueue(T order) {
		lock.lock();
		try {
//			if (!orderQueue.contains(order)) {
				orderQueue.add(order);
//			}
			
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
