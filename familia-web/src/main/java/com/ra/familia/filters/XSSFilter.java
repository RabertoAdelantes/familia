package com.ra.familia.filters;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

public class XSSFilter implements Filter {

	/**
	 * initialize and compile restrictions PATTERNS to sanitize requests
	 */
	private static final Pattern[] PATTERNS = new Pattern[] {
			// Script fragments
			Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE),
			// src='...'
			Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL),
			Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"",
					Pattern.CASE_INSENSITIVE | Pattern.MULTILINE
							| Pattern.DOTALL),
			// lonely script tags
			Pattern.compile("</script>", Pattern.CASE_INSENSITIVE),
			Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),
			// eval(...)
			Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),
			// expression(...)
			Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),
			// javascript:...
			Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE),
			// vbscript:...
			Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE),
			// onload(...)=...
			Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL),
			Pattern.compile("\""),
			Pattern.compile("\'"),
			Pattern.compile("`"),
			Pattern.compile("<"),
			Pattern.compile(">"),
			Pattern.compile(
					"((&lt;)(\\s){0,}script(\\s){0,}(&gt;)(.*?)((&lt;)(\\s){0,}\\/(\\s){0,}script(\\s){0,}(&gt)))",
					Pattern.CASE_INSENSITIVE),
			Pattern.compile(
					"((\\%3C)(\\s){0,}script(\\s){0,}(\\%3E)(.*?)((%3C)(\\s){0,}\\/(\\s){0,}script(\\s){0,}(%3E)))",
					Pattern.CASE_INSENSITIVE),
			Pattern.compile("document(.*)\\.(.*)cookie"),
			Pattern.compile("eval(\\s*)\\("),
			Pattern.compile("setTimeout(\\s*)\\("),

			Pattern.compile("setInterval(\\s*)\\("),
			Pattern.compile("execScript(\\s*)\\("),
			Pattern.compile("(?i)javascript(?-i):"),
			Pattern.compile("(?i)onclick(?-i)"),
			Pattern.compile("(?i)ondblclick(?-i)"),
			Pattern.compile("(?i)onmouseover(?-i)"),
			Pattern.compile("(?i)onmousedown(?-i)"),
			Pattern.compile("(?i)onmouseup(?-i)"),
			Pattern.compile("(?i)onmousemove(?-i)"),
			Pattern.compile("(?i)onmouseout(?-i)"),
			Pattern.compile("(?i)onchange(?-i)"),
			Pattern.compile("((?i)onfocus(?-i))"),
			Pattern.compile("(?i)onblur(?-i)"),
			Pattern.compile("(?i)onkeypress(?-i)"),
			Pattern.compile("(?i)onkeyup(?-i)"),
			Pattern.compile("(?i)onkeydown(?-i)"),
			Pattern.compile("(?i)onload(?-i)"),
			Pattern.compile("(?i)onreset(?-i)"),
			Pattern.compile("(?i)onselect(?-i)"),
			Pattern.compile("(?i)onsubmit(?-i)"),
			Pattern.compile("(?i)onunload(?-i)"),
			Pattern.compile("&#x61;&#x6C;&#x65;&#x72;&#x74;"),

			Pattern.compile("([\\'][\\s]?[(OR|AND|IN)]{2,3}[\\s]?[\\'].*)",
					Pattern.CASE_INSENSITIVE)

	};

	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(final ServletRequest request,
			final ServletResponse response, final FilterChain filterChain)
			throws IOException, ServletException {
		if (!(request instanceof HttpServletRequest)
				|| !(response instanceof HttpServletResponse)) {
			filterChain.doFilter(request, response);
		} else if (isSanitized(((HttpServletRequest) request).getRequestURI())) {
			filterChain.doFilter(new FilteredRequest(request), response);
		} else {
			HttpServletResponse httpResponse = (HttpServletResponse) response;
			String uri = ((HttpServletRequest) request).getRequestURI();
			String newURI = sanitize(uri);
			httpResponse.sendRedirect(newURI);
		}
	}

	private static Iterable<Pattern> getMatchedPatterns(final String input) {
		Iterable<Pattern> result = Collections.emptyList();
		if (input != null) {
			Predicate<Pattern> isMatch = new Predicate<Pattern>() {
				@Override
				public boolean apply(final Pattern pattern) {
					Matcher matcher;
					matcher = pattern.matcher(input);
					return matcher.find();
				}
			};
			result = Iterables.filter(Lists.newArrayList(PATTERNS), isMatch);
		}
		return result;
	}

	private static boolean isSanitized(final String input) {
		return Iterables.isEmpty(getMatchedPatterns(input));
	}

	private static String sanitize(final String input) {
		boolean isSanitized = false;
		String result = input;
		while (!isSanitized) {
			result = doSanitize(result);
			isSanitized = isSanitized(result);
		}
		return result;
	}

	private static String doSanitize(final String input) {
		String result = input;
		for (final Pattern pattern : getMatchedPatterns(input)) {
			result = pattern.matcher(result).replaceAll("");
		}
		return result;
	}

	@Override
	public void destroy() {

	}

	static class FilteredRequest extends HttpServletRequestWrapper {

		public FilteredRequest(final ServletRequest request) {
			super((HttpServletRequest) request);
		}

		@Override
		public String getParameter(final String paramName) {
			String result = sanitize(super.getParameter(paramName));
			return result;
		}

		@Override
		public String[] getParameterValues(final String paramName) {
			String values[] = super.getParameterValues(paramName);
			if (values != null) {
				for (int index = 0; index < values.length; index++) {
					values[index] = sanitize(values[index]);
				}
			}
			return values;
		}

		@Override
		public Map<String, String[]> getParameterMap() {
			Map<String, String[]> newMap = new HashMap<String, String[]>();
			for (Entry<String, String[]> entry : super.getParameterMap()
					.entrySet()) {
				newMap.put(entry.getKey(), getParameterValues(entry.getKey()));
			}

			return Collections.unmodifiableMap(newMap);
		}

	}
}
