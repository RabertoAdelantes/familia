package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "PersonServlet", displayName = "Profile Servlet", urlPatterns = {
		"/profile", "/select", "/Profile", "/Select" }, loadOnStartup = 1)
public class PersonServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonServlet.class);
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
		String redirectUrl = PROFILE_JSP;
		if (ServletFileUpload.isMultipartContent(req)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			ioService.storageFile(bean);
			if (personService.getById(bean.getId()) == null) {
				personService.addItem(bean);
				redirectUrl = req.getContextPath();
			} else {
				personService.updateItem(bean);
			}
		}
		String id = req.getParameter(ID);
		PersonBean bean = personService.getById(id);
		req.getSession().setAttribute(USER_BEAN, bean);
		resp.sendRedirect(redirectUrl);
	}
}
