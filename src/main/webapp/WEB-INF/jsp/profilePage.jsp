<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Profile</title>
</head>
<body>
<%@include file="header.jsp"%>
<h3>Hello, ${username}!</h3>
<a href="OrderHistoryController">View order history</a> <br>
<a href="LogOutController">Log out</a>
<c:if test="${admin == true}">
    <h1>Admin control panel</h1>
    <a href="OrdersViewController">View all orders</a> <br>
    <a href="OrderProcessingController">Process orders</a> <br>
    <a href="MenuUpdateController">Update menu</a> <br>
    <a href="UserViewController">View users</a> <br>
</c:if>
<%@include file="footer.jsp"%>
</body>
</html>
