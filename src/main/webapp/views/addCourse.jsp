<%--
  Created by IntelliJ IDEA.
  User: Mohsen
  Date: 9/30/2022
  Time: 15:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link href="../assets/login.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css">

    <!-- Design by foolishdeveloper.com -->
    <title>new course</title>


</head>
<body>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card border-0 shadow rounded-3 my-5">
                <div class="card-body p-4 p-sm-5">
                    <h5 class="card-title text-center mb-5 fw-light fs-5">enter your new course</h5>
                    <form action="save-course">
                        <div class="form-floating mb-3">
                            <input name="titleOfCourse" type="text" class="form-control" id="floatingInput" placeholder="titleOfCourse">
                            <label for="floatingInput">titleOfCourse</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="uniqueIdOfTheCourse" type="text" class="form-control" id="floatingPassword" placeholder="uniqueIdOfTheCourse">
                            <label for="floatingPassword">uniqueIdOfTheCourse</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="courseStartDate" type="date" class="form-control" id="courseStartDate" placeholder="courseStartDate">
                            <label for="courseStartDate">courseStartDate</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input name="courseEndDate" type="date" class="form-control" id="courseEndDate" placeholder="courseEndDate">
                            <label for="courseEndDate">courseEndDate</label>
                        </div>


                        <div class="d-grid">
                            <button class="btn btn-primary btn-login text-uppercase fw-bold" type="submit">enter
                                </button>
                        </div>




                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- Pills content -->
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" </script>
</body>
</html>



