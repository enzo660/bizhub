<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<title>Bizhub360</title>
<jsp:include page="htmlHead.jsp"/>
</head>
<body>
	<jsp:include page="navBar.jsp"/>
	<p>Sites</p>
	<c:choose>
		<c:when test="${fn:length(siteList) == 0}">
			<p>No sites</p>
		</c:when>
		<c:otherwise>
			<table>
				<thead>
					<tr>
						<th class="number">Id</th>
						<th>Name</th>
						<th>Description</th>
						<th><em>Actions</em></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${siteList}" var="site">
						<tr>
							<td class="number">${site.id}</td>
							<td>${site.name}</td>
							<td>${f:trimToLength(site.description, 40)}</td>
							<td class="small"><a class="button"
								href="<c:url value='/site?id=${site.id}'/>">View</a>
								<a class="button"
								href="<c:url value='/site_edit?id=${site.id}'/>">Edit</a>
								<a class="button"
								href="<c:url value='/site_delete?id=${site.id}'/>"
								onclick="return confirm('Are you sure you wish to delete this site?');">Delete</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
</body>
</html>