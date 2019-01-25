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


	<c:forEach var="profile" items="${profiles_list}">				
		
		<c:url var="profilePhoto" value="/profile/profilephoto/${profile.userId}" />
		
		<c:url var="messageLink" value="/profile/sendMessage">
			<c:param name="userId" value="${profile.userId}" />
		</c:url>

	<div class="profile-image">
		<img src="${profilePhoto}" />
	</div>
	
	<div>
		<a href="${messageLink}">Send a message</a>
	</div>
	
	<div>
		<c:choose>
			<c:when test="${profile.safeProfile.about == null}">
				No info about user
			</c:when>
			
			<c:otherwise>
				${profile.safeProfile.about}	
			</c:otherwise>
		
		</c:choose>	
	
	</div>		
		
		<hr><hr>
	
	</c:forEach>



</body>
</html>