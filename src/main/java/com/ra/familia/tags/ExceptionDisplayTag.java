package com.ra.familia.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.servlets.LoginServlet;

public class ExceptionDisplayTag extends SimpleTagSupport {

	private static final long serialVersionUID = -4061579853792785156L;

	private static final Logger LOG = LoggerFactory
			.getLogger(LoginServlet.class);

	StringWriter sw = new StringWriter();

	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		getJspContext().getOut().println("<b><font color=\"RED\">"+sw.toString()+"</font></b>");
	}

}
