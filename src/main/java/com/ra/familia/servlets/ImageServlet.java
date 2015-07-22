package com.ra.familia.servlets;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ra.familia.dao.DaoFactory;
import com.ra.familia.dao.PersonDao;
import com.ra.familia.entities.PersonBean;

@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final Logger LOG = LoggerFactory
			.getLogger(ImageServlet.class);

	private static Map<String, byte[]> imgCashe = new HashMap<>();

	private static final long serialVersionUID = -3378483395350978236L;
	private PersonDao personDao = DaoFactory.getInstance().getPersonDao();

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String imageId = request.getParameter("id");
		byte[] bytes = imgCashe.get(imageId);
		retriveMedia(response, imageId, bytes);
	}

	private void retriveMedia(HttpServletResponse response, String imageId,
			byte[] mediaData) throws IOException {
		byte[] bytes = mediaData == null ? new byte[] {} : Arrays.copyOf(
				mediaData, mediaData.length);

		if (bytes.length == 0) {
			PersonBean person = personDao.getItemById(imageId);
			bytes = saveToCashe(imageId, person);
		}

		BufferedOutputStream output = new BufferedOutputStream(
				response.getOutputStream());
		try {
			output.write(bytes);
			output.flush();
			response.setContentLength(bytes.length);
		} catch (Exception ex) {
			LOG.error(String.format(
					"Error on image rendering occured '%s' for ID '%s'",
					ex.getLocalizedMessage(), imageId));
		} finally {
			output.close();
		}
	}

	private byte[] saveToCashe(String imageId, PersonBean person) {
		byte[] bytes = person.getDbFile();
		imgCashe.put(imageId, bytes);
		return bytes;
	}

}