package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "LoginServlet", displayName = "Authorization Servlet", urlPatterns = { "/login","/Login" }, loadOnStartup = 1)
public class LoginServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginServlet.class);

	private static final long serialVersionUID = 8623907130423043967L;
	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();

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
		}
		resp.sendRedirect(nextStep);
	}

	private PersonBean getUserByName(final String name, final String password) {
		PersonBean person = new PersonBean();
		person.setPassword(password);
		person.setFirstName(name);

		person = personDao.getItemByName(person);
		return person;
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	}
}
