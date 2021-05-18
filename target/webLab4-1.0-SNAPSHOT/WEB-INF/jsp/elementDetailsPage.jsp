<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<div style="border: black solid 2px" class="item">
    <img src="data:image/jpg;base64,${menuElement.base64Image}" width="300px" height="300px">
    <div>
        <h1>${menuElement.name}</h1>
        <p>${menuElement.description}</p>
        <p>${menuElement.ingredients}</p>
        <p>${menuElement.price}</p>
        <button class="minus">-</button>
        <input name="quantity" type="number" value="1" readonly="readonly" class="input" form="order ${menuElement.elementId}">
        <button class="plus">+</button>
        <form method="post" action="ElementInfoController" id="order ${menuElement.elementId}">
            <input name="menuElementId" type="hidden" value="${menuElement.elementId}">
            <input type="submit" value="Add to cart">
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
