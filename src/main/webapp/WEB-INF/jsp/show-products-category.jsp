<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions" prefix = "fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<p><button onclick="window.history.back()">Back</button></p>


	<c:forEach var="product" items="${listProductsCategory}">
	
	
	
		Category: ${product.category.id}
		<br>
		
		<c:set var = "string1" value = "${product.productText}" />
      	<c:set var = "string2" value = "${fn:substring(string1, 0, 5)}" />

      <p>${string2} ...</p>	
			
		 	
		
		
		<hr><hr>
	

	
	
	</c:forEach>


</body>
</html>