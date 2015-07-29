package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

import java.io.*;

@WebServlet(name = "RegisterServlet", displayName = "Register Servlet", urlPatterns = {
		"/register", "/Register" }, loadOnStartup = 1)
public class RegisterServlet extends GenericServlet {

	private static final String USER_SUSSEFULLY_REGISTERED = "User sussefully registered";
	private static final String USER_ALREDY_EXISTS = "User already exists";
	private static final String FILE_NOTSPECIFIED = "File is not selected";
	private static final Logger LOG = LoggerFactory
			.getLogger(RegisterServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private IOService ioService = new IOService();

	private Services<PersonBean> personService = new PersonServiceImpl();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (ServletFileUpload.isMultipartContent(req)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			processedFile(req, bean);
			if (!isErrored(req)) {
				ioService.storageFile(bean);
				if (personService.getById(bean.getID()) == null) {
					personService.addItem(bean);
					req.setAttribute(REQ_ERROR, USER_SUSSEFULLY_REGISTERED);
				} else {
					req.setAttribute(REQ_ERROR, USER_ALREDY_EXISTS);
				}
			}
		}
		String nextJSP = "/"+REGISTER_JSP;
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
		dispatcher.forward(req,resp);
	}

	private void processedFile(HttpServletRequest req, PersonBean bean) {
		DiskFileItem diskItem = ((DiskFileItem) bean.getFilePath());
		if (diskItem.getSize() == 0) {
			req.setAttribute(REQ_ERROR, FILE_NOTSPECIFIED);
		}
		else
		{
			ioService.storageFile(bean);
		}
	}

	private boolean isErrored(HttpServletRequest req) {
		return req.getAttribute(REQ_ERROR) == null ? false : true;
	}
}
