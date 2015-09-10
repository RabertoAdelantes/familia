package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.PersonGroupServiceImpl;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "LoginServlet", displayName = "Authorization Servlet", urlPatterns = {
		"/login", "/Login" }, loadOnStartup = 1)
public class LoginServlet extends GenericServlet {

	private static final long serialVersionUID = 4583682557004705736L;

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginServlet.class);

	private PersonServiceImpl personService = new PersonServiceImpl();
	private PersonGroupServiceImpl personGroupService = new PersonGroupServiceImpl();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nextStep = SEARCH_URL;
		String email = req.getParameter(EMAIL);
		String password = req.getParameter(PASSWORD);
		String remember = req.getParameter(REMEMBER);
		PersonBean person = getUserByEmail(email, password);
		if (person == null) {
			LOG.warn("Login failed");
			nextStep = req.getContextPath();
			req.getSession().setAttribute(SES_ERROR, ERR_LOGIN_FAILED);
		} else {
			if (remember != null) {
				Cookie loginCookie = new Cookie(USER_BEAN, person.getEmail());
				loginCookie.setMaxAge(30 * 60);
				resp.addCookie(loginCookie);
			}
			req.getSession().setAttribute(USER_BEAN, person);
			boolean isAdmin = personGroupService.isUserAdmin(person);
			req.getSession().setAttribute(IS_ADMIN, isAdmin);
		}
		resp.sendRedirect(nextStep);
	}

	private PersonBean getUserByEmail(final String email, final String password) {
		PersonBean person = new PersonBean();
		person.setPassword(password);
		person.setEmail(email);
		try {
			person = personService.getItemByEmail(person);
		} catch (DaoExeception ex) {
			LOG.error(ex.getMessage());
		}
		return person;
	}

}
