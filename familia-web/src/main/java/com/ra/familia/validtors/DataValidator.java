package com.ra.familia.validtors;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import com.ra.familia.entities.PersonBean;

public class DataValidator {

	
	public static void validateMandatories(PersonBean bean, StringBuffer errors) {
		if (!bean.getEmail().matches(EMAIL_PATTERN)) {
			errors.append(EAMIL_IS_NOT_CORRECT);
		}

		if (bean.getSecondName().isEmpty() && isErrored(errors)) {
			errors.append(SECOND_NAME);
		}

		if (bean.getSecondName().matches(IMAGE_PATTERN) && isErrored(errors)) {
			errors.append(FILE_NOT_UPLOAD);
		}

		if (bean.getPassword().isEmpty() && isErrored(errors)) {
			errors.append(PASSWORD_EMPTY);
		}
	}
	
	public static boolean isErrored(StringBuffer messages) {
		return messages.length() == 0;
	}
}
