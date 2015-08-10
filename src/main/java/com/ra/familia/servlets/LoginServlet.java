package com.ra.familia.servlets;

import static com.ra.familia.servlets.utils.UrlsDictionary.ERR_LOGIN_FAILED;
import static com.ra.familia.servlets.utils.UrlsDictionary.IS_ADMIN;
import static com.ra.familia.servlets.utils.UrlsDictionary.PASSWORD;
import static com.ra.familia.servlets.utils.UrlsDictionary.SEARCH_URL;
import static com.ra.familia.servlets.utils.UrlsDictionary.SES_ERROR;
import static com.ra.familia.servlets.utils.UrlsDictionary.USER_BEAN;
import static com.ra.familia.servlets.utils.UrlsDictionary.USER_NAME;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.PersonGroupServiceImpl;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;


@WebServlet(name = "LoginServlet", displayName = "Authorization Servlet", urlPatterns = { "/login","/Login" }, loadOnStartup = 1)
public class LoginServlet extends GenericServlet {

	private static final long serialVersionUID = 4583682557004705736L;


	private static final Logger LOG = LoggerFactory
			.getLogger(LoginServlet.class);


	private Services<PersonBean> personService = new PersonServiceImpl();
	private PersonGroupServiceImpl personGroupService = new PersonGroupServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String name = req.getParameter(USER_NAME);
		String password = req.getParameter(PASSWORD);

		PersonBean person = getUserByName(name, password);
		String nextStep = SEARCH_URL;
		if (person == null) {
			LOG.info("Login failed");
			nextStep = req.getContextPath();
			req.getSession().setAttribute(SES_ERROR,ERR_LOGIN_FAILED);
		} else {
			LOG.info("Login Success");
			req.getSession().setAttribute(USER_BEAN, person);
			boolean isAdmin = personGroupService.isUserAdmin(person);
			req.getSession().setAttribute(IS_ADMIN, isAdmin);
		}
		resp.sendRedirect(nextStep);
	}

	private PersonBean getUserByName(final String name, final String password) {
		PersonBean person = new PersonBean();
		person.setPassword(password);
		person.setFirstName(name);
		try {
			person = personService.getItemByName(person);
		} catch (DaoExeception ex) {
			//req.setAttribute(REQ_ERROR, CAN_NOT_COMPLETE);
			LOG.error(ex.getMessage());
		}
		return person;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
