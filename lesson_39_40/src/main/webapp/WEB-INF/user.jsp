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
    <title>User information</title>
</head>
<body>
<h1>User information:</h1>
<p>Id: ${user.id}</p>
<p>First name: ${user.firstname}</p>
<p>Second name: ${user.secondName}</p>
<p>Age: ${user.age}</p>
<p>Telephone number: ${user.telephoneNumber}</p>
<p>Sex: ${user.sex}</p>
<p>Created: ${user.created}</p>
<p>Updated: ${user.updated}</p>
<p>Deleted: ${user.deleted}</p>

<form action="/user" method="get">
    <input type="hidden" id="id" name="userId" value="${user.id}" />
    <button type="submit" class="btn">Update user</button>
</form>
<form action="/user/delete" method="post">
    <input type="hidden" id="userId" name="userId" value="${user.id}" />
    <button type="submit" class="btn">Delete user</button>
</form>
</body>
</html>