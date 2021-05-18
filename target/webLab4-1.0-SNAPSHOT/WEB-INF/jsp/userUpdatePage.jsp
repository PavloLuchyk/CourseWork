<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div id="updateMessage"></div>
<form method="post" action="UserUpdateController">
    <input name="username" type="text" value="${user.username}">
    <input name="username" type="text" value="${user.email}">
    <input name="username" type="text" value="${user.phoneNumber}">
</form>
<%@include file="footer.jsp"%>
</body>
</html>
