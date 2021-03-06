package edu.uw.ck.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.uw.ext.quote.YahooQuote;

public class YahooStockQuoteServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ServletContext ctx;

	public YahooStockQuoteServlet() {
		// TODO Auto-generated constructor stub
	}
	
	public void init(ServletConfig servletCfg) {
		ctx = servletCfg.getServletContext();
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceRequest(request, response);
	}

	/**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        serviceRequest(request, response);
    }
    
    private void serviceRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String symbol = request.getParameter("symbol");
		YahooQuote quote = YahooQuote.getQuote(symbol);
		ctx.log("Getting quote for " + symbol);
		String responseXml = String.format("Symbol: %S \n Price: %S", quote.getSymbol(), quote.getPrice());
		
		response.setContentType("text/xml");
		response.setContentLength(responseXml.length());
		response.getWriter().print(responseXml);
		
		
	}

}
