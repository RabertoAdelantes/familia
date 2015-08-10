<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function redirect(elem){
     elem.setAttribute("action","index.jsp");
     elem.submit();
}
</script>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registration</title>
</head>
<body bgcolor="white">
	<center>
		<table border="0" width="30%" cellpadding="5">
			<tbody>
				<h1> THE NEW USER SUCCEFULLY REGISTERED </h1>
				<h3>please activate your account and login. </h3>
				<form method="post" action="" onsubmit="redirect(this);">
					<table border="0" width="30%" cellpadding="3">
						<tbody>
							<tr>
								<td><input type="submit" value="Login" /></td>
							</tr>
						</tbody>
					</table>
					</center>
				</form>
			</tbody>
		</table>
	</center>
</body>
</html>