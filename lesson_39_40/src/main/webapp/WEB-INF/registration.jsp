<%--
  Created by IntelliJ IDEA.
  User: mozhe
  Date: 09.03.2025
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h2>Registration</h2>
<form action="/security/registration" method="post">
    <label for="firstname">First name:</label>
    <input type="text" id="firstname" name="firstname" >

    <label for="secondName">Second name:</label>
    <input type="text" id="secondName" name="secondName" >

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" >

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" >

    <label for="sex">Sex:</label>
    <input type="text" id="sex" name="sex" >

    <label for="telephoneNumber">Telephone number:</label>
    <input type="text" id="telephoneNumber" name="telephoneNumber" >

    <label for="login">Login:</label>
    <input type="text" id="login" name="login" >

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" >

    <input type="submit" value="Registration">
</form>
<a href="/security/login">You have already an account?</a>
</body>
</html>


