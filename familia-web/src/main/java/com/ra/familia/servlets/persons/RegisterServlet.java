package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.FlowBean;
import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.FlowServiceImpl;
import com.ra.familia.services.IOService;
import com.ra.familia.services.MailService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;
import com.ra.familia.validtors.DataValidator;

@WebServlet(name = "RegisterServlet", displayName = "Register Servlet", urlPatterns = {
		"/register", "/Register" })
public class RegisterServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(RegisterServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private IOService ioService = new IOService();
	private MailService mailService = new MailService();

	private PersonServiceImpl personService = new PersonServiceImpl();
	private Services<FlowBean> flowService = new FlowServiceImpl();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(request, res);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextJSP = register(request);
		request.getRequestDispatcher(nextJSP).forward(request, response);
	}

	private String register(HttpServletRequest request) {
		StringBuffer messages = new StringBuffer();
		PersonBean bean = getParamsFromMultipleForm(request);
		DataValidator.validateMandatories(bean, messages);
		if (ServletFileUpload.isMultipartContent(request) && DataValidator.isErrored(messages)) {
			processedFile(bean);
			if (DataValidator.isErrored(messages)) {
				if (getPerson(bean) == null) {
					addPerson(bean, messages,request);
				} else {
					messages.append(USER_ALREDY_EXISTS);
				}
			}
		}
		request.setAttribute(BEAN, bean);
		request.setAttribute(REQ_ERROR, messages);
		return DataValidator.isErrored(messages) ? SUCCESS_URL : REGISTER_JSP;
	}

	private PersonBean getPerson(PersonBean bean) {
		return  personService.getItemByEmail(bean);
	}

	private void addPerson(PersonBean bean, StringBuffer errors, HttpServletRequest request) {
		try {
			String uuid = UUID.randomUUID().toString();			
			FlowBean flowBean = new FlowBean();
			bean.setConfirmationUuid(uuid);
			flowBean.setPearsonBean(bean);
			flowService.addItem(flowBean);
			bean.setConfirmationUuid(request.getHeader("host")+request.getContextPath()+"/activate?id="+uuid);
			mailService.sendRegistrationMail(bean);
		} catch (FamiliaException ex) {
			errors.append(CAN_NOT_COMPLETE);
			LOG.error("Add person is failed.",ex.getException());
		}
	}

	private void processedFile(PersonBean bean) {
		DiskFileItem diskItem = ((DiskFileItem) bean.getFilePath());
		if (diskItem.getSize() > 0) 
	    {
			ioService.storageFile(bean);
		}
	}

}
