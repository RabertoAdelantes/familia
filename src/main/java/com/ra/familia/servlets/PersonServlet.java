package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import java.io.*;
@WebServlet( name="LoginServlet", displayName="Authorization Servlet", urlPatterns = {"/login"}, loadOnStartup=1)
public class PersonServlet extends HttpServlet {

	private static final long serialVersionUID = 8781195695257213199L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		pw.println("<html><body>");
		pw.println("Welcome to servlet 22222");
		pw.println("</body></html>");

		pw.close();
	}
}
