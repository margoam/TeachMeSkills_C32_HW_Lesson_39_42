<%--
  Created by IntelliJ IDEA.
  User: mozhe
  Date: 09.03.2025
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Create User</title>
</head>
<body>
<h2>Create New User</h2>
<form action="/users/create" method="post">
    <label for="firstname">First Name:</label>
    <input type="text" id="firstname" name="firstname" required /><br/>

    <label for="secondName">Second Name:</label>
    <input type="text" id="secondName" name="secondName" required /><br/>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" required /><br/>

    <label for="telephoneNumber">Phone:</label>
    <input type="text" id="telephoneNumber" name="telephoneNumber" required /><br/>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" required /><br/>

    <label for="sex">Sex:</label>
    <select id="sex" name="sex" required>
        <option value="Male">Male</option>
        <option value="Female">Female</option>
    </select><br/>

    <input type="submit" value="Create User" />
</form>
<br/>
<a href="${pageContext.request.contextPath}/users">Back to Users List</a>
</body>
</html>
