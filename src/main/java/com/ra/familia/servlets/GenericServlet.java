package com.ra.familia.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import com.ra.familia.entities.PersonBean; 

public class GenericServlet extends HttpServlet implements UrlsDictionary{
	
	protected PersonBean getRequestParams(HttpServletRequest req) {
		PersonBean person = new PersonBean();
		person.setDate_birth(req.getParameter(DATE_BIRTH));
		person.setDate_death(req.getParameter(DATE_DEATH));
		person.setPassword(req.getParameter(DATE_DEATH));
		person.setFirst_name(req.getParameter(FIRST_NAME));
		person.setMidle_name(req.getParameter(MIDLE_NAME));
		person.setSecond_name(req.getParameter(LAST_NAME));
		person.setEmail(req.getParameter(EMAIL));
		return person;
	}
}
