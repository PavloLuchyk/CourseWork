<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>
    <h1>Header</h1>
</header>
<nav>
    <a href="IndexController">Menu</a> |
    <a href="#">Contacts</a>|
    <a href="#">About us</a> |
    <a href="OrderController">Order</a> |
    <c:if test="${userId == null}">
        <a href="LogInController">Log in</a>
    </c:if>
    <c:if test="${userId != null}">
        <a href="ProfileController">Profile</a>
    </c:if>
</nav>
