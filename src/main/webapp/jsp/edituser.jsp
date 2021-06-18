<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Edycja użytkownika</title>
</head>
<body>
<a href="/user/add">Dodaj użytkownika</a><br>
<form action="/edit" method="post">
    Dane użytkownika:<br>
    ID: <c:out value="${id}"/><br>
    Nazwa użytkownika: <input type="text" name="username" value="${username}"><br>
    email: <input type="text" name="email" value="${email}"><br>
    <button onclick="location.href='/user/list'" type="button">Wróć</button>
    <button type="submit">Edytuj</button>
</form>
</body>
</html>