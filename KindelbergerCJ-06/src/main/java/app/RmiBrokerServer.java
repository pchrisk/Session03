package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uw.ck.account.AccountManagerFactoryImpl;
import edu.uw.ck.broker.BrokerFactoryImpl;
import edu.uw.ck.dao.DaoFactoryImpl;
import edu.uw.ck.rmibroker.RemoteBrokerGateway;
import edu.uw.ck.rmibroker.RemoteBrokerGatewayImpl;
import edu.uw.ext.framework.account.AccountManager;
import edu.uw.ext.framework.account.AccountManagerFactory;
import edu.uw.ext.framework.broker.Broker;
import edu.uw.ext.framework.broker.BrokerFactory;
import edu.uw.ext.framework.dao.AccountDao;
import edu.uw.ext.framework.dao.DaoFactory;
import edu.uw.ext.framework.dao.DaoFactoryException;
import edu.uw.ext.framework.exchange.StockExchange;
import edu.uw.ext.framework.exchange.StockExchangeSpi;

public class RmiBrokerServer {
	
	private static Logger logger = LoggerFactory.getLogger(RmiBrokerServer.class);
	
	public static final String BROKER_NAME = "RTrade";
	public static final int RMI_SERVICE_PORT = 11099;
	

	public static void main(final String[] args) throws DaoFactoryException, IOException {
		
		StockExchange exchange = ExchangeFactory.newTestStockExchange();
		((StockExchangeSpi)exchange).open();
		
		DaoFactory daoFactory = new DaoFactoryImpl();
		AccountDao dao = daoFactory.getAccountDao();
		
		AccountManagerFactory acctMgrFactory = new AccountManagerFactoryImpl();
		AccountManager acctMgr = acctMgrFactory.newAccountManager(dao);
		
		BrokerFactory brokerFactory = new BrokerFactoryImpl();
		Broker broker = brokerFactory.newBroker(BROKER_NAME, acctMgr, exchange);
		
		RemoteBrokerGateway remoteGateway = new RemoteBrokerGatewayImpl(broker);
		
		Registry reg = null;
		
		reg = LocateRegistry.createRegistry(RMI_SERVICE_PORT);
		logger.info("RMI Server listening on port: " + RMI_SERVICE_PORT);
		
		reg.rebind(BROKER_NAME, remoteGateway);
		logger.info("Remote broker is available for processing.");
		
		InputStream is = System.in;
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		System.out.println("***** Press Enter to quit *****");
		br.readLine();
		
		UnicastRemoteObject.unexportObject(remoteGateway, true);
		
	}
	
	
}
