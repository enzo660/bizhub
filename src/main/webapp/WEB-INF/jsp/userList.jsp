<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="/WEB-INF/functions.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
  <head>
    <title>Bizhub360 - Users</title>
    <jsp:include page="htmlHead.jsp"/>
  </head>
  <body>
    <jsp:include page="navBar.jsp"/>
    <c:choose>
      <c:when test="${fn:length(userList) == 0}">
        <p>No users</p>
      </c:when>
      <c:otherwise>
        <table>
          <thead>
            <tr>
              <th class="number">Id</th>
              <th>Name</th>
              <th>Business</th>
              <th>Title</th>
              <th>Email</th>
              <th>Since</th>
              <th><em>Actions</em></th>
            </tr>
          </thead>
          <tbody>
            <c:forEach items="${userList}" var="user">
	           <tr>
                <td class="number">${user.id}</td>
                <td>${user.name}</td>
                <td>${f:trimToLength(user.business, 30)}</td>
                <td>${f:trimToLength(user.title, 30)}</td>
                <td>
                  <a href="mailto:${user.email}">
                    ${f:trimToLength(user.email, 30)}
                  </a>
                </td>
                <td>
                  <fmt:formatDate value="${user.created}" pattern="MMM yyyy" />
                </td>
                <td class="small">
                  <a class="button" href="<c:url value='/user?id=${user.id}'/>">View</a>
                  <a class="button" href="<c:url value='/user_form?id=${user.id}'/>">Edit</a>
                  <a class="button" href="<c:url value='/user_delete?id=${user.id}'/>"
                    onclick="return confirm('Are you sure you wish to delete this user?');">Delete</a>
                </td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </c:otherwise>
    </c:choose>
  </body>
</html>