<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link href="//cdn.datatables.net/1.10.16/css/jquery.dataTables.min.css" rel="stylesheet">
</head>
<body>

<table id="worker-table">

		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Age</th>
				<th>Salary</th>	
				<th>Department</th>				 			  
			</tr>
		</thead>

		 
	</table>


<script	src="//ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="//cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>

<script type="text/javascript">

$.getJSON("/worker/showJson", function() {
	
	console.log("success");	

}).done(function(workers) {
	
	$('#worker-table').DataTable({
		
		data: workers,
		columns: [				
			{data: 'id'},
			{data: 'name'},
			{data: 'age'},
			{data: 'salary'}  , 
			{data: 'department.title'}
			
			
		]
		
	}); 
	
	
}).fail(function() {
	
	console.log("fail!");
}); 
 


</script>


</body>
</html>