<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="include\logout_include.jsp"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.ra.familia.entities.PersonBean"
	scope="session" />

<c:if test="${user.firstName == null}">
	<c:redirect url="/"/>
</c:if>

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
					<th colspan="13">View personal profile</th>
				</tr>
			</thead>
			<tbody>
				<form method="post" action="profile" enctype="multipart/form-data">
					<tr>
						<td></td>
						<td><input type="hidden" value="<%=user.getID()%>" name="id" /></td>
					</tr>
					<tr>
					<tr>
						<td>First Name</td>
						<td><input type="text"
							value="<%if (user.getFirstName() != null) {
				out.println(user.getFirstName());
			}%>"
							name="firstName" /></td>
					</tr>
					<tr>
						<td>Midle Name</td>
						<td><input type="text"
							value="<%if (user.getMidleName() != null) {
				out.println(user.getMidleName());
			}%>"
							name="midleName" /></td>
					</tr>
					<tr>
						<td>Last Name</td>
						<td><input type="text"
							value="<%if (user.getSecondName() != null) {
				out.println(user.getSecondName());
			}%>"
							name="lastName" /></td>
					</tr>
					<tr>
						<td>Password</td>
						<td><input type="password" value="" name="password"
							disabled="disabled" /></td>
					</tr>
					<tr>
						<td>Email</td>
						<td><input type="text"
							value="<%if (user.getEmail() != null) {
				out.println(user.getEmail());
			}%>"
							name="email" /></td>
					</tr>
					<tr>
						<td>Date birth</td>
						<td><input type="text"
							value="<%if (user.getDateBirth() != null) {
				out.println(user.getDateBirth());
			}%>"
							name="date_birth" /></td>
					</tr>
					<tr>
						<td>Date death</td>
						<td><input type="text"
							value="<%if (user.getDateDeath() != null) {
			}%>"
							name="date_death" /></td>
					</tr>
					<tr>
						<td>Portrait Photo:</td>
						<td><input type="file" name="photo" size="50" /></td>
						
					</tr>
					
					<tr>
						<td>Profile Image:</td>
						<td><img src="image/?id=<%=user.getID()%>" height="200px" width="200px" align="left" /></td>
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