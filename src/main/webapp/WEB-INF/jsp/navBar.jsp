<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div id="topNavigation">
	<span>Bizvez - The Online Business Hub</span>

	<span id="links">
	
		<a href="<c:url value='/home'/>">Home</a>
		
		<a href="<c:url value='/tour'/>">Tour</a>
		
		<a href="http://twitter.com/bizvez">Follow Us</a>
		
		<a href="<c:url value='/about'/>">About</a>
		
		<security:authorize ifNotGranted="ROLE_USER">
			<a href="<c:url value='/user_form'/>">Sign Up</a>
		</security:authorize>
		
		<security:authorize ifNotGranted="ROLE_USER">
			<a href="<c:url value='/login'/>">Login</a>
		</security:authorize>
		
		<security:authorize ifAllGranted="ROLE_USER">
			<a href="<c:url value='/logout'/>">Logout</a>
		</security:authorize>
	
	</span>
	
</div>