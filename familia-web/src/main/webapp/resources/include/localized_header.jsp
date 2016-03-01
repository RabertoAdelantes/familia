<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<fmt:setBundle basename="message" var="bundle" />
<%@page import="java.util.Locale"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<form method="post" action="Logout" name="frmLogout">
	<div class="col-sm-offset-3 col-sm-9">
		<a href="?lang=ru_Ru">Русский</a> || <a href="?lang=es_Es">Español</a>
		|| <a href="?lang=en_En">English</a>

		<security:authorize access="isAuthenticated()">
    	||  <a href="<c:url value="/logout" />"> <fmt:message
					key="btn.logout" bundle="${bundle}" /></a>
		</security:authorize>


	</div>
</form>