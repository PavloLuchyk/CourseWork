<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<c:forEach items="${undoneOrders}" var="order">
    <h3>${order.key.orderId} ${order.key.time} ${order.key.status}</h3>
    <form action="OrderProcessingController" method="post">
        <input type="hidden" name="orderId" value="${order.key.orderId}">
        <input type="submit" value="Process order">
    </form>
    <table border="1">
        <tr>
            <td>Menu element id</td>
            <td>Menu element name</td>
            <td>Price</td>
            <td>Quantity </td>
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
</c:forEach>
<%@include file="footer.jsp"%>
</body>
</html>
