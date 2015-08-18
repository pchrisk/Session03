package edu.uw.ck.exchange;

public abstract class ProtocalConstants {

	static int CMD_ELEMENT = 0;
	
	
	static int EXE_TRADE_CMD_TYPE_ELEMENT = 1;

	static int EXEC_TRADE_CMD_ACCOUNT_ELEMENT = 2;
	
	static int EXE_TRADE_CMD_TICKER_ELEMENT = 3;

	static int EXE_TRADE_CMD_SHARES_ELEMENT = 4;

	
	static int INVALID_STOCK = -1;

	
	static int PRICE_CHG_EVNT_TICKER_ELEMENT = 1;
	
	static int PRICE_CHG_EVNT_PRICE_ELEMENT = 2;

	

	static int QUOTE_CMD_TICKER_ELEMENT = 1;

	static String ELEMENT_DELIMETER = ":";

	static String ENCODING = "UTF-8";
	
	

	static String SELL_ORDER = "SELL_ORDER";
	
	static String BUY_ORDER = "BUY_ORDER";
	
	static String OPEN_EVNT = "OPEN_EVNT";

	static String CLOSED_EVNT = "CLOSED_EVNT";
	
	static String OPEN_STATE = "OPEN";

	static String CLOSED_STATE = "CLOSED";

	static String EXE_TRADE_CMD = "EXE_TRADE_CMD";

	static String GET_QUOTE_CMD = "GET_QUOTE_CMD";

	static String GET_STATE_CMD = "GET_STATE_CMD";

	static String GET_TICKERS_CMD = "GET_TICKERS_CMD";

	static String PRICE_CHG_EVNT = "PRICE_CHG_EVNT";


}
