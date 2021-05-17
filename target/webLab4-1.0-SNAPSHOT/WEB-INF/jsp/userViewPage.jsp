<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
</head>
<body>
<%@include file="header.jsp"%>
<c:forEach items="${users}" var="user">
    <div>
        <p>${user.userId} ${user.username} ${user.email} ${user.phoneNumber} ${user.creationTime} ${user.admin}</p>
    </div>
</c:forEach>
<%@include file="footer.jsp"%>
</body>
</html>
