package com.ra.familia.servlets;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "IndexServlet", displayName = "Index Servlet", urlPatterns = {
		"/init", "/init.jsp" }, loadOnStartup = 1)
public class IndexServlet extends GenericServlet {

	private static final long serialVersionUID = 4583682557004705736L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nextStep = SEARCH_URL;
		boolean isCookied = false;
		if (req.getCookies() != null) {
			for (Cookie cookie : req.getCookies()) {
				if (cookie.getName().equals(USER_BEAN)) {
					isCookied = true;
				}
			}
		}
		if (!isCookied) {
			nextStep = INDEX_JSP;
		}
		resp.sendRedirect(nextStep);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
}
