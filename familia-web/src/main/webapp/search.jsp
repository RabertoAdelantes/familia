<%@ taglib prefix="jgc" uri="WEB-INF/custom.tld"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="resources/include/localized_header.jsp"%>
<link href="resources/css/main.css" rel="stylesheet">

<title>Search</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="50%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">
						<h2>
							<fmt:message key="search.title" bundle="${bundle}" />
						</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="search">
					<tr>
						<td>&nbsp;</td>
						<td><fmt:message key="user.name.first" bundle="${bundle}" /></td>
						<td><fmt:message key="user.name.midle" bundle="${bundle}" /></td>
						<td><fmt:message key="user.name.last" bundle="${bundle}" /></td>
						<td><fmt:message key="btn.email" bundle="${bundle}" /></td>
						<td><fmt:message key="user.date.birth" bundle="${bundle}" /></td>
						<td><fmt:message key="user.date.death" bundle="${bundle}" /></td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
						<td><input type="text" value="" name="firstName" /></td>
						<td><input type="text" value="" name="midleName" /></td>
						<td><input type="text" value="" name="lastName" /></td>
						<td><input type="text" value="" name="email" /></td>
						<td><input type="text" value="" name="date_birth"
							disabled="disabled" /></td>
						<td><input type="text" value="" name="date_death"
							disabled="disabled" /></td>
						<td>
							<div class="col-sm-offset-3 col-sm-9">
								<button type="submit" class="btn btn-success btn-sm">
									<fmt:message key="btn.search" bundle="${bundle}" />
								</button>
							</div>
						</td>
						<td>&nbsp;</td>
					</tr>
				</form>
				<c:forEach items="${requestScope.result}" var="person">
					<form method="post" action="Select">
						<tr>
							<td><input type="text" value="${person.ID}" name="id"
								hidden="true" /></td>
							<td>${person.firstName}</td>
							<td>${person.midleName}</td>
							<td>${person.secondName}</td>
							<td>${person.email}</td>
							<td>${person.dateBirth}</td>
							<td>${person.dateDeath}</td>
							<c:if test="${sessionScope.isAdmin==true}">
								<td><a
									href='<c:out value="${pageContext.request.contextPath}/activate?id=${person.ID}"/>'><c:out
											value="${person.isActive eq true ? '': 'ACTIVATE'}" /></td>
								</td>
							</c:if>
							<td>
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<fmt:message key="btn.open" bundle="${bundle}" />
									</button>
								</div>

							</td>
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