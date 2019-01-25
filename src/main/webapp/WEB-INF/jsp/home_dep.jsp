<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<p>Department: ${workers_list[0].department.title}</p>

	

	<table id="worker-table" class="table table-striped table-bordered"> 
	
		<thead>
			<tr>
				<th>Name</th>
				<th>Age</th>
				<th>Salary</th>
				<th>Department</th>
			</tr>		
		</thead>	
		
		<tbody>	 
		
			<c:forEach var="worker" items="${workers_list}">
				
		
			<tr>
				<td>${worker.name}</td>			
				<td>${worker.age}</td>
				<td>${worker.salary}</td>
				<td>${worker.department.title}</td> 			
			</tr>		
		
		
			</c:forEach>
		
		</tbody>	 
	</table>
	
	


</body>

</html>