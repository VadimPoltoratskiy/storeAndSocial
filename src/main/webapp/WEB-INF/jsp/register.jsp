<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<form:errors path="the_user.*" />


	<form:form action="register" modelAttribute="the_user" method="POST">

		<legend>Register</legend>

		<label class="control-label">First name:</label>
		<form:input path="userName" />
		<br>
		<br>

		<label class="control-label">Email:</label>
		<form:input path="email" />
		<br>
		<br>


		<label class="control-label">Password:</label>
		<form:input path="password" />
		<br>
		<br>

		<input type="submit" value="Add user" />

	</form:form>

</body>
</html>