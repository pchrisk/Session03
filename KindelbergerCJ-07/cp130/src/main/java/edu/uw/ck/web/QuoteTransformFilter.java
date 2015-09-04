package edu.uw.ck.web;

import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;


public class QuoteTransformFilter implements Filter {
	
	private ServletContext ctx;
    private String toHtmlPath;
    private String toJsonPath;
    private String toPlainPath;
    

	public QuoteTransformFilter() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		CharResponseWrapper wrapper = new CharResponseWrapper(
				(HttpServletResponse) response);
		chain.doFilter(request, wrapper);

		String xml = wrapper.toString();
		String responseStr = xml;
		Source styleSrc = null;

		String rsType = request.getParameter("rstype");

		if (!rsType.equals("xml")) {
			if (rsType.equals("json")) {
				response.setContentType("text/plain");
				styleSrc = new StreamSource(toJsonPath);
			} else if (rsType.equals("plain")) {
				response.setContentType("text/plain");
				styleSrc = new StreamSource(toPlainPath);
			} else {
				response.setContentType("text/html");
				styleSrc = new StreamSource(toHtmlPath);
			}

			StringReader strRdr = new StringReader(xml);
			Source xmlSource = new StreamSource(strRdr);

			try {
				final TransformerFactory transformerFactory = TransformerFactory
						.newInstance();
				Transformer transformer;
				transformer = transformerFactory.newTransformer(styleSrc);
				final CharArrayWriter caw = new CharArrayWriter();
				final StreamResult result = new StreamResult(caw);
				ctx.log("Transforming...");
				transformer.transform(xmlSource, result);
				responseStr = caw.toString();

			} catch (final TransformerConfigurationException ex) {
				ctx.log("Config error", ex);
				return;
			} catch (final TransformerException e) {
				ctx.log("Transform error", e);
				return;
			}
		}
		response.setContentLength(responseStr.length());
		final PrintWriter out = response.getWriter();
		out.write(responseStr);
		out.close();
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig filterCfg) throws ServletException {
		ctx = filterCfg.getServletContext();
		String path = filterCfg.getInitParameter("htmlXslt");
		toHtmlPath = ctx.getRealPath(path);
		ctx.log(toHtmlPath);
		path = filterCfg.getInitParameter("jsonXslt");
		toJsonPath = ctx.getRealPath(path);
		ctx.log(toJsonPath);
		path = filterCfg.getInitParameter("plainXslt");
		toPlainPath = ctx.getRealPath(path);
		ctx.log(toPlainPath);

	}

}
