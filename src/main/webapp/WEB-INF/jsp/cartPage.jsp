<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Cart</title>
</head>
<body>
<%@include file="header.jsp"%>
<div style="color: red;">${errorMessage}</div>
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
            <td>
                <form method="post" action="RemoveFromCartController">
                    <input type="hidden" name="elementId" value="${k.key.menuElementId}">
                    <input type="submit" value="Remove from cart">
                </form>
            </td>
        </tr>

</c:forEach>
</table>
<h1> Total price: <b id="totalPrice"></b> </h1>
<c:if test="${userId != null}">
    <form method="post" action="OrderController" id="order">
        <input type="submit" value="Order">
    </form>
</c:if>
<c:if test="${userId == null}">
    <form method="post" action="OrderController" id="order">
        Phone number: <input type="tel" name="phoneNumber"> <br>
        Address: <input type="text" name="address"><br>
        <input type="submit" value="Order">
    </form>
</c:if>

<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/script.js" />"></script>
</body>
</html>
