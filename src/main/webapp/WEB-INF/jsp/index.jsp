<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="header.jsp"%>
<br/>
<div id="menu-list">
<c:forEach var="i" items="${menu.menuElements}">
    <div style="border: black solid 2px" class="item">
        <img src="data:image/jpg;base64,${i.base64Image}" width="300px" height="300px"><br>
        <c:url value="ElementInfoController" var="url">
            <c:param name="elementId" value="${i.elementId}" />
        </c:url>
        <a href="${url}">${i.name}</a>
        <p>${i.description}</p>
        <p>${i.ingredients}</p>
        <p>${i.price}</p>
        <input name="quantity" type="number" value="1" min="1" class="input" form="order ${i.elementId}">
        <form method="post" action="CartController" id="order ${i.elementId}">
            <input name="menuElementId" type="hidden" value="${i.elementId}">
            <input type="submit" value="Add to cart">
        </form>
    </div>
</c:forEach>
</div>
<%@include file="footer.jsp"%>
</body>
</html>