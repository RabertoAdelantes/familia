package com.ra.familia.validtors;

import static com.ra.familia.servlets.constants.UrlsConstants.*;

import org.apache.commons.lang.StringUtils;

import com.ra.familia.entities.PersonBean;

public class DataValidator {

	
	public static void validateMandatories(PersonBean bean, StringBuffer errors) {
		if (StringUtils.isEmpty(bean.getEmail())&&!bean.getEmail().matches(EMAIL_PATTERN)) {
			errors.append(EAMIL_IS_NOT_CORRECT);
		}

		if (StringUtils.isEmpty(bean.getSecondName()) && isErrored(errors)) {
			errors.append(SECOND_NAME);
		}
		
		if (StringUtils.isEmpty(bean.getFirstName()) && isErrored(errors)) {
			errors.append(FIRST_NAME);
		}

		if (StringUtils.isEmpty(bean.getPassword()) && isErrored(errors)) {
			errors.append(PASSWORD_EMPTY);
		}
	}
	
	public static boolean isErrored(StringBuffer messages) {
		return messages.length() == 0;
	}
}
