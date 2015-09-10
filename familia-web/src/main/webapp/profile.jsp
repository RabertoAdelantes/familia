<%@ include file="include/logout_include.jsp"%>
<%@ include file="include/search_include.jsp"%>
<%@ include file="include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<jsp:useBean id="user" class="com.ra.familia.entities.PersonBean"
	scope="session" />

<c:if test="${user.firstName == null}">
	<c:redirect url="/" />
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
						<td><fmt:message key="user.name.first" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<%if (user.getFirstName() != null) {
				out.println(user.getFirstName());
			}%>"
							name="firstName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.midle" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<%if (user.getMidleName() != null) {
				out.println(user.getMidleName());
			}%>"
							name="midleName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.last" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<%if (user.getSecondName() != null) {
				out.println(user.getSecondName());
			}%>"
							name="lastName" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.name.password" bundle="${bundle}"/></td>
						<td><input type="password" value="" name="password"
							disabled="disabled" /></td>
					</tr>
					<tr>
						<td><fmt:message key="btn.email" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<%if (user.getEmail() != null) {
				out.println(user.getEmail());
			}%>"
							name="email" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.date.birth" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<c:out value="${bean.dateDeath}"/>"
							name="date_birth" /></td>
					</tr>
					<tr>
						<td><fmt:message key="user.date.death" bundle="${bundle}"/></td>
						<td><input type="text"
							value="<%if (user.getDateDeath() != null) {
							out.println(user.getDateDeath());
						}
						%>"
							name="date_death" /></td>
					</tr>
					<tr>
						<td>Portrait Photo:</td>
						<td><input type="file" name="photo" size="50" /></td>

					</tr>

					<tr>
						<td>Profile Image:</td>
						<td><img src="image/?id=<%=user.getID()%>" height="200px"
							width="200px" align="left" /></td>
					</tr>

				    <tr>
					<td>User status is :</td>
					<td><%if (user.getIsActive() == true) 
							{
							out.println("Active");
			          }
			          else
			          {
			        	  out.println("<font color=\"red\"><b>Not Active</b></font>");
			          }
			          %>
					</td>
				</tr>
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