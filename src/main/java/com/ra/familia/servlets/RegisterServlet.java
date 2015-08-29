package com.ra.familia.servlets;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.IOService;
import com.ra.familia.services.MailService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

@WebServlet(name = "RegisterServlet", displayName = "Register Servlet", urlPatterns = {
		"/register", "/Register" })
public class RegisterServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(RegisterServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private IOService ioService = new IOService();
	
	private MailService mailService = new MailService();

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String nextJSP = register(req);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

	private String register(HttpServletRequest req) {
		StringBuffer messages = new StringBuffer();
		PersonBean bean = getParamsFromMultipleForm(req);
		validateMandatories(bean,messages);
		if (ServletFileUpload.isMultipartContent(req)&&isErrored(messages)) {
				processedFile(req, bean,messages);
				if (isErrored(messages)) {
					if (getPerson(bean, req) == null) {
						addPerson(bean,messages);
					} else {
						messages.append(USER_ALREDY_EXISTS);
					}
				}
		}
		req.setAttribute(REQ_ERROR, messages);
		req.setAttribute(BEAN, bean);
		return isErrored(messages) ? REGISTER_SUCCESS_JSP : REGISTER_JSP;
	}

	private boolean isErrored(StringBuffer messages) {
		return messages.length()==0;
	}

	private void validateMandatories(PersonBean bean,StringBuffer errors) {
		if (!bean.getEmail().matches(EMAIL_PATTERN)) {
			errors.append(EAMIL_IS_NOT_CORRECT);
		}

		if (bean.getSecondName().isEmpty()&&isErrored(errors)) {
			errors.append(SECOND_NAME);
		}

		if (bean.getSecondName().matches(IMAGE_PATTERN)&&isErrored(errors)) {
			errors.append(FILE_NOT_UPLOAD);
		}

		if (bean.getPassword().isEmpty()&&isErrored(errors)) {
			errors.append(PASSWORD_EMPTY);
		}		
	}

	private PersonBean getPerson(PersonBean bean, HttpServletRequest req) {
		PersonBean person = null;
		try {
			person = personService.getItemByName(bean);
		} catch (DaoExeception ex) {
			req.setAttribute(REQ_ERROR, CAN_NOT_COMPLETE);
			LOG.error(ex.getMessage());
		}
		return person;
	}

	private void addPerson(PersonBean bean,StringBuffer errors) {
		try {
			personService.addItem(bean);
			mailService.sendRegistrationMail(bean);
		} catch (DaoExeception ex) {
			errors.append(CAN_NOT_COMPLETE);
		}
	}

	private void processedFile(HttpServletRequest req, PersonBean bean,StringBuffer errors) {
		DiskFileItem diskItem = ((DiskFileItem) bean.getFilePath());
		if (diskItem.getSize() == 0) {
			errors.append(FILE_NOTSPECIFIED);
		} else {
			ioService.storageFile(bean);
		}
	}

}
