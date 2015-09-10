<%@ include file="include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<html>
<head>
<title>Registration</title>
</head>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<script type="text/javascript">
$(function() {
  $( "#datebirth" ).datepicker();
  $( "#datedeath" ).datepicker();
});
</script>

<body bgcolor="white">
	<center>
		<table border="0" width="20%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13"><fmt:message key="registration.title" bundle="${bundle}"/></th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="register" enctype="multipart/form-data">
					<tr>
					<tr>
						<td><fmt:message key="user.name.first" bundle="${bundle}"/></td>
						<td><input type="text" value="<c:out value="${bean.firstName}"/>" name="firstName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.midle" bundle="${bundle}"/></td>
						<td><input type="text" value="<c:out value="${bean.midleName}"/>" name="midleName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.last" bundle="${bundle}"/></td>
						<td><input type="text" value="<c:out value="${bean.secondName}"/>" name="lastName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.password" bundle="${bundle}"/></td>
						<td><input type="password" value="<c:out value="${bean.password}"/>" name="password"/></td>
					</tr>
					<tr>
						<td><fmt:message key="btn.email" bundle="${bundle}"/></td>
						<td><input type="text" value="<c:out value="${bean.email}"/>" name="email" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.date.birth" bundle="${bundle}"/></td>
						<td><input type="text" value="<c:out value="${bean.dateBirth}"/>" name="date_birth"
							id="datebirth" />
					</tr>
					<tr>
						<td><fmt:message key="user.date.death" bundle="${bundle}"/></td>
						<td><input type="date" value="<c:out value="${bean.dateDeath}"/>" name="date_death"
							id="datedeath" />
					</tr>
					<tr>
						<td>Portrait Photo</td>
						<td><input type="file" name="photo" size="50" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="<fmt:message key="btn.register" bundle="${bundle}"/>"></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td></td>
						<td><c:if test="${requestScope.request_error != null}">
								<font color="red">ERROR : </font><b><c:out value="${requestScope.request_error}" /></b>
							</c:if>
						<h5><fmt:message key="comments.dates" bundle="${bundle}"/></h5>	
						</td>
					</tr>
				</form>
			</tbody>
		</table>
	</center>
</body>
</html>