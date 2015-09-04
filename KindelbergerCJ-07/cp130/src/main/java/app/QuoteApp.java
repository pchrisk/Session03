package app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;


public class QuoteApp {

    
    private static String exec(final String symbol, final String format , 
    		                   final boolean post, final boolean useJson) throws IOException {
    HttpURLConnection conn = null;
    final String baseUrl = "http://localhost:8080/StockQuote/QuoteServlet";
        if (post) {
            // Illustrate sending a request document in the request body
            String body;
            String contentType;
            if (useJson) {
            	contentType = "application/json";
                final Properties props = new Properties();
                props.put("symbol", symbol);
                props.put("rstype", format);
                final StringWriter strWrtr = new StringWriter();
                final ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(strWrtr, props);
                body = strWrtr.toString();
            } else {
            	contentType = "application/x-www-form-urlencoded";
            	body = String.format("symbol=%s&rstype=%s", symbol, format);
            }
            
            byte[] bodyBytes = body.getBytes("UTF-8");
            final URL url = new URL(baseUrl);
            conn = (HttpURLConnection)url.openConnection();
        	conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("charset", "UTF-8");
            conn.setRequestProperty("Content-Type", contentType);
            conn.setRequestProperty("Content-Length", "" + Integer.toString(bodyBytes.length));
            final OutputStream out = conn.getOutputStream();
            out.write(bodyBytes);
            out.close();
        } else {
            final String urlStr = String.format("%s?symbol=%s&rstype=%s", baseUrl, symbol, format);
            final URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
        }
        
        
        final InputStream in = conn.getInputStream();
        final Reader rdr = new InputStreamReader(in);
        final char buf[] = new char[1024];
        int len = 0;
        final StringWriter strWrtr = new StringWriter();

        while ((len = rdr.read(buf)) != -1) {
        	strWrtr.write(buf, 0, len);
        }
        rdr.close();
        return strWrtr.toString();
    }

    
    public static void main(final String[] args) throws IOException {
        final String symbol = "GOOG";
        String result;
        
        System.out.println("Processing GET requests...");
        System.out.println("JSON:");
        result = exec(symbol, "json", false, false);
        System.out.println(result);
        
        System.out.println();
        System.out.println("plain:");
        result = exec(symbol, "plain", false, false);
        System.out.println(result);
        
        System.out.println();
        System.out.println("HTML:");
        result = exec(symbol, "html",false, false);
        System.out.println(result);
        
        System.out.println();
        System.out.println("XML:");
        result = exec(symbol, "xml", false, false);
        System.out.println(result);
        
        System.out.println();
        System.out.println();
        System.out.println("Processing POST requests ");
        System.out.println();

        System.out.println("JSON:");
        result = exec(symbol, "json", true, false);
        System.out.println(result);
        
        System.out.println();
        System.out.println("plain:");
        result = exec(symbol, "plain", true, false);
        System.out.println(result);

        System.out.println();
        System.out.println("HTML:");
        result = exec(symbol, "html", true, false);
        System.out.println(result);

        System.out.println();
        System.out.println("XML:");
        result = exec(symbol, "xml", true, false);

        
    }

}