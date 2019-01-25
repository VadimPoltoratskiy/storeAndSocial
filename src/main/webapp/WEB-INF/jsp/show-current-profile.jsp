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

	<c:url var="profilePhoto" value="/profile/profilephoto/${profile.userId}" />

	<div class="profile-image">
		<img src="${profilePhoto}" />
	</div>


	<div class="profile-text">
		<c:choose>
			<c:when test="${profile.safeProfile.about == null}">
				No information about user
			</c:when>
				
			<c:otherwise>
				${profile.safeProfile.about}
			</c:otherwise>
		</c:choose>

	</div>
	
	<div>
		<a href="/profile/messages/${profile.profileId}">Incoming messages (${unreadMessagesCount})</a>	
	</div>
	
	<div>
		<a href="/profile/sentMessages/${profile.profileId}">Sent messages</a>	
	</div>
	
	<c:forEach var="article" items="${articles}">
	
		<p>${article.articleDescription}</p>
		
				
		<c:url var="moreInfoLink" value="/profile/message/show">
			<c:param name="messageId" value="${message.id}" />
	</c:url>	
		<hr><hr>
		
		
	
	
	</c:forEach>
	
		
	

	
	


</body>
</html>