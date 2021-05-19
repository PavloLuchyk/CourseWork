<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu update</title>
</head>
<style>
    .item{
        border: black solid 2px; width: 25%;
        display: inline-block; padding: 1%; margin: 2%;
    }

    .item:hover{ background-color: lightgray; }
</style>
<body>
<%@include file="header.jsp"%>
<div>
    <h1>Add new menu element</h1>
    <form method="post" action="UploadController" enctype="multipart/form-data">
        <label>Element's name</label>
        <input type="text" name="name"><br>
        <label>Element's description</label>
        <input type="text" name="description"><br>
        <label>Element's ingredients</label>
        <input type="text" name="ingredients"><br>
        <label>Element's price</label>
        <input type="text" name="price"><br>
        <label>Element's image</label>
        <input type="file" name="file" accept="image/*"><br>
        <input type="submit" value="Add"><br>
    </form>
</div>
<c:forEach var="i" items="${menu.menuElements}">
<div style="border: black solid 2px" class="item">
    <img src="data:image/jpg;base64,${i.base64Image}" width="300px" height="300px">
    <p>${i.elementId}</p>
    <p>${i.name}</p>
    <p>${i.description}</p>
    <p>${i.ingredients}</p>
    <p>${i.price}</p>
    <form action="UpdateElementController" method="get">
        <input name="menuElementId" value="${i.elementId}" type="hidden">
        <input type="submit" value="Edit">
    </form>
    <form action="DeleteElementController" method="post">
        <input name="elementId" value="${i.elementId}" type="hidden">
        <input type="submit" value="Delete">
    </form>
</div>
</c:forEach>
<%@include file="footer.jsp"%>
</body>
</html>
