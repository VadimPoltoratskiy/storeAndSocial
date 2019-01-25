<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head> 

	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
		  
	<link type="text/css"
		  rel="stylesheet"
		  href="${pageContext.request.contextPath}/resources/css/style.css" />


	<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"
			type="text/javascript"> </script>

	<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"
			type="text/javascript"> </script>


</head>


<body>

<form:form action="addWorker" modelAttribute="the_worker" method="POST">


	<legend>Add worker</legend>	

	<form:hidden path="id" />
	
		<label class="control-label">First name:</label> 
		<form:input path="name" /><br><br>
		
		<label class="control-label">Age:</label>
		<form:input path="age" />	<br><br>


		<label class="control-label">Salary:</label> 
		<form:input path="salary" /><br><br>
		
		<label class="control-label">Department id:</label> 
<%-- 		<form:input path="department.dep_id" /><br><br> --%>

		<form:select path="department.dep_id">
			<form:option value="1">Prog</form:option>
			<form:option value="2">Admin</form:option>
			<form:option value="3">QA</form:option>				
		</form:select>

<input type="submit" value="Add worker" class="btn btn-primary" />


</form:form>

</body>
</html>