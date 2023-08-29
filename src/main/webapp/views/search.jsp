<%--
  Created by IntelliJ IDEA.
  User: Fallahnezhad
  Date: 10/25/2022
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="../assets/login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">
    <title>Title</title>
</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">welcome to search page</h5>
                    <form action="search">
                        <div class="form-floating mb-3">
                            <input type="text" name="name" class="form-control" id="floatingInput" placeholder="name">
                            <label for="floatingInput">name</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="gender" type="text" class="form-control" id="floatingPassword" placeholder="gender">
                            <label for="floatingInput">gender</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="status" type="text" class="form-control"  placeholder="status">
                            <label for="floatingInput">status</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="username" type="text" class="form-control"  placeholder="username">
                            <label for="floatingInput">username</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="password" type="text" class="form-control" placeholder="password">
                            <label for="floatingInput">password</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="role" type="text" class="form-control" placeholder="role">
                            <label for="floatingInput">role</label>
                        </div>


                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">search
                                </button>
                        </div>
                        </form>
</body>
</html>
