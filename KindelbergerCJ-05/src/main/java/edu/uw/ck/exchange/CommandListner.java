package edu.uw.ck.exchange;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.broker.OrderManagerImpl;
import edu.uw.ext.framework.exchange.StockExchange;

public class CommandListner implements Runnable {
	
	private static Logger logger = LoggerFactory.getLogger(CommandListner.class);

	
	private int cmdPort = 0;
	private volatile boolean listening = true;
	private ServerSocket serverSocket = null;
	private StockExchange realExchange = null;
	
	private ExecutorService rqstExecutor = Executors.newCachedThreadPool();
	
	public CommandListner(int commandPort, StockExchange realExchange) {
		cmdPort = commandPort;
		this.realExchange = realExchange;
	}

	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(cmdPort);
			
			while (listening) {
				Socket sock = null;
				
				sock = serverSocket.accept();
				
				rqstExecutor.execute(new CommandHandler(sock, realExchange));
				
			}
		} catch (IOException e) {
			logger.error("Server socket error: ", e);
		}
		
		
//		try {
//			
//			servSock = new ServerSocket(commandport);
//			
//			while (listening) {
//				try {
//					sock = servSock.accept();
//				} catch {
//					
//				}
//				
//				if (sock ==null) {
//					continue;
//				}
//				
//				requestExecutor.execute(new CommandHandler(sock, realExchange);
//				
//			
//		} catch {
//			
//		} finally {
//			terminate();
//		}
//		
//			
//		}
//
//	}
	
//	public void terminate() {
//		listening = fasle;
//		
//		try {
////			shutdown requestExecutor
//		}
	}
	
	public void terminate() {
		listening = false;
		try {
			serverSocket.close();
			rqstExecutor.shutdown();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
