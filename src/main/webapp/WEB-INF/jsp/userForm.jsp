<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
	<title>Bizvez - The Online Business Hub</title>
	<jsp:include page="htmlHead.jsp" />
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/form.css'/>">
	 <link rel="stylesheet" type="text/css" href="<c:url value='/css/userForm.css'/>">
	 <script src="<c:url value='/js/userForm.js'/>"></script>
</head>
<body>

	<div id="wrapper">
	
		<jsp:include page="navBar.jsp" />
		
		<div class="genericForm signup">
	    
			<form:form commandName="userAndPassword" id="userForm">
			
				<c:choose>
		    		<c:when test="${authUser == null}">
		        		<h1>Sign Up</h1>
						<p> Enter your basic details to get started </p>
		    		</c:when>
		    		<c:otherwise>
		      	 		<h1>Edit Profile Details</h1>
		    		</c:otherwise>
				</c:choose>
				
				
				<spring:hasBindErrors name="userAndPassword">
					<div class="errorsMessage"> Please fix the errors!  </div>  <form:errors />
				</spring:hasBindErrors>
				
				<security:authorize ifAllGranted="ROLE_ADMIN">
					<c:if test="${userAndPassword.user.idSet}">
						<label>ID:</label>
						<div class="formValue">${userAndPassword.user.id}</div>
					</c:if>
				</security:authorize>
				
				<spring:nestedPath path="user">
				
					<form:label path="firstName">First Name *: <span class="small">Enter your first name</span> </form:label> 
					<form:input path="firstName" /> 
					<form:errors path="firstName" cssClass="error" />
					
					<form:label path="lastName">Last Name *: <span class="small">Enter your last name</span> </form:label>  
					<form:input path="lastName" /> 
					<form:errors path="lastName" cssClass="error" />
					
					<form:label path="business">Business : <span class="small">Enter the name of your business</span> </form:label>
					<form:input path="business" /> 
					<form:errors path="business" cssClass="error" />
					
					<form:label path="title">Title : <span class="small">Enter your title (optional)</span> </form:label> 
					<form:input path="title" />
					<form:errors path="title" cssClass="error" />
					
					<form:label path="email">Email *: <span class="small">Enter your email. You'll use this to log in</span> </form:label>  
					<form:input path="email" /> 
					<form:errors path="email" cssClass="error" />
					
					<c:if test="${not profile}">
						<security:authorize ifAllGranted="ROLE_ADMIN">
						
							<form:label path="enabled">Enabled *: </form:label> 
							<form:radiobutton path="enabled" value="true" /> Yes &nbsp;&nbsp;&nbsp; 
							<form:radiobutton path="enabled" value="false" /> No 
							<form:errors path="enabled" cssClass="error" />
								
							<form:label path="admin">Admin *: </form:label> 
							<form:radiobutton path="admin" value="true" /> Yes &nbsp;&nbsp;&nbsp; 
							<form:radiobutton path="admin" value="false" /> No 
							<form:errors path="admin" cssClass="error" />
							
						</security:authorize>
					</c:if>
				
				</spring:nestedPath>
				
				<c:set var="passwordRequired"
					value="${userAndPassword.user.idSet? '' : '*'}" />
					
				<form:label path="password">Password ${passwordRequired}: <span class="small">Create a password for Bizvez</span></form:label> 
				<form:password path="password" /> 
				<form:errors path="password" cssClass="error" />
				
				<form:label path="passwordVerification">Password Verification ${passwordRequired}: <span class="small">Enter the password again</span> </form:label> 
				<form:password path="passwordVerification" /> 
				<form:errors path="passwordVerification" cssClass="error" />
				
				<a id="userSaveButton" class="button redShade" >Save</a>
				
			</form:form>
			
		</div>
		
		<jsp:include page="footer.jsp"/>
	
	</div>

</body>
</html>