<%@ include file="resources/include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="jgc" uri="WEB-INF/custom.tld"%>
<%@ page import="com.ra.familia.entities.PersonBean"%>
<%@ page import="java.util.Set"%>
<jsp:useBean id="user" class="com.ra.familia.entities.PersonBean"
	scope="request" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Personal profile</title>
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker.min.css" />
<link rel="stylesheet"
	href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/css/datepicker3.min.css" />

<script
	src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.3.0/js/bootstrap-datepicker.min.js"></script>
<script src="resources/js/familia.js"></script>

<link href="resources/css/main_reg.css" rel="stylesheet">
</head>
<body bgcolor="white">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-footer">
						<p>
							<fmt:message key="personal.title" bundle="${bundle}" />
						</p>
					</div>
					<div class="panel-body">

						<form class="form-horizontal" role="profile" method="post"
							action="update" enctype="multipart/form-data">

							<div class="form-group">
								<label for="firstName" class="col-sm-3 control-label"></label>
								<div class="col-sm-9">

									<input type="hidden" class="form-control"
										value="<%=user.getID()%>" name="id" name="id" />
								</div>
							</div>


							<div class="form-group">
								<label for="firstName" class="col-sm-3 control-label"><fmt:message
										key="user.name.first" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="firstName" name="firstName"
										value="<%if (user.getFirstName() != null) {
				out.println(user.getFirstName());
			}%>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="midleName" class="col-sm-3 control-label"><fmt:message
										key="user.name.midle" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="midleName" name="midleName"
										data-fv-notempty="false"
										value="<%if (user.getMidleName() != null) {
				out.println(user.getMidleName());
			}%>">
								</div>
							</div>

							<div class="form-group">
								<label for="lastName" class="col-sm-3 control-label"><fmt:message
										key="user.name.last" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="lastName" name="lastName"
										value="<%if (user.getSecondName() != null) {
				out.println(user.getSecondName());
			}%>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-3 control-label"> <fmt:message
										key="btn.email" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="email" name="email"
										value="<%if (user.getEmail() != null) {
				out.println(user.getEmail());
			}%>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label class="col-xs-3 control-label"><fmt:message
										key="user.date.birth" bundle="${bundle}" /></label>
								<div class="col-xs-5 date">
									<div class="input-group input-append date" id="date_birth">
										<input type="text" class="form-control" name="date_birth"
											value="<%if (user.getDateBirth() != null) {
				out.println(user.getDateBirth());
			}%>" />
										<span class="input-group-addon add-on"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-xs-3 control-label"><fmt:message
										key="user.date.death" bundle="${bundle}" /></label>
								<div class="col-xs-5 date">
									<div class="input-group input-append date" id="date_death">
										<input type="text" class="form-control" name="date_death"
											value="<%if (user.getDateDeath() != null) {
				out.println(user.getDateBirth());
			}%>" />
										<span class="input-group-addon add-on"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="photo" class="col-sm-3 control-label"> <fmt:message
										key="user.photo" bundle="${bundle}" />
								</label>
								<div style="position: relative;">
									<a class='btn btn-primary' href='javascript:;'> <fmt:message
											key="user.photo.select" bundle="${bundle}" /> <input
										type="file"
										style="position: absolute; z-index: 2; top: 0; filter: alpha(opacity = 0); opacity: 0; background-color: transparent; color: transparent;"
										name="photo"
										onchange='$("#upload-file-info").html($(this).val());'>
									</a> &nbsp; <span class='label label-info' id="upload-file-info"></span>
								</div>


								<div class="form-group">
									<label for="photo" class="col-sm-3 control-label"> <fmt:message
											key="user.photo" bundle="${bundle}" />
									</label> <br>
									<div style="position: relative;">
										<img src="image/?id=<%=user.getID()%>" height="200px"
											width="300px" align="left" />
									</div>
								</div>

								<div class="form-group">
									<label for="relatives" class="col-sm-3 control-label">
										<fmt:message key="user.relatives" bundle="${bundle}" />
									</label>
									<table width="600px" cellpadding="5" style="border:1px solid gray">
										<tbody>
											<tr style="border:1px solid gray">
												<td style="border:1px solid gray" width="150px" align="center"><b><fmt:message
															key="user.name.first" bundle="${bundle}" /></b></td>
												<td style="border:1px solid gray" width="150px" align="center"><b><fmt:message
															key="user.name.midle" bundle="${bundle}" /></b></td>
												<td style="border:1px solid gray" width="150px" align="center"><b><fmt:message
															key="user.name.last" bundle="${bundle}" /></b></td>
												<td style="border:1px solid gray" width="50px" align="center"><b><fmt:message
															key="user.relatives" bundle="${bundle}" /></b></td>
											</tr>
											<c:forEach items="${requestScope.relatives}" var="person">
												<tr style="border:1px solid gray">
													<td style="border:1px solid gray" align="center">${person.firstName}</td>
													<td style="border:1px solid gray" align="center">${person.midleName}</td>
													<td style="border:1px solid gray" align="center">${person.secondName}</td>
													<td style="border:1px solid gray" align="center">${person.connection}</td>
												</tr>
											</c:forEach>
									</table>
								</div>

								<div class="form-group last">
									<div class="col-sm-offset-3 col-sm-9">
										<button type="submit" class="btn btn-success btn-sm">
											<fmt:message key="btn.save" bundle="${bundle}" />
										</button>
									</div>
								</div>
						</form>

						<form class="form-horizontal" role="profile" method="post"
							action="relatives">
							<input type="text" value="${user.getID()}" name="currentUserId"
								hidden="true" />
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<fmt:message key="btn.updaterelatives" bundle="${bundle}" />
									</button>
								</div>
							</div>
						</form>
						<%@ include file="resources/include/search_include.jsp"%>
					</div>
					<div class="panel-footer">
						<p>
						<div>
							<h5>
								<fmt:message key="comments.dates" bundle="${bundle}" />
							</h5>
						</div>
						<jsp:include page="resources/include/error_include.jsp" />
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>