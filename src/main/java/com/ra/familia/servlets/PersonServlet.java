package com.ra.familia.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.dao.PropertiesManager;
import com.ra.familia.entities.PersonBean;

import java.io.*;
import java.util.Properties;

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
			String filePath = saveFile(bean);
			bean.setPhoto(filePath);
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

	private String saveFile(PersonBean bean) {
		String name = "";
		FileItem item = (FileItem) bean.getPhoto();
		try {
			String directory = properties.getProperty(USER_UPLOAD_FOLDER,
					USER_UPLOAD_DEFAUL_FOLDER);
			name = directory + File.separator + new File(item.getName()).getName();
			item.write(new File(name));
		} catch (Exception ex) {
			LOG.error(ex.getLocalizedMessage());
		}
		return name;
	}

}
