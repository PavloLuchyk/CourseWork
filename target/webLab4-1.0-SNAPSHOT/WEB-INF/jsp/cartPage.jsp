<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div>${errorMessage}</div>
<table border="1" class="table" id="cart">
    <tr>
        <td>Element id</td>
        <td>Element name</td>
        <td> Price</td>
        <td>Quantity</td>
        <td>Sub total</td>
    </tr>
<c:forEach var="k" items="${orderMenuElements}">
    <c:url value="ElementInfoController" var="url">
        <c:param name="elementId" value="${k.value.elementId}" />
    </c:url>
        <tr class="cartItem">
            <td>${k.key.menuElementId}</td>
            <td><a href="${url}">${k.value.name}</a></td>
            <td >${k.value.price}</td>
            <td>
                <button class="minus">-</button>

                    <input type="number" value="${k.key.quantity}" name="${k.key.menuElementId}_quantity"
                           class="input" readonly="readonly" data-price="${k.value.price}" form="order">

                <button class="plus">+</button>
            </td>
            <td class="subTotal">${k.key.quantity*k.value.price}</td>
        </tr>

</c:forEach>
</table>
<h1> Total price: <b id="totalPrice"></b> </h1>
<form method="post" action="OrderController" id="order">
    <input type="submit" value="Order">
</form>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/script.js" />"></script>
</body>
</html>
