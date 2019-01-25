<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />


<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"
	type="text/javascript">
	
</script>

<title>Admin login form</title>
</head>
<body>


	<form action="/worker/login" method="post">

		<div>
			<div>
				<input type="text" name="username" placeholder="User Name" />
			</div>
			<div>
				<input type="password" name="password" placeholder="Password" />
			</div>
			<div>
				<input type="submit" value="Sign In" />
			</div>

			<c:if test="${param.error != null}">
				<div>Wrong username and password.</div>
			</c:if>

		</div>
		
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>

</body>
</html>
