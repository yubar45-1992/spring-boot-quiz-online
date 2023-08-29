<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Fallahnezhad
  Date: 10/25/2022
  Time: 11:29 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
<form action="editInfo">
    <table class="table table-striped table-bordered">
        <h3 class="text-center"> user information</h3>
        <br>

        <tr>
            <td>name</td>
            <td>username</td>
            <td>password</td>
            <td>gender</td>
            <td>role</td>
            <td>status</td>
        </tr>
      <c:forEach var="user" items="${user}">
          <tr>
              <th><input value="${user.name}" type="text" name="input"> <button class="btn btn-dark btn-block" name="name" value="<%=request.getParameter("input")%>">enter</button></th>
              <th><input type="text" value="${user.username}"> </th>
              <th><input type="text" value="${user.password}" ></th>
              <th><select name="gender">
                  <option value="${user.gender}" selected>${user.gender}</option>
                  <option value="male">Male</option>
                  <option value="female">Female</option>
                  <option value="other">other</option>
              </select></th>
              <th>${user.role}</th>
              <th>${user.status}<pre></pre><button class="btn btn-dark btn-block" value="${user.id}" name="button">  enter</button></th>
          </tr>

          </c:forEach>
          </table>
</body>
</html>
