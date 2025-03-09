<%--
  Created by IntelliJ IDEA.
  User: mozhe
  Date: 09.03.2025
  Time: 20:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Details</title>
</head>
<body>
<h2>User Details</h2>
<form action="/users/${user.id}/update" method="post">
    <input type="hidden" name="_method" value="PUT" />
    <label for="firstname">First Name:</label>
    <input type="text" id="firstname" name="firstname" value="${user.firstname}" required /><br/>

    <label for="secondName">Second Name:</label>
    <input type="text" id="secondName" name="secondName" value="${user.secondName}" required /><br/>

    <label for="age">Age:</label>
    <input type="number" id="age" name="age" value="${user.age}" required /><br/>

    <label for="telephoneNumber">Phone:</label>
    <input type="text" id="telephoneNumber" name="telephoneNumber" value="${user.telephoneNumber}" required /><br/>

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${user.email}" required /><br/>

    <label for="sex">Sex:</label>
    <select id="sex" name="sex" required>
        <option value="Male" ${user.sex == 'Male' ? 'selected' : ''}>Male</option>
        <option value="Female" ${user.sex == 'Female' ? 'selected' : ''}>Female</option>
    </select><br/>

    <input type="submit" value="Update" />
</form>
<br/>
<form action="/users/${user.id}/delete" method="post" style="display:inline;">
    <input type="submit" value="Delete User" />
</form>
<br/>
<a href="/users">Back to Users List</a>
</body>
</html>

