<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello</h1>
<c:forEach items="user" var="i">
    <h1>${i.name}</h1>
    <table border="1">
        <c:forEach items="orders" var="j">
            <tr>
                <td>${j.orderId}</td>
                <td>${j.time}</td>
                <c:forEach var="k" items="orderDetails">
                    <table border="1">
                        <tr>
                            <td>${k.menuelementId}</td>
                            <td>${k.quantity}</td>
                        </tr>
                    </table>
                </c:forEach>

            </tr>
        </c:forEach>
    </table>
</c:forEach>

</body>
</html>
