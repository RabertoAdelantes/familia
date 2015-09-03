<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login form</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

<style type="text/css">
body {
	background: url(imagines/index_logo.jpg) no-repeat center center fixed;
	-webkit-background-size: cover;
	-moz-background-size: cover;
	-o-background-size: cover;
	background-size: cover;
}

.panel-default {
	opacity: 0.9;
	margin-top: 30px;
}

.form-group.last {
	margin-bottom: 0px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-4 col-md-offset-7">
				<div class="panel panel-default">
					<div class="panel-heading">
						<strong class="">Login</strong>
					</div>
					<div class="panel-body">
						<form class="form-horizontal" role="form" method="post"
							action="login">
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-3 control-label">Email</label>
								<div class="col-sm-9">
									<input class="form-control" id="userName"
										placeholder="Email" required="true">
								</div>
							</div>
							<div class="form-group">
								<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
								<div class="col-sm-9">
									<input class="form-control" id="password"
										placeholder="Password" required="true" type="password">
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-3 col-sm-9">
									<div class="checkbox">
										<label class=""> <input type="checkbox" class="">Remember me</label>
									</div>
								</div>
							</div>
							<div class="form-group last">
								<div class="col-sm-offset-3 col-sm-9">
									<button type="submit" class="btn btn-success btn-sm">Sign
										in</button>
								</div>
							</div>
							<jsp:include page="include/error_include.jsp" />
						</form>
					</div>
					<div class="panel-footer">
						Not Registered? <a href="register.jsp" class="">Register here</a>
					</div>
				</div>

			</div>
		</div>
	</div>
</body>
</html>