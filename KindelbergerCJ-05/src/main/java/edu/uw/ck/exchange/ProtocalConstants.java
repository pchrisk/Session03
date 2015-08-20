package edu.uw.ck.exchange;

public abstract class ProtocalConstants {

	static final int CMD_ELEMENT = 0;
	
	
	static final int EXE_TRADE_CMD_TYPE_ELEMENT = 1;

	static final int EXE_TRADE_CMD_ACCOUNT_ELEMENT = 2;
	
	static final int EXE_TRADE_CMD_TICKER_ELEMENT = 3;

	static final int EXE_TRADE_CMD_SHARES_ELEMENT = 4;

	
	static final int INVALID_STOCK = -1;

	
	static final int PRICE_CHG_EVNT_TICKER_ELEMENT = 1;
	
	static final int PRICE_CHG_EVNT_PRICE_ELEMENT = 2;

	

	static final int QUOTE_CMD_TICKER_ELEMENT = 1;

	static final String ELEMENT_DELIMETER = ":";

	static final String ENCODING = "UTF-8";
	
	

	static final String SELL_ORDER = "SELL_ORDER";
	
	static final String BUY_ORDER = "BUY_ORDER";
	
	static final String OPEN_EVNT = "OPEN_EVNT";

	static final String CLOSED_EVNT = "CLOSED_EVNT";
	
	static final String OPEN_STATE = "OPEN";

	static final String CLOSED_STATE = "CLOSED";

	static final String EXE_TRADE_CMD = "EXE_TRADE_CMD";

	static final String GET_QUOTE_CMD = "GET_QUOTE_CMD";

	static final String GET_STATE_CMD = "GET_STATE_CMD";

	static final String GET_TICKERS_CMD = "GET_TICKERS_CMD";

	static final String PRICE_CHG_EVNT = "PRICE_CHG_EVNT";


}
