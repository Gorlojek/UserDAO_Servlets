<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dodaj</title>
</head>
<body>
<form method="post" action="/add">
    Dane użytkownika:<br>
    Nazwa użytkownika: <input type="text" name="username"><br>
    email: <input type="text" name="email"><br>
    Hasło: <input type="password" name="password" ><br>
    <button onclick="location.href='/user/list'" type="button">Wróć</button>
    <button type="submit">Dodaj</button>

</form>
</body>
</html>