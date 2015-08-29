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
import com.ra.familia.exceptions.DaoExeception;
import com.ra.familia.services.IOService;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;
import static com.ra.familia.servlets.constants.TablesConstants.*;

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
		byte[] bytes = ioService.getCashe().get(imageId+IMG_SUFFIX);
		if (bytes == null) {
			PersonBean person = getPersonByImage(imageId);
			if (person != null) {
				bytes = ioService.getMediaFromFs(person.getFilePath()
						.toString());
				ioService.getCashe().add(imageId+IMG_SUFFIX, bytes);
			}
		}
		response.getOutputStream().write(bytes);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private PersonBean getPersonByImage(String imageId) {
		PersonBean person = null;
		try {
			person = personService.getById(imageId);
		} catch (DaoExeception ex) {
			LOG.error(ex.getMessage());
		}
		return person;
	}
}