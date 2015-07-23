package com.ra.familia.tags;

import java.io.IOException;

import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoRecordsFoundTag extends SimpleTagSupport {

	private static final long serialVersionUID = -4061579853792785156L;

	private static final Logger LOG = LoggerFactory
			.getLogger(NoRecordsFoundTag.class);

	public void doTag() throws JspException, IOException {
		final JspWriter out = getJspContext().getOut();
		out.println("<b><font color=\"#0101DF\">NO RECORDS FOUND</font></b>");
	}

}
