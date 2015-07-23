<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="jgc" uri="WEB-INF/custom.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.ra.familia.entities.PersonBean"%>
<%@ page import="java.util.Set"%>
<%@ include file="include/logout_include.jsp"%>
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
						<h2>Search persons</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="search">
				<tr>
					<td>First Name</td>
					<td><input type="text" value="" name="firstName" /></td>
					<td>Midle Name</td>
					<td><input type="text" value="" name="midleName" /></td>
					<td>Last Name</td>
					<td><input type="text" value="" name="lastName" /></td>
					<td>Email</td>
					<td><input type="text" value="" name="email" /></td>
					<td>Date birth</td>
					<td><input type="text" value="" name="date_birth" /></td>
					<td>Date birth</td>
					<td><input type="text" value="" name="date_death" /></td>
					<td><input type="submit" value="Search"></td>
				</tr>
				</form>

				<c:forEach items="${sessionScope.result}" var="person">
					<form method="post" action="Select">
					<tr>
						<td><input type="text" value="${person.id}" name="id"
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
						<td><input type="submit" value="Select" /></td>
					</tr>
					</form>
				</c:forEach>
				
				<c:if test="${sessionScope.result.size() == 0}">
						<jgc:resultempty/>
				</c:if>
				
			</tbody>
		</table>
	</center>
</body>
</html>