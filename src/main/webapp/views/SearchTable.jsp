

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <title>Title</title>
</head>
<body>

<table class="table table-striped table-bordered">
  <h3 class="text-center"> result search</h3>
  <br>

  <tr>
    <td>name</td>
    <td>username</td>
    <td>password</td>
    <td>gender</td>
    <td>role</td>
    <td>status</td>
  </tr>

  <c:forEach var="user" items="${userList}">
    <tr>
      <th>${user.name}</th>
      <th>${user.username}</th>
      <th>${user.password}</th>
      <th>${user.gender}</th>
      <th>${user.role}</th>
      <th>${user.status}</th>
    </tr>
  </c:forEach>
</table>
</body>
</html>
