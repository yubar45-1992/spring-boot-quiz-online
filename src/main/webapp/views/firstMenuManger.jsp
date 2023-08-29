<%--
  Created by IntelliJ IDEA.
  User: Fallahnezhad
  Date: 10/25/2022
  Time: 5:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">

</head>
<body>





<table class="table table-striped table-bordered">
    <h3 class="text-center"> please chose </h3>
    <br>

    <tr>
        <td><a href="http://localhost:8080/manager/showList">show all user </a></td>
        <td><a href="http://localhost:8080/manager/load-search">advance search</a></td>
        <td><a href="http://localhost:8080/manager/show-course">show course</a></td>
        <td><a href="http://localhost:8080/manager/addCourse">add new course</a></td>
    </tr>
    <tr>

        <td><a href="http://localhost:8080/manager/addProfessor">add new Professor</a></td>
        <td><a href="http://localhost:8080/manager/university-student">add new University student</a></td>
        <td><a href="http://localhost:8080/manager/delete-student"> delete student for course</a></td>
    </tr>
    </table>
</body>
</html>
