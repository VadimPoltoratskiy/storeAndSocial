<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Access denied</title>
</head>
<body>

	<div>
		<div>
			<h3>You do not have permission to access this page!</h3>
		</div>
		<form action="/logout" method="post">
			<input type="submit" value="Sign in as different user" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>
</body>
</html>
