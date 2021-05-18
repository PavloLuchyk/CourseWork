<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<a href="OrderHistoryController">View order history</a> <br>
<c:if test="${admin == true}">
    <h1>Admin control panel</h1>
    <a href="OrderProcessingController">Process orders</a> <br>
    <a href="MenuUpdateController">Update menu</a> <br>
    <a href="UserViewController">View users</a> <br>
</c:if>
<%@include file="footer.jsp"%>
</body>
</html>
