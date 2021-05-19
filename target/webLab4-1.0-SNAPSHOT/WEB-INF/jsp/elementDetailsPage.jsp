<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${menuElement.name}</title>
</head>
<style>
    .item{
        border: black solid 2px; width: 25%;
        display: inline-block; padding: 1%; margin: 2%;}

    .item:hover{ background-color: lightgray; }
</style>
<body>
<%@include file="header.jsp"%>
<div style="border: black solid 2px" class="item">
    <img src="data:image/jpg;base64,${menuElement.base64Image}" width="300px" height="300px">
    <div>
        <h1>${menuElement.name}</h1>
        <p>${menuElement.description}</p>
        <p>${menuElement.ingredients}</p>
        <p>${menuElement.price}</p>
        <input name="quantity" type="number" value="1" min="1" class="input" form="order ${menuElement.elementId}">
        <form method="post" action="ElementInfoController" id="order ${menuElement.elementId}">
            <input name="menuElementId" type="hidden" value="${menuElement.elementId}">
            <input type="submit" value="Add to cart">
        </form>
    </div>
</div>
<%@include file="footer.jsp"%>
</body>
</html>
