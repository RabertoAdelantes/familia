package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.ConfirmationBean;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.ConfirmationServiceImpl;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "Profile Servlet", displayName = "Profile Servlet", urlPatterns = {
		"/activate", "/Activate" })
public class ActivateServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(ActivateServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private Services<ConfirmationBean> confirmationService = new ConfirmationServiceImpl();
	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ConfirmationBean bean = null;
		String nextPage = ACTIVATE_FAILED_JSP;
		String id = req.getParameter(ID);

		try {
			bean = confirmationService.getById(id);
			if (bean != null && !bean.isUsed()) {
				bean.setUsed(true);
				confirmationService.updateItem(bean);
				PersonBean person = personService.getById(String.valueOf(bean
						.getUserReference()));
				person.setActive(true);
				personService.updateItem(person);
				nextPage = ACTIVATE_JSP;
			}
		} catch (FamiliaException ex) {
			LOG.error("USer actiobation failed : " + ex.getMessage());
		}
		resp.sendRedirect(nextPage);
	}
}
