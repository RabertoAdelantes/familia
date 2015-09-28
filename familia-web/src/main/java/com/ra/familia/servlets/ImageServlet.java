package com.ra.familia.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.exceptions.FamiliaException;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

import static com.ra.familia.dao.constants.TablesConstants.*;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(ImageServlet.class);

	private IOService ioService = new IOService();
	private static final long serialVersionUID = -3378483395350978236L;
	private Services<PersonBean> personService = new PersonServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("id");
		if (imageId != null) {
			PersonBean person = getPersonByImage(imageId);
			if (person != null) {
				ioService.getCashe().add(imageId + IMG_SUFFIX,
						person.getDbFile());
				byte[] bytes = person.getDbFile();
				if (bytes != null) {
					response.setContentType("image/jpg");
					response.getOutputStream().write(bytes);
					response.getOutputStream().flush();
					response.getOutputStream().close();
				}
			}
		}
	}

	private PersonBean getPersonByImage(String imageId) {
		PersonBean personBean = null;
		try {
			return personService.getById(imageId);
		} catch (FamiliaException familiaEx) {
			LOG.error(String.format(
					"GetPersonByImage '%s' failed. Winth error %s", imageId,
					familiaEx.getException().getMessage()));
		}
		return personBean;
	}
}