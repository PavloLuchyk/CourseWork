<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div style="border: black solid 2px" class="item">
    <img src="data:image/jpg;base64,${param.image}" width="300px" height="300px"><br>
    <c:url value="ElementInfoController" var="url">
        <c:param name="elementId" value="${param.elementId}" />
    </c:url>
    <a href="${url}">${param.name}</a>
    <p>${param.description}</p>
    <p>${param.ingredients}</p>
    <p>${param.price}</p>
    <button class="minus">-</button>
    <input name="quantity" type="number" value="1" readonly="readonly" class="input" form="order ${param.elementId}">
    <button class="plus">+</button>
    <form method="post" action="CartController" id="order ${param.elementId}">
        <input name="menuElementId" type="hidden" value="${param.elementId}">
        <input type="submit" value="Add to cart">
    </form>
</div>
