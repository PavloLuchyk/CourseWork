<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="header.jsp"%>
<img src="data:image/jpg;base64,${menuElement.base64Image}" width="300px" height="300px"> <br>
<h2>Menu element: ${menuElement.elementId} </h2>
<form method="post" action="UpdateElementController">
    <input type="hidden" name="elementId" value="${menuElement.elementId}">
    <input type="text" name="name" value="${menuElement.name}" required><br>
    <input type="text" name="description" value="${menuElement.description}" required><br>
    <input type="text" name="ingredients" value="${menuElement.ingredients}" required><br>
    <input type="text" name="price" value="${menuElement.price}"><br>
    <input type="submit" value="Update details">
</form>
<form method="post" action="UpdateImageController" enctype="multipart/form-data">
    <input type="hidden" name="elementId" value="${menuElement.elementId}">
    <input type="file" name="file" accept="image/*"><br>
    <input type="submit" value="Update image">
</form>
<%@include file="footer.jsp"%>
</body>
</html>
