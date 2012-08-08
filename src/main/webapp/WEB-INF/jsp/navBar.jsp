<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${authUser != null}">
  <div class="authUserGreeting">
    Welcome ${authUser}!
  </div>
</c:if>

<div class="navBar">
	<a class="button" href="<c:url value='/home'/>">Home</a>
	<security:authorize ifAllGranted="ROLE_ADMIN">
		<a class="button" href="<c:url value='/sites'/>">List Sites</a>
	</security:authorize>
	<security:authorize ifAllGranted="ROLE_ADMIN">
		<a class="button" href="<c:url value='/user_form'/>">Add User</a>
	</security:authorize>
	<security:authorize ifAllGranted="ROLE_ADMIN">
		<a class="button" href="<c:url value='/users'/>">List Users</a>
	</security:authorize>
	<security:authorize ifAllGranted="ROLE_USER">
		<a class="button" href="<c:url value='/logout'/>">Logout</a>
	</security:authorize>
	<security:authorize ifNotGranted="ROLE_USER">
		<a class="button" href="<c:url value='/login'/>">Login</a>
	</security:authorize>
	<security:authorize ifNotGranted="ROLE_USER">
		<a class="button" href="<c:url value='/user_form'/>">Sign Up!</a>
	</security:authorize>
</div>