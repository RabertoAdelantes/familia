<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="include/localized_header.jsp"%>

<!DOCTYPE html>
<html>
<head>
<title>Login form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<script src="http://code.jquery.com/jquery-1.11.3.js"></script>	
<script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>

<link href="css/main.css" rel="stylesheet">

</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">

					<div class="panel-body">
						<form class="form-horizontal" role="form" method="post"
							action="login">
							<div class="form-group">
								<label for="email" class="col-sm-3 control-label"><fmt:message
										key="btn.email" bundle="${bundle}" /></label>
								<div class="col-sm-9">
									<input class="form-control" id="email" name="email"
										placeholder="<fmt:message key="btn.email" bundle="${bundle}" />"
										value="${requestScope.user.email}" required="true">
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
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">
										<fmt:message key="btn.login" bundle="${bundle}" />
									</button>
								</div>
							</div>
						</form>
					</div>
					<div class="panel-footer">
						<fmt:message key="register.not" bundle="${bundle}" />
						<a href="register.jsp" class=""><fmt:message
								key="register.here" bundle="${bundle}" /></a>
						<div>
							<jsp:include page="include/error_include.jsp" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>