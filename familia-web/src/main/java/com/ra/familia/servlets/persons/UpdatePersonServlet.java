package com.ra.familia.servlets.persons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "Update person Servlet", displayName = "Update person Servlet", urlPatterns = {
		"/update", "/Update" })
public class UpdatePersonServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(UpdatePersonServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pk = update(request);
		request.setAttribute("id", pk);
		response.sendRedirect("profile?id="+pk);
	}

	private String update(HttpServletRequest req) {
		String retValue = null;
		PersonBean bean = getParamsFromMultipleForm(req);
		try {
			personService.updateItem(bean);
			retValue = bean.getID();
		} catch (FamiliaException eex) {
			LOG.error(eex.getMessage());
		}
		return retValue;
	}

}
