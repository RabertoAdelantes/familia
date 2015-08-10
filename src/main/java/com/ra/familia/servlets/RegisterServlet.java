package com.ra.familia.servlets;

import static com.ra.familia.servlets.utils.UrlsDictionary.CAN_NOT_COMPLETE;
import static com.ra.familia.servlets.utils.UrlsDictionary.EAMIL_IS_NOT_CORRECT;
import static com.ra.familia.servlets.utils.UrlsDictionary.EMAIL_PATTERN;
import static com.ra.familia.servlets.utils.UrlsDictionary.FILE_NOTSPECIFIED;
import static com.ra.familia.servlets.utils.UrlsDictionary.FILE_NOT_UPLOAD;
import static com.ra.familia.servlets.utils.UrlsDictionary.IMAGE_PATTERN;
import static com.ra.familia.servlets.utils.UrlsDictionary.PASSWORD_EMPTY;
import static com.ra.familia.servlets.utils.UrlsDictionary.REGISTER_JSP;
import static com.ra.familia.servlets.utils.UrlsDictionary.REGISTER_SUCCESS_JSP;
import static com.ra.familia.servlets.utils.UrlsDictionary.REQ_ERROR;
import static com.ra.familia.servlets.utils.UrlsDictionary.SECOND_NAME;
import static com.ra.familia.servlets.utils.UrlsDictionary.USER_ALREDY_EXISTS;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

@WebServlet(name = "RegisterServlet", displayName = "Register Servlet", urlPatterns = {
		"/register", "/Register" }, loadOnStartup = 1)
public class RegisterServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(RegisterServlet.class);
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
		String nextJSP = register(req);
		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(nextJSP);
		dispatcher.forward(req, resp);
	}

	private String register(HttpServletRequest req) {
		String nextJSP = "/" + REGISTER_JSP;
		StringBuffer messages = new StringBuffer();
		if (ServletFileUpload.isMultipartContent(req)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			messages.append(validateMandatories(bean));
			if (messages.length()==0) {
				String message = processedFile(req, bean);
				messages.append(message);
				if (messages.length()==0) {
					PersonBean person = getPerson(bean, req);
					if (person == null) {
						message = addPerson(bean);
						messages.append(message);
						nextJSP = (messages.length()==0) ? "/"
								+ REGISTER_SUCCESS_JSP : nextJSP;
					} else {
						messages.append(USER_ALREDY_EXISTS);

					}
				}
			}
		}
		req.setAttribute(REQ_ERROR, messages);
		return nextJSP;
	}

	private String validateMandatories(PersonBean bean) {
		StringBuffer errorMessage = new StringBuffer();

		if (!bean.getEmail().matches(EMAIL_PATTERN)) {
			errorMessage.append(EAMIL_IS_NOT_CORRECT);
		}

		if (bean.getSecondName().isEmpty()) {
			errorMessage.append(SECOND_NAME);
		}

		if (bean.getSecondName().matches(IMAGE_PATTERN)) {
			errorMessage.append(FILE_NOT_UPLOAD);
		}

		if (bean.getPassword().isEmpty()) {
			errorMessage.append(PASSWORD_EMPTY);
		}
		
		return errorMessage.toString();
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

	private String addPerson(PersonBean bean) {
		String errorMessage = StringUtils.EMPTY;
		try {
			personService.addItem(bean);
		} catch (DaoExeception ex) {
			errorMessage = CAN_NOT_COMPLETE;
		}
		return errorMessage;
	}

	private String processedFile(HttpServletRequest req, PersonBean bean) {
		String error = StringUtils.EMPTY;
		DiskFileItem diskItem = ((DiskFileItem) bean.getFilePath());
		if (diskItem.getSize() == 0) {
			error = FILE_NOTSPECIFIED;
		} else {
			ioService.storageFile(bean);
		}
		return error;
	}

}
