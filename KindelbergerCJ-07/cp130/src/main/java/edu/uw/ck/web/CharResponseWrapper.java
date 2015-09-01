package edu.uw.ck.web;

import java.io.CharArrayWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class CharResponseWrapper extends HttpServletResponseWrapper {
	
	private final CharArrayWriter output;

	public CharResponseWrapper(final HttpServletResponse response) {
		super(response);
		output = new CharArrayWriter();
	}

}
