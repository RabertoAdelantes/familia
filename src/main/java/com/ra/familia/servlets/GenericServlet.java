package com.ra.familia.servlets;

import static com.ra.familia.servlets.utils.UrlsDictionary.DATE_BIRTH;
import static com.ra.familia.servlets.utils.UrlsDictionary.DATE_DEATH;
import static com.ra.familia.servlets.utils.UrlsDictionary.EMAIL;
import static com.ra.familia.servlets.utils.UrlsDictionary.FIRST_NAME;
import static com.ra.familia.servlets.utils.UrlsDictionary.ID;
import static com.ra.familia.servlets.utils.UrlsDictionary.LAST_NAME;
import static com.ra.familia.servlets.utils.UrlsDictionary.LAST_NAME2;
import static com.ra.familia.servlets.utils.UrlsDictionary.MIDLE_NAME;
import static com.ra.familia.servlets.utils.UrlsDictionary.PASSWORD;
import static com.ra.familia.servlets.utils.UrlsDictionary.PHOTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;


public class GenericServlet extends HttpServlet {

	private static final long serialVersionUID = -1211204935725232738L;

	private static final Logger LOG = LoggerFactory
			.getLogger(GenericServlet.class);

	protected PersonBean getRequestParams(HttpServletRequest req) {
		PersonBean person = new PersonBean();
		person.setID(req.getParameter(ID));
		person.setDateBirth(req.getParameter(DATE_BIRTH));
		person.setDateDeath(req.getParameter(DATE_DEATH));
		person.setPassword(req.getParameter(DATE_DEATH));
		person.setFirstName(req.getParameter(FIRST_NAME));
		person.setMidleName(req.getParameter(MIDLE_NAME));
		person.setLastName(req.getParameter(LAST_NAME));
		person.setEmail(req.getParameter(EMAIL));
		person.setLastName2(req.getParameter(LAST_NAME2));
		return person;
	}

	protected PersonBean getParamsFromMultipleForm(HttpServletRequest req) {
		PersonBean person = new PersonBean();
		Map<String, Object> params = getParameters(req);
		for (Entry<String, Object> iterable : params.entrySet()) {
			if (ID.equals(iterable.getKey())) {
				person.setID(iterable.getValue().toString());
			}
			if (DATE_BIRTH.equals(iterable.getKey())) {
				person.setDateBirth(iterable.getValue().toString());
			}
			if (DATE_DEATH.equals(iterable.getKey())) {
				person.setDateDeath(iterable.getValue().toString());
			}
			if (FIRST_NAME.equals(iterable.getKey())) {
				person.setFirstName(iterable.getValue().toString());
			}
			if (MIDLE_NAME.equals(iterable.getKey())) {
				person.setMidleName(iterable.getValue().toString());
			}
			if (LAST_NAME.equals(iterable.getKey())) {
				person.setLastName(iterable.getValue().toString());
			}
			if (PASSWORD.equals(iterable.getKey())) {
				person.setPassword(iterable.getValue().toString());
			}
			if (EMAIL.equals(iterable.getKey())) {
				person.setEmail(iterable.getValue().toString());
			}
			if (LAST_NAME2.equals(iterable.getKey())) {
				person.setLastName2(iterable.getValue().toString());
			}
			if (PHOTO.equals(iterable.getKey())) {
				person.setFilePath(iterable.getValue());
			}
		}
		return person;

	}

	private Map<String, Object> getParameters(HttpServletRequest req) {
		Map<String, Object> parameters = new HashMap<String, Object>();
		List<FileItem> multiparts;
		try {
			multiparts = new ServletFileUpload(new DiskFileItemFactory())
					.parseRequest(req);
			for (FileItem item : multiparts) {
				if (item.isFormField()) {
					parameters.put(item.getFieldName(), item.getString());
					
				} else {
					parameters.put(PHOTO, item);
				}
			}
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage());
		}
		return parameters;
	}
}
