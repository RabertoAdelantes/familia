package com.ra.familia.filters;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;

@WebFilter("/IdiomasFilter")
public class IdiomasFilter implements Filter {

	private static final String LANG = "lang";
	private static final String LOCALE_REGEXP = "[a-z]{1,2}_[A-Z]{1}[a-z]{1}";

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		String lang = request.getParameter(LANG);
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if (lang != null && lang.matches(LOCALE_REGEXP)) {
			Config.set(httpRequest.getSession(), Config.FMT_LOCALE, new Locale(
					lang.split("_")[0], lang.split("_")[1]));
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
