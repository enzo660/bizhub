<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
        
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizvez</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
    <jsp:include page="navBar.jsp"/>
    <p>
      willkomen to bzvz
    </p>
    <security:authorize ifAllGranted="ROLE_USER">
		<a class="button" href="<c:url value='/user'/>">My Profile</a>
	</security:authorize>
	<security:authorize ifAllGranted="ROLE_USER">
		<c:choose>
    		<c:when test="${empty site}">
        		<a class="button" href="<c:url value='/site_form'/>"> Create my Site</a>
    		</c:when>
    		<c:otherwise>
      	 		<a class="button" href="<c:url value='/site'/>">My Site</a>
    		</c:otherwise>
		</c:choose>
		
	</security:authorize>
	<p>
    </p>
  </body>
</html>