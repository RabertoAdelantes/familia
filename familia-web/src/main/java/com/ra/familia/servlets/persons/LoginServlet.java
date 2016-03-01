package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import com.google.common.collect.Maps;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.EncryptionService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "LoginServlet", displayName = "Authorization Servlet", urlPatterns = { "/login",
		"/Login" }, loadOnStartup = 1)
public class LoginServlet extends GenericServlet {

	private static final long serialVersionUID = 4583682557004705736L;

	private EncryptionService encryptionService = new EncryptionService();
	private PersonServiceImpl personService = new PersonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter(EMAIL);
		String password = req.getParameter(PASSWORD);

		Map<String, Object> errorMap = Maps.newHashMap();
		String nextStep = performLogin(email, password, errorMap);
		handleErrorMessages(errorMap, req);

		if (nextStep == null) {
			req.getRequestDispatcher("index.jsp").forward(req, resp);
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			if (auth != null) {
				new SecurityContextLogoutHandler().logout(req, resp, auth);
			}
		} else {
			resp.sendRedirect("search.jsp");
		}
	}

	private void handleErrorMessages(Map<String, Object> errorMap, HttpServletRequest req) {
		errorMap.forEach((param, value) -> {
			if (USER_BEAN.equals(param)) {
				HttpSession session = req.getSession(true);
				session.setAttribute(USER_BEAN, value);
			} else {
				req.setAttribute(param, value);
			}
		});
	}

	private String performLogin(String email, String password, Map<String, Object> errorMap) {
		String nextStep = null;
		PersonBean person = getUserByEmailAndPassword(email, password);
		if (person == null) {
			errorMap.put(REQ_ERROR, ERR_LOGIN_FAILED);
			person = new PersonBean();
			person.setEmail(email);
			errorMap.put(USER_BEAN, person);
		} else if (person != null && person.isActive() == false) {
			errorMap.put(REQ_ERROR, ERR_NOT_ACTIVE);
		} else {
			nextStep = SEARCH_URL;
			errorMap.put(USER_BEAN, person);
		}
		return nextStep;
	}

	private PersonBean getUserByEmailAndPassword(final String email, final String password) {
		PersonBean person = new PersonBean();
		person.setPassword(encryptionService.encode(password));
		person.setEmail(email);
		return personService.getItemByEmail(person);
	}

}
