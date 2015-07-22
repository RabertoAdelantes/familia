package com.ra.familia.servlets.utils;

public interface UrlsDictionary {

	// session attributes
	public static final String USER_BEAN = "user";
	public static final String SEARCH_SET = "result";
	public static final String PROFILE_URL = "profile";
	public static final String SEARCH_URL = "search";
	// request attributes
	public static final String REQ_ERROR = "request_error";
	public static final String SES_ERROR = "session_error";

	// jsp pages
	public static final String SEARCH_JSP = "search.jsp";
	public static final String INDEX_JSP = "index.jsp";
	public static final String PROFILE_JSP = "profile.jsp";
	// request parameters
	public static final String PHOTO = "photo";
	public static final String USER_NAME = "userName";
	public static final String DATE_BIRTH = "date_birth";
	public static final String FIRST_NAME = "firstName";
	public static final String MIDLE_NAME = "midleName";
	public static final String LAST_NAME = "lastName";
	public static final String LAST_NAME2 = "lastName2";
	public static final String EMAIL = "email";
	public static final String DATE_DEATH = "date_death";
	public static final String PASSWORD = "password";
	public static final String NEW = "new";
	public static final String TRUE = "true";
	public static final String ID = "id";

	// request parameters
	public static final String ERR_LOGIN_FAILED = "Login failed : user or password is incorrect";
	
	public static final String USER_UPLOAD_FOLDER = "user_upload_folder";
	public static final String FILE_MAX_SIZE = "file.size.max";
	public static final String USER_UPLOAD_DEFAUL_FOLDER = "Z:\\";

}
