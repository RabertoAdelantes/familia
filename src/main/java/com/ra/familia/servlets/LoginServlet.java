package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

import java.io.*;

@WebServlet(name = "LoginServlet", displayName = "Authorization Servlet", urlPatterns = { "/login" }, loadOnStartup = 1)
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 8623907130423043967L;
	private PersonDao personDao;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		personDao = new PersonDao();
		 String requestedName = req.getParameter("userName"); 
		System.out.println("ppppppp"+requestedName);
		PrintWriter pw = resp.getWriter();
		boolean isRegistered = isValidUser(requestedName);
		if (isRegistered) {
			pw.println("<html><body>");
			pw.println("Welcome to servlet11111111111");
			pw.println("</body></html>");
		} else {
			pw.println("<html><body>");
			pw.println("Welcome to servlet222222222222");
			pw.println("</body></html>");
		}
		pw.close();
	}

	private boolean isValidUser(final String name) {
		boolean isValid = false;
		PersonBean person = personDao.getItemByName(name);
		if (person != null) {
			isValid = true;
		}
		return isValid;
	}
}
