<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>${site.name} Site</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
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
        <th>&nbsp;</th>
        <td style="padding-top: 10px;">
          <a class="button" href="<c:url value='/site_edit?id=${site.id}'/>">Edit</a>
          <a class="button" href="<c:url value='/site_delete?id=${site.id}'/>"
	       onclick="return confirm('Are you sure you wish to delete this site?');">Delete</a>
        </td>
      </tr>
    </table>
  </body>
</html>