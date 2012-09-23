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
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/form.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/userView.css'/>">
  </head>
  <body>
  
  	<div id="wrapper">
  	
  		<jsp:include page="navBar.jsp"/>
  		
  		<a class="mainLink userViewGoHomeLink" href="<c:url value='/home'/>">Go Home</a>
		
		<div class="genericForm view">
			<h1>${site.name} Site</h1>
			<p></p>
			
			<label>Name:</label>
			<div class="formValue">${site.name}</div>
			
			<label>Description:</label>
			<div class="formValue">${f:convertToHtmlLineBreaks(site.description)}</div>
			
			<label>City:</label>
			<div class="formValue">${site.city}</div>
			
			<label>State:</label>
			<div class="formValue">${site.state}</div>
			
			<label>Bizvez site address:</label>
			<div class="formValue">www.bizvez.com/site/${site.address}</a></div>
			
			<security:authorize ifNotGranted="ROLE_ADMIN">
				<label></label>
				<div class="formValue">
					<a class="button redShade" href="<c:url value='/site_form'/>">Edit</a>
					<a
						class="button redShade" href="<c:url value='/site_delete'/>"
						onclick="return confirm('Are you sure you wish to delete this site?');">Delete
					</a>	
				</div>
			</security:authorize>

		</div>
  		
	    <jsp:include page="footer.jsp"/>
  	
  	</div>
    
  </body>
</html>