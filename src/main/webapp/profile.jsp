<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="include\logout_include.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal profile</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="30%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">View/create personal profile</th>
				</tr>
			</thead>
			<tbody>
				<form method="get" action="profile" enctype="multipart/form-data">
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
						<td><input type="password" value="" name="password" /></td>
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
						<td>Date birth</td>
						<td><input type="text" value="" name="date_death" /></td>
					</tr>
					<tr>
						<td>Portrait Photo:</td>
						<td><input type="file" name="photo" size="50" /></td>
					</tr>
					<tr>
						<td><input type="submit" value="Save"></td>
						<td>&nbsp;</td>
					</tr>
				</form>
			</tbody>
		</table>
	</center>
</body>
</html>