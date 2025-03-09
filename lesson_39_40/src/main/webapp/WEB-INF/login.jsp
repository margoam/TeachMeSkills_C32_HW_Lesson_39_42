<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Enter</title>
</head>
<body>
<h2>Login:</h2>
<form action="/security/login" method="post">
    <label>Login: <input type="text" name="username" required></label><br>
    <label>Password: <input type="password" name="password" required></label><br>
    <button type="submit">Submit</button>
</form>
<a href="/security/registration">Registration</a>
</body>
</html>
