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
    <title>Sign Up | By Code Info</title>
    <link href="../assets/signup.css" rel="stylesheet">
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap"
            rel="stylesheet"
    />
</head>
<body>
<div class="signup-box">
    <h1>Sign Up</h1>
    <h4>WELCOME</h4>
    <form action="save-user">
        <label>Username</label>
        <input name="username" type="text" placeholder="" />
        <label>Password</label>
        <input name="password" type="password" placeholder="" />
        <label>Name</label>
        <input name="name" type="text" placeholder="" />
        <label  for="gender"> Select you gender</label>
        <select name="gender">
            <option value="none" selected>Gender</option>
            <option value="male">Male</option>
            <option value="female">Female</option>
            <option value="other">other</option>
        </select>
        <label for="role"> Select you role</label>
        <select name="role">
            <option value="Professor">Professor</option>
            <option value="University student">University student</option>

        </select>

          <button> Submit</button>
    </form>

    <p>
        By clicking the Sign Up button,you agree to our <br />
        <a href="#">Terms and Condition</a> and <a href="#">Policy Privacy</a>
    </p>
</div>
<p class="para-2">
    Already have an account? <a href="login.html">Login here</a>
</p>
</body>
</html>