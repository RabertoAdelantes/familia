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

<link href="css/main_reg.css" rel="stylesheet">

<!-- 
<script type="text/javascript">
	$(document).ready(function() {
		$('#date_birth').datepicker({
			calendarWeeks : true,
			todayHighlight : true,
			toggleActive : true,
			todayHighlight:true,
			clearBtn:true,
			autoclose:true
		});
		$('#date_death').datepicker({
			calendarWeeks : true,
			todayHighlight : true,
			toggleActive : true,
			todayHighlight:true,
			clearBtn:true,
			autoclose:true
		});
	});
</script>
-->

<script	src="js/familia.js"></script>

<body bgcolor="white">
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-footer">
						<p>
							<fmt:message key="registration.title" bundle="${bundle}" />
						</p>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" method="post"
							action="register" enctype="multipart/form-data">
							<div class="form-group">
								<label for="firstName" class="col-sm-3 control-label"><fmt:message
										key="user.name.first" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="firstName" name="firstName"
										value="<c:out value="${bean.firstName}"/>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="midleName" class="col-sm-3 control-label"><fmt:message
										key="user.name.midle" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="midleName" name="midleName"
										value="<c:out value="${bean.midleName}"/>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="lastName" class="col-sm-3 control-label"><fmt:message
										key="user.name.last" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="lastName" name="lastName"
										value="<c:out value="${bean.secondName}"/>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="password" class="col-sm-3 control-label"> <fmt:message
										key="user.name.password" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" type="password" id="password"
										name="password"
										value="<c:out value="${bean.password}"/>"
										required="true">
								</div>
							</div>

							<div class="form-group">
								<label for="email" class="col-sm-3 control-label"> <fmt:message
										key="btn.email" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="email" name="email"
										value="<c:out value="${bean.email}"/>" required="true">
								</div>
							</div>

							<div class="form-group">
								<label class="col-xs-3 control-label"><fmt:message
										key="user.date.birth" bundle="${bundle}" /></label>
								<div class="col-xs-5 date">
									<div class="input-group input-append date" id="date_birth">
										<input type="text" class="form-control" name="date_birth" /> <span
											class="input-group-addon add-on"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label class="col-xs-3 control-label"><fmt:message
										key="user.date.death" bundle="${bundle}" /></label>
								<div class="col-xs-5 date">
									<div class="input-group input-append date" id="date_death">
										<input type="text" class="form-control" name="date_death" /> <span
											class="input-group-addon add-on"><span
											class="glyphicon glyphicon-calendar"></span></span>
									</div>
								</div>
							</div>

							<div class="form-group">
								<label for="photo" class="col-sm-3 control-label">
									<fmt:message key="user.photo" bundle="${bundle}" />
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

							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<fmt:message key="btn.register" bundle="${bundle}" />
									</button>
									<button class="btn btn-success btn-sm" onclick="location.href = '${pageContext.request.contextPath}';">Cancel</button>
								</div>
							</div>
						</form>
					</div>
					<div class="panel-footer">
						<p>
						<div>
							<h5>
								<fmt:message key="comments.dates" bundle="${bundle}" />
							</h5>
						</div>
						<jsp:include page="include/error_include.jsp" /></p>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>