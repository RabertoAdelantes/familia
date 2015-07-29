package com.ra.familia.servlets;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.entities.PersonBean;
import com.ra.familia.services.ApplicationCashe;
import com.ra.familia.services.PersonServiceImpl;
import com.ra.familia.services.Services;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(ImageServlet.class);

	private ApplicationCashe imgCashe = ApplicationCashe.getInsatnce();
	
	private static final long serialVersionUID = -3378483395350978236L;
	private Services<PersonBean> personService = new PersonServiceImpl();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String imageId = request.getParameter("id");
		byte[] bytes = imgCashe.get(imageId);
		PersonBean person = personService.getById(imageId);
		byte[] bytes3= saveToCashe(imageId, person);
		byte[] bytes2 = person.getDbFile();
		bytes2.equals(bytes3);
		response.getOutputStream().write(bytes3);
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}

	private void retriveMedia(HttpServletResponse response, String imageId,
			byte[] mediaData) throws IOException {
		byte[] bytes = mediaData == null ? new byte[] {} : Arrays.copyOf(
				mediaData, mediaData.length);

		if (bytes.length == 0) {
			PersonBean person = personService.getById(imageId);
			bytes = saveToCashe(imageId, person);
		}

//		BufferedOutputStream output = new BufferedOutputStream(
//				response.getOutputStream());
		try {
			response.getOutputStream().write(bytes);
			response.getOutputStream().flush();
			response.setContentLength(bytes.length);
		} catch (Exception ex) {
			LOG.error(String.format(
					"Error on image rendering occured '%s' for ID '%s'",
					ex.getLocalizedMessage(), imageId));
		} finally {
			response.getOutputStream().close();
		}
	}

	private byte[] saveToCashe(String imageId, PersonBean person) {
		byte[] bytes = person.getDbFile();
		imgCashe.add(imageId, bytes);
		byte[] bytes2 =imgCashe.get(imageId);
		bytes.equals(bytes2);
		return bytes2;
	}

}