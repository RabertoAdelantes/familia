package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

import java.io.*;

@WebServlet(name = "PersonServlet", displayName = "Profile Servlet", urlPatterns = {
		"/profile", "/select", "/Profile", "/Select" }, loadOnStartup = 1)
public class PersonServlet extends GenericServlet {

	private static final long serialVersionUID = 8781195695257213199L;

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String param = req.getParameter(NEW);
		PersonBean bean = getRequestParams(req);
		String redirectUrl = PROFILE_JSP;
		if (personDao.getItemByName(bean) == null) {
			personDao.addItem(bean);
			redirectUrl = req.getContextPath();
		} else {
			personDao.updateItem(bean);
		}
		req.getSession().setAttribute(USER_BEAN, bean);
		res.sendRedirect(redirectUrl);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter(ID);
		PersonBean bean = personDao.getItemById(id);
		req.getSession().setAttribute(USER_BEAN, bean);
		resp.sendRedirect(PROFILE_JSP);
	}

}
