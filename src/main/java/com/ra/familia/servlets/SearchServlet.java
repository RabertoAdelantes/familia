package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

import java.io.*;
import java.util.Set;

@WebServlet(name = "SearchServlet", displayName = "Search Servlet", urlPatterns = {
		"/search", "/Search" }, loadOnStartup = 1)
public class SearchServlet extends GenericServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServlet.class);

	private static final long serialVersionUID = 8781195695257213199L;

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean person = getRequestParams(req);
		Set<PersonBean> persons = personService.getItemsByName(person);
		req.getSession().setAttribute(SEARCH_SET, persons);
		req.getRequestDispatcher(SEARCH_JSP).forward(req, resp);
	}


	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean person = (PersonBean) req.getSession().getAttribute(
				USER_BEAN);
		String redirectUrl = SEARCH_JSP;
		if (person == null) {
			redirectUrl = INDEX_JSP;
		} 
		req.getRequestDispatcher(redirectUrl).forward(req, resp);
	}
}
