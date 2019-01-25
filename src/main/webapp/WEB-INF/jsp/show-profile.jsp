<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
		<button onclick="window.history.back()">Back</button>
	</p>


	<p>${article.articleText}</p>

	<c:url var="profilePhoto" value="/profile/profilephoto/${userId}" />

	<div class="profile-image">
		<img src="${profilePhoto}" />
	</div>

	<div>
		<p>Profile id: ${profileId}</p>
		<a href="/profile/messages/${profileId}">Messages</a>
	</div>

	<div class="profile-text">
		<c:choose>
			<c:when test="${profile.about == null}">
				No information about user
			</c:when>

			<c:otherwise>
				${profile.about}
			</c:otherwise>
		</c:choose>

		<c:forEach var="article" items="${articles}">

			<c:url var="commentLink" value="/profile/leaveComment">
				<c:param name="articleId" value="${article.id}" />
			</c:url>


			<p>${article.articleText}</p>
			
			<p> <a href="${commentLink}"> Leave Your comment</a></p>


			<c:forEach var="comment" items="${article.comments}">

				<p>${comment.text}</p>
				

			</c:forEach>
			<hr>
			<hr>


		</c:forEach>

		<p>
			<a href="/profile/edit-profile-about">Edit profile</a>
		</p>

	</div>



</body>
</html>