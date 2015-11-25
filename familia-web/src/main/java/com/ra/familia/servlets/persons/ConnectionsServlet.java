package com.ra.familia.servlets.persons;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.entities.RelationBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.PersonRelationServiceImpl;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "ConnectionsServlet", displayName = "ConnectionsServlet", urlPatterns = {
		"/connections", "/Connections" })
public class ConnectionsServlet extends GenericServlet {

	private static final long serialVersionUID = 8781195695257213199L;

	private PersonRelationServiceImpl personRelationService = new PersonRelationServiceImpl();
	private PersonServiceImpl personService = new PersonServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RelationBean bean = collectBean(request);
		try {
			personRelationService.addItem(bean);
			PersonBean user = personService.getById(bean.getPersonId());
			request.setAttribute("user", user);
		} catch (FamiliaException e) {
			e.printStackTrace();
		}
		request.setAttribute("idCurrent",request.getParameter("idCurrent"));
		request.getRequestDispatcher(PROFILE_URL).forward(request, response);
	}

	private RelationBean collectBean(HttpServletRequest request) {
		RelationBean relationBean = new RelationBean();
		relationBean.setPersonId(request.getParameter("idCurrent"));
		relationBean.setPersonRelationId(request.getParameter("id"));
		relationBean.setTypeId(request.getParameter("relatives"));
		return relationBean;
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
