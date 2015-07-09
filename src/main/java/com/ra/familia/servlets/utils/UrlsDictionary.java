package com.ra.familia.servlets.utils;

public interface UrlsDictionary {

	// session attributes
	public static final String USER_BEAN = "user".intern();
	public static final String SEARCH_SET = "result".intern();
	public static final String PROFILE_URL = "profile".intern();
	public static final String SEARCH_URL = "search".intern();
	// request attributes
	public static final String REQ_ERROR = "request_error".intern();
	public static final String SES_ERROR = "session_error".intern();

	// jsp pages
	public static final String SEARCH_JSP = "search.jsp".intern();
	public static final String INDEX_JSP = "index.jsp".intern();
	public static final String PROFILE_JSP = "profile.jsp".intern();
	// request parameters
	public static final String PHOTO = "photo".intern();
	public static final String USER_NAME = "userName".intern();
	public static final String DATE_BIRTH = "date_birth".intern();
	public static final String FIRST_NAME = "firstName".intern();
	public static final String MIDLE_NAME = "midleName".intern();
	public static final String LAST_NAME = "lastName".intern();
	public static final String LAST_NAME2 = "lastName2".intern();
	public static final String EMAIL = "email".intern();
	public static final String DATE_DEATH = "date_death".intern();
	public static final String PASSWORD = "password".intern();
	public static final String NEW = "new".intern();
	public static final String TRUE = "true".intern();
	public static final String ID = "id".intern();

	// request parameters
	public static final String ERR_LOGIN_FAILED = "Login failed : user or password is incorrect".intern();
	
	public static final String USER_UPLOAD_FOLDER = "user_upload_folder".intern();
	public static final String USER_UPLOAD_DEFAUL_FOLDER = "Z:\\".intern();

}
