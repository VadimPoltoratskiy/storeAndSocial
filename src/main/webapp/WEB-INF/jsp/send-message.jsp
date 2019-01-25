<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:errors path="message.*" />

	<p>Your message:</p>

	<form:form modelAttribute="message">
	
		<form:hidden path="id"/>
		
		<label>To id:</label> 
		<form:input path="profile.id" /><br><br>
		
		<label>From id:</label>
		<form:input path="fromProfile.id" /><br><br>

		<form:textarea path="messageText" name="messageText" rows="10" cols="50"></form:textarea>
		
		<input type="submit" name="submit" value="Save" />
		
	</form:form>

<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>

	<script>
		tinymce.init({
			selector : 'textarea'
		});
	</script>
	
</body>
</html>