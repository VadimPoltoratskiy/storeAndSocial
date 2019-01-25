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


	<c:forEach var="message" items="${messages}">
	
	<c:url var="moreInfoLink" value="/profile/message/show">
			<c:param name="messageId" value="${message.id}" />
	</c:url>	
	
	<c:url var="deleteLink" value="/profile/message/delete">
			<c:param name="messageId" value="${message.id}" />
	</c:url>	
	
		To user: ${message.profile.user.userName}
		<br>
		
		<c:set var = "string1" value = "${message.messageText}" />
      	<c:set var = "string2" value = "${fn:substring(string1, 0, 5)}" />

      <p>${string2} ...</p>	
			
		 	
		
			<a href="${moreInfoLink}">More info</a> <a href="${deleteLink}">Delete</a>
		<hr><hr>
	

	
	
	</c:forEach>


</body>
</html>