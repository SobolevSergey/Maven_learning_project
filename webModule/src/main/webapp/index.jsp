<%--
  Created by IntelliJ IDEA.
  User: programmer
  Date: 13.06.18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Users</title>

    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>

    <script>
        $(function () {

            $('#getForecast').click(function () {
                var data = {city: "Васюки", date: "20120318"};
                $.get("/rest/weather", data, success, "json");
            });

            function success(resultDate) {
                alert("test");
                var result = resultDate.city + " прогноз на " + resultDate.date;
                result += ": " + resultDate.caption + ". Максимальная температура: " + resultDate.maxTemp + "C";
                alert(result);
            }

        });
    </script>
</head>
<body>
<div style="text-align: center;">
    <h1>Users Management</h1>
    <h2>
        <a href="/new">Add New User</a>
        &nbsp;&nbsp;&nbsp;
        <a href="/list">List All Users</a>
        <button id="getForecast">Получить прогноз погоды</button>
    </h2>
</div>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>Login</th>
            <th>Password</th>
            <th>BirthDate</th>
            <th>Active</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.password}"/></td>
                <td><c:out value="${user.birthDate}"/></td>
                <td><c:choose>
                    <c:when test="${user.active == true}">
                        Yes
                    </c:when>
                    <c:otherwise>
                        No
                    </c:otherwise>
                </c:choose>
                </td>
                <td>
                    <a href="/webModule/edit?login=<c:out value='${user.login}' />">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/webModule/delete?login=<c:out value='${user.login}' />">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
