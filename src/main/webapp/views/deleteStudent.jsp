<%--
  Created by IntelliJ IDEA.
  User: Fallahnezhad
  Date: 10/25/2022
  Time: 7:48 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <title>Title</title>

  </script>
</head>
<body>

<table class="table table-striped table-bordered">
  <h3 class="text-center"> add new University student</h3>
  <br>
  <form action="remove-student" method="post">
  <tr>
    <td>titleOfCourse</td>
    <td>uniqueIdOfTheCourse</td>
    <td>courseStartDate</td>
    <td>courseEndDate</td>
    <td>professor</td>
    <td>University student</td>
  </tr>
  <c:forEach var="course" items="${course}">

    <tr>
      <th>${course.titleOfCourse}</th>
      <th>${course.uniqueIdOfTheCourse}</th>
      <th>${course.courseStartDate}</th>
      <th>${course.courseEndDate}</th>
      <th>${course.professor}</th>
      <th>${course.universityStudent}</th>

      <th> chose and write the name student for delete</th>

      <th>
          <input type="text" name="student">
          <button  id="test" class="btn btn-dark btn-block"  value="${course.id}" name="courseId" >delete</button>
      </th>
          </c:forEach>

    </tr>

</table>
</form>
<form action="first-menu">
  <button class="btn btn-dark btn-block">back to first menu</button>
</form>
</body>
</html>
