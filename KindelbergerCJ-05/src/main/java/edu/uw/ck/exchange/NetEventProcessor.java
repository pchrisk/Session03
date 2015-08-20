package edu.uw.ck.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;

public class NetEventProcessor implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(NetEventProcessor.class);

	public NetEventProcessor() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try (Multicastsocket eventsocket  new multicastsocket)
		
		DataGramPacket packet = new DatagramPacket(buf, )
		
		while (true){
			eventSocket.recieve(packet)
			
			
			switch (eventType) {
			
			case price_change_event
			fireListeners(exchagneEvent.newPriceChangeEvent(this, ticker, price));
			case open_event
			fireListgeners(ExchagneEvent.newOPenEvent(this)):
			case close_event
			fireListeners(ExchangeEvent.newCloseEvent(this));
			break;
			}
			
			addExchangeListern()
			
			removeExchagneListerner()
			
			private void fireListenerers() exchagneEvent evnt)
			
//			copy verbatim ok
		}

	}

}
