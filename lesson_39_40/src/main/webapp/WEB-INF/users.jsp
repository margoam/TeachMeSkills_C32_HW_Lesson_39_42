<%--
  Created by IntelliJ IDEA.
  User: mozhe
  Date: 09.03.2025
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Users List</title>
</head>
<body>
<h2>Users List</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>First Name</th>
        <th>Second Name</th>
        <th>Age</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.firstname}</td>
            <td>${user.secondName}</td>
            <td>${user.age}</td>
            <td>${user.telephoneNumber}</td>
            <td>${user.email}</td>
            <td>
                <a href="/users/${user.id}">View</a>
                <a href="/users/${user.id}/update">Edit</a>
                <form action="/users/${user.id}/delete" method="post" style="display:inline;">
                    <input type="submit" value="Delete" />
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br/>
<a href="${pageContext.request.contextPath}/users/create">Create New User</a>
</body>
</html>

