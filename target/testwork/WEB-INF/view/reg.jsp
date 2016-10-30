<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<h1>Заполните форму</h1>
<form action="<c:url value="/sign-up"/>" method="post">
<label>Имя:</label>
<input type="text" size="30" name="name"><br>
<label>Пароль:</label>
<input type="password" size="30" name="password"><br>
<label>Подтвердите пароль:</label>
<input type="password" size="30" name="confirmpass"><br>
    <button type="submit">Войти</button>
</form>
<c:if test="${not empty message}">
   <h4 style="color: red">${message}</h4>
</c:if>
</body>
</html>
