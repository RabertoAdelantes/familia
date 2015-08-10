package com.ra.familia.servlets.utils;


public final class UrlsDictionary {

	private UrlsDictionary()
	{
		
	}
	// session attributes
	public static final String IS_ADMIN = "isAdmin";
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
	public static final String REGISTER_JSP = "register.jsp";
	public static final String REGISTER_SUCCESS_JSP = "register_success.jsp";
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
	
	public static final String USER_SUSSEFULLY_REGISTERED = "User sussefully registered.";
	public static final String USER_ALREDY_EXISTS = "User already exists.";
	public static final String CAN_NOT_COMPLETE = "The operation cannot be completed.";
	public static final String EAMIL_IS_NOT_CORRECT = "The email is not correct.";
	public static final String SECOND_NAME = "The second name can not be empty.";
	public static final String FILE_NOTSPECIFIED = "File is not selected.";
	public static final String FILE_NOT_UPLOAD = "File is NOT image.";
	public static final String PASSWORD_EMPTY = "Password can't be empty.";
		
	public static final String EMAIL_PATTERN = 
			"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	
	public static final String IMAGE_PATTERN = 
            "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";





}
