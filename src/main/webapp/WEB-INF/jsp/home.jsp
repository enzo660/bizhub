<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizvez - The Online Business Hub</title>
    <jsp:include page="htmlHead.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/home.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/form.css'/>">
  </head>
  <body>
  
  	<div id="wrapper">
  		
  		<jsp:include page="navBar.jsp"/>
  		
  		<div id="welcomeMessage">
  			<c:if test="${authUser != null}">
			  <div class="authUserGreeting">
			    Welcome <span class="name">${authUser}</span>!
			  </div>
			</c:if>
		    <security:authorize ifAllGranted="ROLE_USER">
				<a class="mainLink" href="<c:url value='/user'/>">My Profile</a>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_USER">
				<c:choose>
		    		<c:when test="${empty site}">
		        		<a class="mainLink" href="<c:url value='/site_form'/>"> Create My Site</a>
		    		</c:when>
		    		<c:otherwise>
		      	 		<a class="mainLink" href="<c:url value='/site'/>">My Site</a>
		    		</c:otherwise>
				</c:choose>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_ADMIN">
				<a class="mainLink" href="<c:url value='/user_form'/>">Add User</a>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_ADMIN">
				<a class="mainLink" href="<c:url value='/users'/>">List Users</a>
			</security:authorize>
			<security:authorize ifAllGranted="ROLE_ADMIN">
				<a class="mainLink" href="<c:url value='/sites'/>">List Sites</a>
			</security:authorize>
  		</div>
		
		<div id="contentWrapper">

			<img src="<c:url value='/images/bizvez-logo.jpg'/>"  class="figure" alt="Bizvez Logo" width="510px" height="155px">

			<div class = "logoText">The online business hub</div>

			<div id="howToCreate">
				<p class="stepIntro">Want to exhibit your business online? Create an address on Bizvez in few clicks.</p>

				<div id="step1" class="stepContent">			
					<p> <span class="number"> 1. </span> Click on the 'Tour' link in the bar at the top if you'd like a quick tour.<p>
					<p>Create your account using the <span class="bold">'Sign Up'</span> link on the top or the <span class="greenText bold">green</span> button below.</p>	
				</div>

				<div id="step2" class="stepContent">
					<p> <span class="number"> 2. </span> Create your business 'site'. </p>
					<p>Enter basic details like the name of your business, description, city, state, etc.<p>
					<p>Pick a unique name to address your business.</p>
				</div>
				
				<div id="step3" class="stepContent">
					<p> <span class="number"> 3. </span> Use the editor to create basic content for your business. </p>
					<p>That's it! You're online!</p>
					<security:authorize ifNotGranted="ROLE_USER">
						<a id="freeButton" class="button greenShade bold" href="<c:url value='/user_form'/>" >It's Free!</a>
					</security:authorize>
					<security:authorize ifAllGranted="ROLE_USER">
						<a id="freeButton" class="button greenShade bold" href="<c:url value='/site_form'/>" >My Site</a>
					</security:authorize>
					
				</div>
			</div>
			
		</div>
  		
  		<jsp:include page="footer.jsp"/>
  		
  	</div>
    
  </body>
</html>