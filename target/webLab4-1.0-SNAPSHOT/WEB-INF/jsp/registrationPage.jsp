<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<%@include file="header.jsp"%>
<div style="color: red;">${message}</div>
<form action="RegistrationController" onsubmit="return validate" method="post" id="registration">
  Username:         <input type="text" name="username" size="32" required> <br>
  Email:            <input type="email" name="email" id="email" onkeyup="emailValidation()" required>
  <span id="emailMessage"></span> <br>
  Password:         <input type="password" id="password" name="password" onkeyup="check()" required>
  <span id="passwordCheck"></span> <br>
  Confirm password: <input type="password" id="confirm_password" onkeyup="check()" required>
  <span id="message"></span> <br>
  Phone number:     <input type="text"  name="phoneNumber" required> <br>
  <input type="submit" id="submitButton" value="Register">
</form>
<%@include file="footer.jsp"%>
<script type="text/javascript" src="<c:url value="/static/js/registrationScript.js" />"></script>
</body>
</html>
