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
        <td>Address</td>
        <td>Creation time</td>
        <td>Admin</td>
    </tr>
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.userId} </td>
            <td>${user.username}</td>
            <td>${user.email}</td>
            <td>${user.phoneNumber}</td>
            <td>${user.address}</td>
            <td>${user.creationTime}</td>
            <td>${user.admin}</td>
            <td>
                <form action="GrantAccessController" method="post">
                    <input name="userId" value="${user.userId}" type="hidden">
                    <input name="admin" value="${user.admin}" type="hidden">
                    <input type="submit" value="${user.admin ? "Set admin" : "Remove admin"}">
                </form>
            </td>
        </tr>
</c:forEach>
</table>
<%@include file="footer.jsp"%>
</body>
</html>
