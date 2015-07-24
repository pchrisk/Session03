package edu.uw;

import static org.junit.Assert.*;

import org.junit.Test;

import test.AbstractOrderQueueTest;

import edu.uw.ext.framework.broker.OrderDispatchFilter;
import edu.uw.ext.framework.broker.OrderQueue;
import edu.uw.ext.framework.order.Order;
import edu.uw.ext.framework.order.StopBuyOrder;
import edu.uw.ext.framework.order.StopSellOrder;

/*****************************************************************************
 * Replace these imports with the import of your implementing classes.       *
 *****************************************************************************/
import edu.uw.ck.broker.OrderQueueImpl;
import edu.uw.ck.broker.StopBuyOrderComparator;
import edu.uw.ck.broker.StopSellOrderComparator;

/**
 * Concrete subclass of AbstractQueueTest, provides implementations of the 
 * createStopBuyOrderQueue, createStopSellOrderQueue and createAnyOrderQueue
 * methods which create instances of "my" OrderQueue implementation class, using
 * "my" Comparator implementations.
 */
public class OrderQueueTest extends AbstractOrderQueueTest {
    /**
     * Creates an instance of "my" OrderQueue implementation class, using
     * an instance of "my" implementation of Comparator that is intended to
     * order StopBuyOrders.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    protected final OrderQueue<StopBuyOrder> createStopBuyOrderQueue(
                        final OrderDispatchFilter<?, StopBuyOrder> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue and Comparator.   *
         *********************************************************************/
        return new OrderQueueImpl<StopBuyOrder>(new StopBuyOrderComparator(), filter);
    }

    /**
     * Creates an instance of "my" OrderQueue implementation class, using
     * an instance of "my" implementation of Comparator that is intended to
     * order StopSellOrders.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    protected final OrderQueue<StopSellOrder> createStopSellOrderQueue(
                          final OrderDispatchFilter<?, StopSellOrder> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue and Comparator.   *
         *********************************************************************/
        return new OrderQueueImpl<StopSellOrder>(new StopSellOrderComparator(), filter);
    }
    
    /**
     * Creates an instance of "my" OrderQueue implementation class, the queue
     * will order the Orders according to their natural ordering.
     *
     * @param filter the OrderDispatch filter to be used
     * 
     * @return a new OrderQueue instance
     */
    protected final OrderQueue<Order> createAnyOrderQueue(
                            final OrderDispatchFilter<?, Order> filter) {
        /*********************************************************************
         * This needs to be an instance of your OrderQueue.                  *
         *********************************************************************/
        return new OrderQueueImpl<Order>(filter);
    }

}
