<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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

	<p>
		<a href="/worker/list"class="btn btn-small btn-info">Main page</a>
	</p>
	<div class="hero-unit">
		<div class="page-header">
			<h1>
				Cart<br> <small>Shopping List</small>
			</h1>
		</div>

	</div>

<!-- 	<p>Cart</p> -->

	<table id="worker-table"class="table table-striped table-bordered">

		<thead>
			<tr>
				<th>Id</th>
				<th>Name</th>
				<th>Age</th>
				<th>Salary</th>
				<th>Department</th>

			</tr>
		</thead>

		<tbody>

			<c:forEach var="item" items="${cartItems}">


				<c:url var="deleteLink" value="/cart/deleteItem">
					<c:param name="workerId" value="${item.worker.id}" />
				</c:url>

				<c:url var="updateLink" value="/cart/showItems">
				</c:url>

				<tr>
					<td>${item.worker.id}</td>
					<td>${item.worker.name}</td>
					<td>${item.worker.age}</td>
					<td>${item.worker.salary}</td>
					<td>${item.worker.department.title}</td>
					<td><a href="${deleteLink}"
					class="btn btn-small btn-warning">delete</a></td>

					<td class="quantity"><input type="text"
						id="${item.getQuantityIdName()}" value="${item.quantity}" size="1" />

						<a href="${updateLink}"
						onclick="updateItemProcess(${item.worker.id})"
						class="btn btn-small btn-info">Update</a></td>

					<td>${item.getTotalPrice()}</td>


				</tr>


			</c:forEach>

		</tbody>
	</table>

	<a href="/cart/order"class="btn btn-small btn-info">Order</a>

	<script
		src="//ajax.googleapis.com/ajax/libs/jquery/1.3.2/jquery.min.js"></script>

	<script type="text/javascript">

	function updateItemProcess(idValue) {
		
		var idName = "quantity" + idValue;
		var qnt = $('#' + idName).val();
		
		$.ajax({
			
			url: "updateCart",
			data: ({
				id: idValue,
				quantity: qnt
			})			
			
		});	 
		
	}

</script>

</body>
</html>