<!DOCTYPE html>
<html>
<head>
<title>Login form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="include/localized_header.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>

<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<link href="css/main.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class=""><fmt:message key="registration.title"
								bundle="${bundle}" /></strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" method="post"
							action="login">
							<div class="form-group">
								<label for="email" class="col-sm-3 control-label"><fmt:message
										key="btn.email" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="email" name="name"
										placeholder="<fmt:message key="btn.email" bundle="${bundle}"/>"
										required="true">
								</div>
							</div>
							<div class="form-group">
								<label for="password" class="col-sm-3 control-label"><fmt:message
										key="user.name.password" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="password" name="password"
										placeholder="<fmt:message key="user.name.password" bundle="${bundle}"/>"
										required="true" type="password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="checkbox">
										<input type="checkbox" name="remember"/>
										<fmt:message key="link.remember" bundle="${bundle}" />
									</div>
								</div>
							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<fmt:message key="btn.login" bundle="${bundle}" />
									</button>
								</div>
							</div>
							<jsp:include page="include/error_include.jsp" />
						</form>
					</div>
					<div class="panel-footer">
						<fmt:message key="register.not" bundle="${bundle}" />
						<a href="register.jsp" class=""><fmt:message
								key="register.here" bundle="${bundle}" /></a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>