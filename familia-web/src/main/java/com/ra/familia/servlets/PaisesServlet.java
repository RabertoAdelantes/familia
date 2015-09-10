package com.ra.familia.servlets;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.jstl.core.Config;

@WebServlet(name = "Etnos Servlet", displayName = "Etnos Servlet", urlPatterns = { "/locale" })
public class PaisesServlet extends GenericServlet {

	private static final String LANG = "lang";
	private static final String REFERER = "referer";
	private static final String LOCALE_REGEXP = "[a-z]{1,2}_[A-Z]{1}[a-z]{1}";

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String lang = req.getParameter(LANG);
		if (lang != null && lang.matches(LOCALE_REGEXP)) {
			Config.set(req.getSession(), Config.FMT_LOCALE,
					new Locale(lang.split("_")[0], lang.split("_")[1]));
		}
		res.sendRedirect(req.getHeader(REFERER));
	}
}
