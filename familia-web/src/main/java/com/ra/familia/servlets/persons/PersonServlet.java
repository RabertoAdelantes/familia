package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "PersonServlet", displayName = "Profile Servlet", urlPatterns = {
		"/profile", "/select", "/Profile", "/Select" })
public class PersonServlet extends GenericServlet {

	private static final String PROFILE_ID = "profileId";
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
		String profileId = req.getParameter("id");
		if (ServletFileUpload.isMultipartContent(req)
				&& StringUtils.isEmpty(profileId)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			ioService.storageFile(bean);
			try {
				if (personService.getById(bean.getID()) == null) {
					personService.addItem(bean);
					redirectUrl = req.getContextPath();
				} else {
					personService.updateItem(bean);
				}
			} catch (FamiliaException ex) {
				req.setAttribute(REQ_ERROR, CAN_NOT_COMPLETE);
				LOG.error(ex.getMessage());
			}
		} else {
			Map<String, Object> params = getParameters(req);
			try {
				if (StringUtils.isEmpty(profileId)) {
					profileId = getProfileId(req, params);
				}
				PersonBean personBean = personService.getById(profileId);
				req.setAttribute("user", personBean);
			} catch (FamiliaException ex) {
				LOG.error(ex.getMessage());
			}
		}
		req.getRequestDispatcher(redirectUrl).forward(req, resp);

	}

	private String getProfileId(HttpServletRequest req,
			Map<String, Object> params) {
		String profileId = StringUtils.EMPTY;
		String[] ids = (String[]) params.get("id");
		if (!ArrayUtils.isEmpty(ids)) {
			profileId = ids[0];
			req.getSession().setAttribute(PROFILE_ID, profileId);
		} else {
			profileId = (String) req.getSession().getAttribute(PROFILE_ID);
		}
		return profileId;
	}

}
