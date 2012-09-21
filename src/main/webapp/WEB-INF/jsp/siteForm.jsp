<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizvez - The Online Business Hub</title>
    <jsp:include page="htmlHead.jsp"/>
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/siteForm.css'/>">
    <script src="<c:url value='/js/siteForm.js'/>"></script>
    <script src="<c:url value='/js/ckeditor/ckeditor.js'/>"></script>
  </head>
  <body>
  
  	<div id="wrapper">
  	
  		<jsp:include page="navBar.jsp"/>
  		
  		<div id="siteFormMain" class="genericForm signup site">
  		
  			<form:form commandName="site" id="siteForm" >
  			
  				<div id="detailsDiv">
  					<h1>Site Details</h1>
	  				<p></p>
	  				
	  				<spring:hasBindErrors name="site">
						<div class="errorsMessage"> Please fix the errors!  </div>  <form:errors />
		        	</spring:hasBindErrors>
		        	
		        	<security:authorize ifAllGranted="ROLE_ADMIN">
						<c:if test="${site.idSet}">
							<label>ID:</label>
							<div class="formValue">${site.id}</div>
						</c:if>
					</security:authorize>
					
					<form:label path="name">Site Name *: <span class="small">Enter a name for your site. Could be the same as the name for your business.</span> </form:label> 
					<form:input path="name" /> 
					<form:errors path="name" cssClass="error" />
					
					<form:label path="description">Description : <span class="small">Enter a brief description for your site. (optional)</span> </form:label> 
					<form:input path="description" /> 
					<form:errors path="description" cssClass="error" />
					
					<form:label path="city">City *: <span class="small">Enter the name of the city your business is in.</span> </form:label> 
					<form:input path="city" /> 
					<form:errors path="city" cssClass="error" />
					
					<form:label path="state">State *: <span class="small">Enter the name of the state your business is in.</span> </form:label> 
					<form:input path="state" /> 
					<form:errors path="state" cssClass="error" />
					
					<form:label path="address">Bizvez Site Address : <span class="small">This is how your site will be addressed. If you enter 'joe-realtor', your address will be <span class="sampleLink">www.bizvez.com/site/joe-realtor</span></span> </form:label> 
					<form:input path="address" /> 
					<form:errors path="address" cssClass="error" />
  				</div>
  				
				<div id="contentDiv">
					<form:textarea id="content" path="content"/> 
					<form:errors path="content" cssClass="error" />
				</div>
				
				<a id="showEditorLink" class="button greenShade" >Edit Site Content</a>
				
<!-- 				<div id="siteFormSaveContainer">
					<button type="submit" class="siteFormSave">Save</button>
				</div> -->
				
				<a id="siteSaveButton" class="button redShade saveButtonOnDetailsPage" >Save</a>
  			
  				<div id="closeEditorContainer">
					<a class="mainLink"> Go back to Site Details </a>
				</div>
  			
  			</form:form>
  		
  		</div>
	    
	    <jsp:include page="footer.jsp"/>
  	
  	</div>
    
  </body>
</html>