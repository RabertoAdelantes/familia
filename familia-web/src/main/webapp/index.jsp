<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="isIndex" value="true" scope="request" />

<!DOCTYPE html>
<html>
<head>
<title>Login form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<%@ include file="resources/include/localized_header.jsp"%>
<link href="./resources/css/main.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">

					<div class="panel-body">
						<c:url value="${request.contextPath}/j_spring_security_check"
							var="loginUrl" />
						<div class="container" style="width: 400px;">
							<form class="form-horizontal" class="form-horizontal" role="form"
								action="<c:url value="/j_spring_security_check"/>" method="post">
								<input type="hidden" name="${_csrf.parameterName}"
									value="${_csrf.token}" />

								<h2 class="form-signin-heading">Please sign in</h2>

								<div class="form-group">
									<label for="email" class="col-sm-3 control-label"><fmt:message
											key="btn.email" bundle="${bundle}" /></label>
									<div class="col-sm-9">
										<input class="form-control" id="email" name="j_username"
											placeholder="<fmt:message key="btn.email" bundle="${bundle}" />"
											value="${requestScope.user.email}" required="true">
									</div>
								</div>

								<div class="form-group">
									<label for="password" class="col-sm-3 control-label"><fmt:message
											key="user.name.password" bundle="${bundle}" /></label>
									<div class="col-sm-9">
										<input class="form-control" id="password" name="j_password"
											placeholder="<fmt:message key="user.name.password" bundle="${bundle}"/>"
											required="true" placeholder="Password" type="password">
									</div>
								</div>
								<div class="form-group last">
									<div class="col-sm-offset-3 col-sm-9">
										<button type="submit" class="btn btn-success btn-sm">
											<fmt:message key="btn.login" bundle="${bundle}" />
										</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
