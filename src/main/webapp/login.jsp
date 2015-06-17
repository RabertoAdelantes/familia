<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="emp" class="PersonDao" scope="request">
    <jsp:setProperty name="person" property="*"/>
</jsp:useBean>
<%
out.println("Invalid password <a href='index.jsp'>try again</a>");

%>