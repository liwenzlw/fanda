package com.yisi.back.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.commons.lang.StringEscapeUtils;

public class XSSEscapeFilter implements Filter {

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		XSSEscapeRequestWrapper xSSEscapeRequestWrapper = new XSSEscapeRequestWrapper((HttpServletRequest)request);
		chain.doFilter(xSSEscapeRequestWrapper, response);
	}

	@Override
	public void destroy() {
	}
}

class XSSEscapeRequestWrapper extends HttpServletRequestWrapper {

	private HttpServletRequest request;

	public XSSEscapeRequestWrapper(HttpServletRequest request) {
		super(request);
		this.request = request;
	}

	@Override
	public String getParameter(String name) {
		return StringEscapeUtils.escapeHtml(super.getParameter(name));
	}

	@Override
	public String[] getParameterValues(String name) {
		String[] values = super.getParameterValues(name);
		if (values != null) {
			int length = values.length;
			String[] escapseValues = new String[length];
			for (int i = 0; i < length; i++) {
				escapseValues[i] = StringEscapeUtils.escapeHtml(values[i]);
			}
			return escapseValues;
		}
		return values;
	}
}
