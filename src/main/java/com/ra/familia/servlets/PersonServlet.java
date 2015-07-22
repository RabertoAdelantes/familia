package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;

import java.io.*;
import java.util.Properties;
import java.util.UUID;

@WebServlet(name = "PersonServlet", displayName = "Profile Servlet", urlPatterns = {
		"/profile", "/select", "/Profile", "/Select" }, loadOnStartup = 1)
public class PersonServlet extends GenericServlet {

	private static final Logger LOG = LoggerFactory
			.getLogger(PersonServlet.class);
	private static final long serialVersionUID = 8781195695257213199L;

	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();

	private static Properties properties = PropertiesManager
			.getCommonProperties();

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String redirectUrl = PROFILE_JSP;
		if (ServletFileUpload.isMultipartContent(req)) {
			PersonBean bean = getParamsFromMultipleForm(req);
			storageFile(bean);
			if (personDao.getItemById(bean.getId()) == null) {
				personDao.addItem(bean);
				redirectUrl = req.getContextPath();
			} else {
				personDao.updateItem(bean);
			}
		}
		String id = req.getParameter(ID);
		PersonBean bean = personDao.getItemById(id);
		req.getSession().setAttribute(USER_BEAN, bean);
		resp.sendRedirect(redirectUrl);
	}

	private void storageFile(PersonBean bean) {
		String max_size = properties.getProperty(FILE_MAX_SIZE, "1048576");
		FileItem item = (FileItem) bean.getFilePath();
		String name = getFileName(item);
		try {
			item.write(new File(name));
			bean.setFilePath(name);
			
			if (item.getSize() < Long.valueOf(max_size)) {
				FileInputStream in = new FileInputStream(name);
				byte[] bytes = IOUtils.toByteArray(in);
				bean.setDbFile(bytes);
			} 
		} catch (Exception ex) {
			LOG.error(String.format("Error occured on save '%s'",
					ex.getLocalizedMessage()));
		}
	}

	private String getFileName(final FileItem item) {
		String directory = properties.getProperty(USER_UPLOAD_FOLDER,
				USER_UPLOAD_DEFAUL_FOLDER);
		String name = directory + File.separator;
		if (isFileExists(name + item.getName())) {
			String[] fileAttr = item.getName().split("\\.");
			String fName = fileAttr[0];
			String fExt = fileAttr[1];
			name += fName + "_" + UUID.randomUUID().toString() + "." + fExt;
		}
		return name;
	}

	private boolean isFileExists(final String fname) {
		return new File(fname).exists();
	}

}
