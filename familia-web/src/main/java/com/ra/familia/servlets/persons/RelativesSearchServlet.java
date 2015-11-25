package com.ra.familia.servlets.persons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ra.familia.services.TypesServiceImpl;
import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "RelativesSearchServlet", displayName = "RelativesSearchServlet", urlPatterns = {
		"/relatives", "/Relatives" })
public class RelativesSearchServlet extends GenericServlet {

	private static final String RELATIVES_SEARCH_JSP = "/relatives_search.jsp";

	private static final String CURRENT_USER_ID = "currentUserId";

	private static final long serialVersionUID = 8781195695257213199L;

	private TypesServiceImpl typesService = new TypesServiceImpl();

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(CURRENT_USER_ID,
				request.getParameter(CURRENT_USER_ID));
		request.setAttribute("relTypes", typesService.getRelatives());
		request.getRequestDispatcher(RELATIVES_SEARCH_JSP).forward(request,
				response);
	}

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(RELATIVES_SEARCH_JSP).forward(request,
				response);

	}

}
