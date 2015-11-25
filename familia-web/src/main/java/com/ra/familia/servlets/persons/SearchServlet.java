package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Sets;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.services.TypesServiceImpl;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "SearchServlet", displayName = "Search Servlet", urlPatterns = {
		"/search", "/Search" })
public class SearchServlet extends GenericServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(SearchServlet.class);

	private static final long serialVersionUID = 8781195695257213199L;

	private Services<PersonBean> personService = new PersonServiceImpl();
	private TypesServiceImpl typesService = new TypesServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean person = getRequestParams(request);
		Set<PersonBean> persons = Sets.newHashSet();
		if (areAnyCriterias(person)) {
			persons = getPersonsByCriteria(person);
		} else {
			persons = (Set<PersonBean>) personService.getAllItems();
		}
		request.setAttribute(SEARCH_SET, persons);
		request.setAttribute("relTypes", typesService.getRelatives());
		request.setAttribute("currentUserId",request.getParameter("currentId"));
		String nextUrl = request.getParameter("isRelSearch")!=null?SEARCH_REL_JSP:SEARCH_JSP;
		request.getRequestDispatcher(nextUrl).forward(request, resp);
	}

	private boolean areAnyCriterias(PersonBean person) {
		boolean isValid = true;
		if (StringUtils.isEmpty(person.getFirstName())
				&& StringUtils.isEmpty(person.getEmail())
				&& StringUtils.isEmpty(person.getMidleName())
				&& StringUtils.isEmpty(person.getSecondName())
				&& StringUtils.isEmpty(person.getDateBirth())
				&& StringUtils.isEmpty(person.getDateDeath())) {
			isValid = false;
		}
		return isValid;
	}

	private Set<PersonBean> getPersonsByCriteria(PersonBean person) {
		Set<PersonBean> prsn = Sets.newHashSet();
		try {
			prsn = personService.getItemsByName(person);
		} catch (FamiliaException dex) {
			LOG.error(dex.getMessage());
		}
		return prsn;
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		HttpSession session = req.getSession(false);
		PersonBean person = (PersonBean) session.getAttribute(USER_BEAN);
		if (person == null) {
			req.getRequestDispatcher(INDEX_JSP).forward(req, resp);
		} else {
			String id = req.getParameter(ID);
			if (isValidPersonId(id)) {
				req.setAttribute(ID, id);
			}
			doPost(req, resp);
		}
	}

	private boolean isValidPersonId(String id) {
		PersonBean person = null;
		if (id != null) {
			try {
				person = personService.getById(id);
			} catch (FamiliaException dex) {
				LOG.error(dex.getMessage());
			}
		}
		return person != null ? true : false;
	}
}
