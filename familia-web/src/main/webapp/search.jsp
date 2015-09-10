<%@ taglib prefix="jgc" uri="WEB-INF/custom.tld"%>
<%@ page import="com.ra.familia.entities.PersonBean"%>
<%@ page import="java.util.Set"%>
<%@ include file="include/logout_include.jsp"%>
<%@ include file="include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Search</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="50%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">
						<h2><fmt:message key="search.title" bundle="${bundle}"/></h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="search">
					<tr>
						<td><fmt:message key="user.name.first" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="firstName" /></td>
						<td><fmt:message key="user.name.midle" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="midleName" /></td>
						<td><fmt:message key="user.name.last" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="lastName" /></td>
						<td><fmt:message key="btn.email" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="email" /></td>
						<td><fmt:message key="user.date.birth" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="date_birth" /></td>
						<td><fmt:message key="user.date.death" bundle="${bundle}"/></td>
						<td><input type="text" value="" name="date_death" /></td>
						<td><input type="submit" value="<fmt:message key="btn.search" bundle="${bundle}"/>"></td>
					</tr>
				</form>

				<c:forEach items="${sessionScope.result}" var="person">
					<form method="post" action="Select">
						<tr>
							<td><input type="text" value="${person.ID}" name="id"
								hidden="true" /></td>
							<td>${person.firstName}</td>
							<td>&nbsp;</td>
							<td>${person.midleName}</td>
							<td>&nbsp;</td>
							<td>${person.secondName}</td>
							<td>&nbsp;</td>
							<td>${person.email}</td>
							<td>&nbsp;</td>
							<td>${person.dateBirth}</td>
							<td>&nbsp;</td>
							<td>${person.dateDeath}</td>
							<td>&nbsp;</td>
							<c:if test="${sessionScope.isAdmin==true}">
								<td><a
									href='<c:out value="${pageContext.request.contextPath}/activate?id=${person.ID}"/>'><c:out
											value="${person.isActive eq true ? '': 'ACTIVATE'}" /></td>
								</td>
							</c:if>
							<td><input type="submit" value="<fmt:message key="btn.open" bundle="${bundle}"/>" /></td>
						</tr>
					</form>
				</c:forEach>

				<c:if test="${sessionScope.result.size() == 0}">
					<jgc:resultempty />
				</c:if>

			</tbody>
		</table>
	</center>
</body>
</html>