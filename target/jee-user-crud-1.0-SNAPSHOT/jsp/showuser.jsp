<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Szczegóły</title>
</head>
<body>
<a href="/add">Dodaj użytkownika</a><br>
Szczegóły użytkownika:<br>
ID: <c:out value="${id}"/><br>
Nazwa użytkownika: <c:out value="${username}"/><br>
email: <c:out value="${email}"/><br>
<button onclick="location.href='/user/list'" type="button">Wróć</button>
</body>
</html>