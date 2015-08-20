package edu.uw.ck.exchange;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.StockExchange;

public class CommandListner implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(CommandListner.class);

	
	private int
	
	
	public CommandListner(int commandPort, StockExchange realExchange) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		try {
			
			servSock = new ServerSocket(commandport);
			
			while (listening) {
				try {
					sock = servSock.accept();
				} catch {
					
				}
				
				if (sock ==null) {
					continue;
				}
				
				requestExecutor.execute(new CommandHandler(sock, realExchange);
				
			
		} catch {
			
		} finally {
			terminate();
		}
		
			
		}

	}
	
	public void terminate() {
		listening = fasle;
		
		try {
//			shutdown requestExecutor
		}
	}

}
