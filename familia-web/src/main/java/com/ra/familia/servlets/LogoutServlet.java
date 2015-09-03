package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;

@WebServlet(name = "LogoutServlet", displayName = "Logiur Servlet", urlPatterns = { "/logout","/Logout" }, loadOnStartup = 1)
public class LogoutServlet extends GenericServlet {

	private static final long serialVersionUID = 8623907130423043967L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			req.getSession().invalidate();
		resp.sendRedirect(req.getContextPath());
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		super.doPost(req, resp);
	}

}
