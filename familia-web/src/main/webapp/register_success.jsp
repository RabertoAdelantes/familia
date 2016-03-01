<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="resources/include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="css/main.css">
<title><fmt:message key="register.success" bundle="${bundle}"/></title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="30%" cellpadding="5">
			<tbody>
				<h1><fmt:message key="register.success" bundle="${bundle}"/></h1>
				<form action="index.jsp">
					<table border="0" width="30%" cellpadding="3">
						<tbody>
							<tr>
								<td>
								<input type="submit" value="<fmt:message key="btn.login" bundle="${bundle}"/>" /></td>
							</tr>
						</tbody>
					</table>
					</center>
				</form>
			</tbody>
		</table>
	</center>
</body>
</html>