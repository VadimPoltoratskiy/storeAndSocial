<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:forEach var="product" items="${products}">
	
	<c:url var="productInfoLink" value="/profile/product/show">
			<c:param name="productId" value="${product.id}" />
	</c:url>
	<c:url var="authorInfoLink" value="/profile/show/${product.authorProfile.user.userid}">
			
	</c:url>	
	
		<p>${product.title}</p>	
		<p>${product.price}</p>	
		<p>${product.authorProfile.user.userName} 
		<p>${product.productText}</p>
		<p>${product.category.id}</p>
		
		<p><a href="${productInfoLink}">more about product</a></p>
		<p><a href="${authorInfoLink}">more about author</a></p>
	
	</c:forEach>


</body>
</html>