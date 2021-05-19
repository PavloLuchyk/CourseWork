<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<%@include file="header.jsp" %>

<c:forEach items="${allOrders}" var="order">
    <hr style="border: black 2px solid; background-color: black;">
    <table border="1">
        <tr>
            <td>Order id</td>
            <td>User Id</td>
            <td>Order time</td>
            <td>Processed</td>
        </tr>
        <tr>
            <td> ${order.key.orderId}</td>
            <td>${order.key.userId}</td>
            <td> ${order.key.time}</td>
            <td>${order.key.status}</td>
        </tr>
    </table>
    <h4>Order details</h4>
    <table border="1">
        <tr>
            <td>Menu element id</td>
            <td>Menu element name</td>
            <td>Price</td>
            <td>Quantity</td>
        </tr>
        <c:forEach items="${order.value}" var="details">
            <tr>
                <td>${details.key.menuElementId}</td>
                <td>${details.value.name}</td>
                <td>${details.value.price}</td>
                <td>${details.key.quantity}</td>
            </tr>
        </c:forEach>
    </table>
    <hr style="border: black 2px solid; background-color: black;">
    <br>
</c:forEach>
<%@include file="footer.jsp" %>
</body>
</html>
