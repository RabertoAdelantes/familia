package com.ra.familia.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

public class NoRecordsFoundTag extends SimpleTagSupport {

	@Override
	public void doTag() throws JspException, IOException {
		final JspWriter out = getJspContext().getOut();
		out.println("<b><font color=\"#0101DF\">NO RECORDS FOUND</font></b>");
	}

}
