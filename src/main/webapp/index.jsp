<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/main.css">
<title>Grande Familia</title>
</head>
<body>
	<form method="post" action="login">
		<center>
			<table border="0" width="30%" cellpadding="3">
				<thead>
					<tr>
						<th colspan="2">Login Page</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>Username</td>
						<td><input type="text" name="userName" value="" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" name="password" value="" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Login" /></td>
						<td><jsp:include page="include/error_include.jsp" /></td>
					</tr>
				</tbody>
			</table>
		</center>
	</form>
	<jsp:include page="include/register_include.jsp" />
</body>
</html>