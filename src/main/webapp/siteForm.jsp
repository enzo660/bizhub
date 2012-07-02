<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizhub360 - Site Form</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
    <jsp:include page="navBar.jsp"/>
    <h1>Site Form</h1>
    <form method="post" action="<c:url value='/site_save'/>">
      <table>
        <c:if test="${not empty error}">
          <tr>
            <th>Error:</th>
            <td class="error">${error}</td>
          </tr>
        </c:if>
        <tr>
          <th>Name *:</th>
          <td>
            <input type="text" name="name" value="${site.name}"/>
          </td>
        </tr>
        <tr>
          <th>Description:</th>
          <td>
            <textarea name="description" rows="5" cols="60">${site.description}</textarea>
          </td>
        </tr>
        <tr>
          <th>&nbsp;</th>
          <td>
            <input type="hidden" name="id" value="${site.id}"/>
            <input type="submit" value="Save"/>
          </td>
        </tr>
      </table>
    </form>
  </body>
</html>