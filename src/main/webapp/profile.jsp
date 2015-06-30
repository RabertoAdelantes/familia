<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="logout.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal profile</title>
</head>
<body bgcolor="white">
	<center>
		<table border="1" width="30%" cellpadding="5">
			<thead>
				<tr>
					<th colspan="13">View/create personal profile</th>
				</tr>
			</thead>
			<tbody>
				<form method="get" action="profile">
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
						<td><input type="submit" value="Save"></td>
					</tr>
				</form>
			</tbody>
		</table>
	</center>
</body>
</html>