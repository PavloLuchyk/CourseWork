<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="header.jsp"%>
<table border="1">
    <tr>
        <td>User id</td>
        <td>Username</td>
        <td>Email</td>
        <td>Phone number</td>
        <td>Creation time</td>
        <td>Admin</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId} </td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.creationTime}</td>
            <td>${user.admin}</td>
        </tr>
</c:forEach>
</table>
<%@include file="footer.jsp"%>
</body>
</html>
