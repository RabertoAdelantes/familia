package com.ra.familia.tags;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class ExceptionDisplayTag extends SimpleTagSupport {

	StringWriter sw = new StringWriter();

	@Override
	public void doTag() throws JspException, IOException {
		getJspBody().invoke(sw);
		getJspContext().getOut().println("<b><font color=\"RED\">"+sw.toString()+"</font></b>");
	}

}
