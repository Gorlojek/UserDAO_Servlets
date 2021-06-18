<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Baza użytkowników</title>
</head>

<body>
<a href="/user/add">Dodaj użytkownika</a>
<table>
    <tr>
        <th>ID</th>
        <th>username</th>
        <th>email</th>
        <th>actions</th>
    </tr>
    <c:forEach items="${users}" var="user" varStatus="status">
    <tr>
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.email}</td>
        <td><a href="<c:url value="/user/editUser?username=${user.userName}&email=${user.email}&id=${user.id}"/>">Edytuj</a>
            <a href="<c:url value="/user/showUser?username=${user.userName}&email=${user.email}&id=${user.id}"/>">Pokaż</a>
            <a href=<c:url value="/user/deleteUser?username=${user.userName}&email=${user.email}&id=${user.id}"/>>Usuń</a> </td>
        </c:forEach>
    </tr>
</table>
</body>
</html>