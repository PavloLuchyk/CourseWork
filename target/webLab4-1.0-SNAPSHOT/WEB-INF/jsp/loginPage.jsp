<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div style="color: green;">${message}</div>
<div style="color: red;">${errorMessage}</div>
<form method="post" action="LogInController" id="loginForm">
    <input type="text" name="username"><br>
    <input type="password" name="password"><br>
    <input type="submit" value="Log in"><br>
</form>
<a href="RegistrationController">Register</a>
<%@include file="footer.jsp"%>
</body>
</html>
