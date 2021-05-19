<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<%@include file="WEB-INF/jsp/header.jsp"%>
<br/>
<div id="menu-list">
<c:forEach var="i" items="${menu.menuElements}">
    <jsp:include page="WEB-INF/jsp/foodInfo.jsp">
        <jsp:param name="elementId" value="${i.elementId}"/>
        <jsp:param name="image" value="${i.base64Image}"/>
        <jsp:param name="name" value="${i.name}"/>
        <jsp:param name="description" value="${i.description}"/>
        <jsp:param name="ingredients" value="${i.ingredients}"/>
        <jsp:param name="price" value="${i.price}"/>
    </jsp:include>
</c:forEach>
</div>
<%@include file="WEB-INF/jsp/footer.jsp"%>
<script src="static/js/cartScript.js"></script>
</body>
</html>