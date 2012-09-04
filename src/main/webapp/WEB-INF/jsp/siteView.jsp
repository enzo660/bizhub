<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>${site.name} Site</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
  
  	<div id="wrapper">
  	
  		<jsp:include page="navBar.jsp"/>
  		
	    <h1>${site.name} Site</h1>
	    <table class="nameValuePairs">
	      <tr>
	        <th>Name:</th>
	        <td>${site.name}</td>
	      </tr>
	      <tr>
	        <th>Description:</th>
	        <td>${f:convertToHtmlLineBreaks(site.description)}</td>
	      </tr>
	      <tr>
	        <th>City:</th>
	        <td>${site.city}</td>
	      </tr>
	      <tr>
	        <th>State:</th>
	        <td>${site.state}</td>
	      </tr>
	      <tr>
	        <th>Bizvez site address:</th>
	        <td>${site.address}</td>
	      </tr>
	      <tr>
	        <th>Content:</th>
	        <td>${site.content}</td>
	      </tr>
	      
	      <security:authorize ifNotGranted="ROLE_ADMIN">
	      	<tr>
		        <th>&nbsp;</th>
		        <td style="padding-top: 10px;">
		          <a class="button" href="<c:url value='/site_form'/>">Edit</a>
		          <a class="button" href="<c:url value='/site_delete'/>"
			       onclick="return confirm('Are you sure you wish to delete this site?');">Delete</a>
		        </td>
		      </tr>
	      </security:authorize>
	      
	    </table>
	    
	    <jsp:include page="footer.jsp"/>
  	
  	</div>
    
  </body>
</html>