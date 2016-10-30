<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Авторизация</title>
</head>
<body>
<form action="<c:url value="/sign-in"/>" method="post">
<label>Имя:</label>
<input type="text" size="30" name="name"><br>
<label>Пароль:</label>
<input type="password" size="30" name="password"><br>
    <button type="submit">Войти</button>
</form>
<a href=<c:url value="/sign-up"/>>Регистрация</a>
<c:if test="${not empty message}">
    <h4 style="color: red">${message}</h4>
</c:if>

</body>
</html>
