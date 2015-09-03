<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:if test="${sessionScope.session_error != null}">
    <font color="red">ERROR : </font><b><c:out value="${sessionScope.session_error}" /></b>
</c:if>

