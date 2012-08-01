<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>${user.name} User</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
    <jsp:include page="navBar.jsp"/>
    <p>${user.name} User</p>
	<table class="nameValuePairs">
		<tr>
			<th>First Name:</th>
			<td>${user.firstName}</td>
		</tr>
		<tr>
			<th>Last Name:</th>
			<td>${user.lastName}</td>
		</tr>
		<tr>
			<th>Business:</th>
			<td>${user.business}</td>
		</tr>
		<tr>
			<th>Title:</th>
			<td>${user.title}</td>
		</tr>
		<tr>
			<th>Email:</th>
			<td><a href="mailto:${user.email}">${user.email}</a></td>
		</tr>
		<tr>
			<th>Password:</th>
			<td>${f:mask(user.passwordDigest)}</td>
		</tr>
		<tr>
			<th>Created:</th>
			<td><fmt:formatDate value="${user.created}"
					pattern="MMMMM d, yyyy hh:mma" /></td>
		</tr>
		<security:authorize ifAllGranted="ROLE_ADMIN">
			<tr>
				<th>Enabled:</th>
				<td>${user.enabled? 'Yes' : 'No'}</td>
			</tr>
			<tr>
				<th>Admin:</th>
				<td>${user.admin? 'Yes' : 'No'}</td>
			</tr>
			<tr>
				<th>&nbsp;</th>
				<td style="padding-top: 10px;">
					<a class="button" href="<c:url value='/user_form?id=${user.id}'/>">Edit</a> 
					<a class="button" href="<c:url value='/user_delete?id=${user.id}'/>" onclick="return confirm('Are you sure you wish to delete this user?');">Delete</a>
				</td>
			</tr>
		</security:authorize>
	</table>
</body>
</html>