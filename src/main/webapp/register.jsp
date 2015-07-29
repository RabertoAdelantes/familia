<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function redirect(elem){
     elem.setAttribute("action","index.jsp");
     elem.submit();
}
</script>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="30%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">Register new user</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="register" enctype="multipart/form-data">
					<tr>
					<tr>
						<td>First Name</td>
						<td><input type="text" value="" name="firstName" /></td>
					</tr>
					<tr>
						<td>Midle Name</td>
						<td><input type="text" value="" name="midleName" /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type="text" value="" name="lastName" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" value="" name="password" T /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text" value="" name="email" /></td>
					</tr>
					<tr>
						<td>Date birth</td>
						<td><input type="text" value="" name="date_birth" /></td>
					</tr>
					<tr>
						<td>Date death</td>
						<td><input type="text" value="" name="date_death" /></td>
					</tr>
					<tr>
						<td>Portrait Photo:</td>
						<td><input type="file" name="photo" size="50" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Register"></td>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><c:if test="${requestScope.request_error != null}">
								<c:out value="${requestScope.request_error}" />
							</c:if></td>
						<td></td>
					</tr>
				</form>
				<form method="post" action="" onsubmit="redirect(this);">
					<table border="0" width="30%" cellpadding="3">
						<tbody>
							<tr>
								<td><input type="submit" value="Login" /></td>
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