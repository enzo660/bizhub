<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizvez - The Online Business Hub</title>
    <jsp:include page="htmlHead.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/form.css'/>">
    <script src="<c:url value='/js/login.js'/>"></script>
  </head>
  <body>
  
  	<div id="wrapper">
  	
  		<jsp:include page="navBar.jsp"/>
  		
	    <div class="genericForm">
	    
			<form action="<c:url value='/security_check.html'/>" method="post" id="loginForm">
			
				<!-- To show errors during login  -->
				<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">        
		            <strong>	
		            	Error:       
		                <spring:message code="loginError.${f:getType(sessionScope.SPRING_SECURITY_LAST_EXCEPTION)}" 
		                text="Your login attempt was not successful, please try again."/>
		            </strong><br/>
		            <spring:message code="loginErrorExtra.${f:getType(sessionScope.SPRING_SECURITY_LAST_EXCEPTION)}" 
		                text="Reason: ${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}"/>
		            <c:set scope="session" var="SPRING_SECURITY_LAST_EXCEPTION" value="${null}"/>	            
		        </c:if>
			
				<h1>Login</h1>
	
				<label>Email
				<span class="small">Enter your email address registered with Bizvez.</span>
				</label>
				<input type="text"  name="j_username" id="j_username" 
	              value="${sessionScope.SPRING_SECURITY_LAST_USERNAME}" />
	
				<label>Password
				</label>
				<input type="password"  name="j_password" id="j_password" />
				
				<label>Remember me:
				<span class="small">(Remember me on this computer)</span>
				</label>
				<input type="checkbox" name="_spring_security_remember_me" 
	            id="_spring_security_remember_me"/>
	
				<div id="signUpButtonContainer">
					<a id="showEditorLink" class="button redShade login" >Show Editor!</a>
				</div>
		
			</form>
			
		</div>
	    
	    <jsp:include page="footer.jsp"/>
  	
  	</div>
    
  </body>
</html>