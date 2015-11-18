package com.ra.familia.servlets.persons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.ra.familia.servlets.constants.UrlsConstants.*;


import com.ra.familia.servlets.GenericServlet;

@WebServlet(name = "SuccessServlet", displayName = "Success Servlet", urlPatterns = {
		"/success", "/Success" })
public class SuccessServlet extends GenericServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.sendRedirect(REGISTER_SUCCESS_JSP);
	}

}
