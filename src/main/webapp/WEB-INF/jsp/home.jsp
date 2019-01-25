

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css" />


<script
	src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.js"
	type="text/javascript">
	
</script>

<script
	src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"
	type="text/javascript">
	
</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<c:url value="/profile/upload-profile-photo" var="uploadPhotoLink" />
		
		<form method="post" enctype="multipart/form-data" id="photoUploadForm"
			action="${uploadPhotoLink}">

			<input type="file" accept="image/*" name="file" id="photoFileInput"/> 
			
			<input type="submit" value="upload" /> 
			
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>

	<div class="hero-unit">
		<div class="page-header">
			<h1>
				Company<br> <small>List of all employees</small>
			</h1>
		</div>

	</div>


	<p>
		<a href="/cart/showItems"class="btn btn-small btn-info">Show cart</a>
	</p>


	<table id="worker-table" class="table table-striped table-bordered">

		<thead>
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th>Salary</th>
				<th>Department</th>
				<th>Update/Delete</th>
				<th>Buy</th>

			</tr>
		</thead>

		<tbody>

			<c:forEach var="worker" items="${workers_list}">

				<c:url var="updateLink" value="/worker/showFormUpdate">
					<c:param name="workerId" value="${worker.id}" />
				</c:url>

				<c:url var="deleteLink" value="/worker/delete">
					<c:param name="workerId" value="${worker.id}" />
				</c:url>

				<c:url var="buyLink" value="/cart/addItem">
					<c:param name="workerId" value="${worker.id}" />
				</c:url>



				<tr>
					<td>${worker.name}</td>
					<td>${worker.age}</td>
					<td>${worker.salary}</td>
					<td>${worker.department.title}</td>
					<td><a href="${updateLink}"class="btn btn-small btn-info">Update</a> <a href="${deleteLink}"
						onclick="if (!(confirm('Delete worker?'))) return false"
						class="btn btn-small btn-warning">Delete</a></td>

					<td><a href="${buyLink}"class="btn btn-small btn-info">Buy</a></td>
					


				</tr>


			</c:forEach>

		</tbody>
	</table>

<script	src="//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

$(document).ready(function(){
    $('#worker-table').DataTable();
});


</script>


</body>
</html>