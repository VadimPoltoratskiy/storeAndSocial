<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<title>Welcome</title>
</head>

<body>
	<div>
		<h2>Admin page</h2>		 
	</div>

	<div>
		<h1>Hello, 
			<c:out value="${pageContext.request.remoteUser}"></c:out>
		</h1>
		
		<form action="/logout" method="post">
			<input type="submit" value="Sign Out" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>
	</div>

</body>
</html>
