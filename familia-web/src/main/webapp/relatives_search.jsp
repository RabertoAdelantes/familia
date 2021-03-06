<%@ taglib prefix="jgc" uri="WEB-INF/custom.tld"%>
<%@ page import="com.ra.familia.entities.PersonBean"%>
<%@ page import="com.ra.familia.entities.TypeBean"%>
<%@ page import="java.util.Set"%>
<%@ include file="resources/include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>

<script>
	function checkform() {
		if (this.idCurrent.value == this.id.value) {
			alert("Can assign to itself");
		} else {
			self().submit();
		}
	}
</script>

<link href="css/main_reg.css" rel="stylesheet">
<title>Search</title>
</head>
<body bgcolor="white">
	<center>
		<table width="50%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">
						<h2>
							<fmt:message key="search.title.relatives" bundle="${bundle}" />
						</h2>
					</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="search">
					<input type="text" value="true" name="isRelSearch" hidden="true" />
					<input type="text" value="${requestScope.currentUserId}" name="currentId" hidden="true" />
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
					<form method="post" action="connections" name="${person.ID}">
						<tr>
							<td><input type="text" value="${person.ID}" name="id"
								hidden="true" /> <input type="text"
								value="<c:out value="${requestScope.currentUserId}" />"
								name="idCurrent" hidden="true" /></td>
							<td>${person.firstName}</td>
							<td>${person.midleName}</td>
							<td>${person.secondName}</td>
							<td>${person.email}</td>
							<td>${person.dateBirth}</td>
							<td>${person.dateDeath}</td>
							<td><select name="relatives">
									<c:forEach items="${requestScope.relTypes}" var="typeRelation">
										<option value="${typeRelation.ID}">${typeRelation.type}</option>
									</c:forEach>
							</select></td>
							<td>
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm"
										onclick="return checkform();">
										<fmt:message key="btn.assign" bundle="${bundle}" />
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