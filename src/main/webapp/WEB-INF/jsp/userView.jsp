<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Bizvez - The Online Business Hun</title>
<jsp:include page="htmlHead.jsp" />
<link rel="stylesheet" type="text/css" href="<c:url value='/css/form.css'/>">
</head>
<body>

	<div id="wrapper">
	
		<jsp:include page="navBar.jsp" />
		
		<div class="genericForm">
			<h1>${user.name}</h1>
			<p></p>
			
			<label>First Name:</label>
			<div class="formValue">${user.firstName}</div>
			
			<label>Last Name:</label>
			<div class="formValue">${user.lastName}</div>
			
			<label>Business:</label>
			<div class="formValue">${user.business}</div>
			
			<label>Title:</label>
			<div class="formValue">${user.title}</div>
			
			<label>Email:</label>
			<div class="formValue"><a href="mailto:${user.email}">${user.email}</a></div>
			
			<label>Password:</label>
			<div class="formValue">${f:mask(user.passwordDigest)}</div>
			
			<label>Created:</label>
			<div class="formValue"><fmt:formatDate value="${user.created}"
							pattern="MMMMM d, yyyy hh:mma" /></div>
							
			<security:authorize ifAllGranted="ROLE_ADMIN">			
				<label>Enabled:</label>
				<div class="formValue">${user.enabled? 'Yes' : 'No'}</div>			
				<label>Admin:</label>
				<div class="formValue">${user.admin? 'Yes' : 'No'}</div>			
			</security:authorize>
			
			<security:authorize ifNotGranted="ROLE_ADMIN">
				<label></label>
				<div class="formValue">
					<a class="button" href="<c:url value='/user_form'/>">Edit</a>
					<a
						class="button" href="<c:url value='/user_delete'/>"
						onclick="return confirm('Are you sure you wish to delete this profile?');">Delete
					</a>	
				</div>
			</security:authorize>

		</div>
			
		<jsp:include page="footer.jsp"/>
	
	</div>
	
</body>
</html>