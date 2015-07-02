<%@ taglib prefix="err" uri="/WEB-INF/custom.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
	String errorMessage = (String) session
			.getAttribute("session_error");
	if (errorMessage != null) {
%>
<err:exceptionDisplay>ERROR : </err:exceptionDisplay>
<%=errorMessage%>
<%
	}
%>
