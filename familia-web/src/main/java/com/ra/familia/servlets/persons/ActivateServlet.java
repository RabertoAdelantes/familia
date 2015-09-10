package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "Profile Servlet", displayName = "Profile Servlet", urlPatterns = {
		"/activate", "/Activate" })
public class ActivateServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(ActivateServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		PersonBean bean = null;
		String id = req.getParameter(ID);
			try {
				bean = personService.getById(id);
				if (bean!=null)
				{
					bean.setActive(true);
					personService.updateItem(bean);
				}
			} catch (DaoExeception daoex) {
				LOG.error(daoex.getMessage());
			}
		req.getSession().setAttribute(USER_BEAN, bean);
		resp.sendRedirect(SEARCH_URL+"?id="+id);
	}
}
