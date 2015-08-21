package edu.uw.ck.exchange;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

import javax.swing.event.EventListenerList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.ExchangeEvent;
import edu.uw.ext.framework.exchange.ExchangeListener;
import static edu.uw.ck.exchange.ProtocalConstants.*;

public class NetEventProcessor implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(NetEventProcessor.class);
	
	private static int BUFFER_SIZE = 1024;
	private String eventIpAddress = "";
	private int eventPort = 0;
	private EventListenerList lList = new EventListenerList();
	
	

	public NetEventProcessor(String eventIpAddress, int eventPort) {
		this.eventIpAddress = eventIpAddress;
		this.eventPort = eventPort;
	}

	@Override
	public void run() {
		try (MulticastSocket mSocket = new MulticastSocket(eventPort)) {
			InetAddress eventGroup = InetAddress.getByName(eventIpAddress);
			
			logger.info("Getting events from " + eventIpAddress + ":" + eventPort);
			
			byte[] buffer = new byte[BUFFER_SIZE];
					
			DatagramPacket dgPacket = new DatagramPacket(buffer, buffer.length);
			
			while (true) {
				mSocket.receive(dgPacket);
				
				String msg = new String(dgPacket.getData(), dgPacket.getOffset(), dgPacket.getLength(), ENCODING);
				String[] msgParts = msg.split(ELEMENT_DELIMETER);
				String eventType = msgParts[CMD_ELEMENT];
				
				switch (eventType) {
					case PRICE_CHG_EVNT:
						String priceAsString = msgParts[PRICE_CHG_EVNT_PRICE_ELEMENT];
						String ticker = msgParts[PRICE_CHG_EVNT_TICKER_ELEMENT];
						int price = -1;
						
						price = Integer.parseInt(priceAsString);
						
						fireListeners(ExchangeEvent.newPriceChangedEvent(this, ticker, price));
						
					case OPEN_EVNT:
						fireListeners(ExchangeEvent.newOpenedEvent(this));
					case CLOSED_EVNT:
						fireListeners(ExchangeEvent.newClosedEvent(this));
						
					default:
						break;
				}						
			}
		} catch (final IOException e) {
			logger.error("NetEventProcessor error ", e);
		}
	}
		
//		try (Multicastsocket eventsocket  new multicastsocket)
//		
//		DataGramPacket packet = new DatagramPacket(buf, )
//		
//		while (true){
//			eventSocket.recieve(packet)
//			
//			
//			switch (eventType) {
//			
//			case price_change_event
//			fireListeners(exchagneEvent.newPriceChangeEvent(this, ticker, price));
//			case open_event
//			fireListgeners(ExchagneEvent.newOPenEvent(this)):
//			case close_event
//			fireListeners(ExchangeEvent.newCloseEvent(this));
//			break;
//			}
//			
//						
//			private void fireListenerers() exchagneEvent evnt)
			
//			copy verbatim ok


	private void fireListeners(ExchangeEvent evnt) {
		ExchangeListener[] listeners;
		listeners = lList.getListeners(ExchangeListener.class);

		for (ExchangeListener listener : listeners) {
			switch (evnt.getEventType()) {
			case OPENED:
				listener.exchangeOpened(evnt);
				break;

			case CLOSED:
				listener.exchangeClosed(evnt);
				break;

			case PRICE_CHANGED:
				listener.priceChanged(evnt);
				break;

			default:
				logger.warn("Attempted to fire an unknown exchange event: "
						+ evnt.getEventType());
				break;
			}
		}

	}

	public void removeExchangeListener(ExchangeListener l) {
		lList.remove(ExchangeListener.class, l);
		
	}

	public void addExchangeListener(ExchangeListener l) {
		lList.add(ExchangeListener.class, l);
		
	}

}
