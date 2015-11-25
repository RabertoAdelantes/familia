<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<fmt:setBundle basename="message" var="bundle" />
<%@page import="java.util.Locale"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<form method="post" action="Logout" name="frmLogout">
	<div class="col-sm-offset-3 col-sm-9">
		<a href="?lang=ru_Ru">Русский</a> || <a href="?lang=es_Es">Español</a>
		|| <a href="?lang=en_En">English</a>
		<c:if test='${empty requestScope.isIndex}'>	
        || <a href="${pageContext.request.contextPath}" onClick="document.frmLogout.submit()"><fmt:message
					key="btn.logout" bundle="${bundle}" /></a>
		</c:if>
	</div>
</form>