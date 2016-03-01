package com.ra.familia.servlets.persons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ra.familia.servlets.GenericServlet;

@WebServlet(urlPatterns = { "/" }, loadOnStartup = 1)
public class IndexServlet extends GenericServlet {

	private static final long serialVersionUID = 4583682557004705736L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doGet(req, resp);
		System.out.println(">>>>>>>>>>>>>>>>>>>>INDEX GET");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.doPost(req, resp);
		System.out.println(">>>>>>>>>>>>>>>>>>>>INDEX POST");

	}

}
