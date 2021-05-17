<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<header>
    <h1>Header</h1>
</header>
<nav>
    <a href="#">Menu</a> |
    <a href="#">Contacts</a>|
    <a href="#">About us</a> |
    <a href="#">Order</a> |
    <a href="#">Log in</a>
    <c:out value="aaaaaaaaaaaaaaaaa"></c:out>
</nav>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="IndexController">Hello Servlet</a>
<footer>
    <h1>Footer</h1>
</footer>
</body>
</html>