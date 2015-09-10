package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "PersonServlet", displayName = "Profile Servlet", urlPatterns = {
		"/profile", "/select", "/Profile", "/Select" })
public class PersonServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private IOService ioService = new IOService();

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirectUrl = PROFILE_JSP;
		if (ServletFileUpload.isMultipartContent(req)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			ioService.storageFile(bean);
			try {
				if (personService.getById(bean.getID()) == null) {
					personService.addItem(bean);
					redirectUrl = req.getContextPath();
				} else {
					personService.updateItem(bean);
				}
			} catch (DaoExeception ex) {
				req.setAttribute(REQ_ERROR, CAN_NOT_COMPLETE);
				LOG.error(ex.getMessage());
			}
		}
		String id = req.getParameter(ID);
		PersonBean bean =null;
		try {
			bean = personService.getById(id);
		} catch (DaoExeception ex) {
			req.setAttribute(REQ_ERROR, CAN_NOT_COMPLETE);
			LOG.error(ex.getMessage());
		}
		req.getSession().setAttribute(USER_BEAN, bean);
		resp.sendRedirect(redirectUrl);
	}
}
