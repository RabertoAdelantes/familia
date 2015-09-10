package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "SearchServlet", displayName = "Search Servlet", urlPatterns = {
		"/search", "/Search" })
public class SearchServlet extends GenericServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServlet.class);

	private static final long serialVersionUID = 8781195695257213199L;

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean person = getRequestParams(req);
		Collection<PersonBean> persons = new ArrayList<>();
		
		if (person.getID()!=null)
		{
			person = getPersonById(person);
			if (person!=null)
			{
				persons.add(person);
			}
		}
		else if (person.getFirstName() == null
				|| person.getFirstName().isEmpty()) {
			persons = personService.getAllItems();
		}
		else
		{
			try {
				persons = personService.getItemsByName(person);
			} catch (DaoExeception dex) {
				LOG.error(dex.getMessage());
			}
		}
		req.getSession().setAttribute(SEARCH_SET, persons);
		resp.sendRedirect(SEARCH_JSP);
	}

	private PersonBean getPersonById(PersonBean person) {
		PersonBean prsn = null;
		try {
			prsn = personService.getById(person.getID());
		} catch (DaoExeception dex) {
			LOG.error(dex.getMessage());
		}
		return prsn;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean person = (PersonBean) req.getSession().getAttribute(
				USER_BEAN);
		if (person == null) {
			req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
		} else {
			String id = req.getParameter(ID);
			if (isValidPersonId(id)) {
				req.setAttribute(ID, id);
			}
		}
		doPost(req, resp);
	}

	private boolean isValidPersonId(String id) {
		PersonBean person = null;
		if (id != null) {
			try {
				person = personService.getById(id);
			} catch (DaoExeception dex) {
				LOG.error(dex.getMessage());
			}
		}
		return person != null ? true : false;
	}
}
