<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="resources/include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<link rel="stylesheet" href="resources/css/main.css">
<title>Activation failed</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="30%" cellpadding="5">
			<tbody>
				<h1>Activation failed. Your link can be inactive or has already used.</h1>
				<h3><fmt:message key="reg.email" bundle="${bundle}"/></h3>
				<form action="index.jsp">
					<table border="0" width="30%" cellpadding="3">
						<tbody>
							<tr>
								<td>
								<input type="submit" value="Login" /></td>
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