<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Vlad.M
  Date: 29.10.2016
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
</head>
<body>
<form action="<c:url value="/welcome"/>" method="post">
    <strong>${welcome}</strong> | Time: ${now}
    <button type="submit">Выйти</button>
</form>
</body>
</html>
