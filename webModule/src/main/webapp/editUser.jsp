<%--
  Created by IntelliJ IDEA.
  User: programmer
  Date: 13.06.18
  Time: 14:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Edit User</title>
</head>
<body>
<div align="center">
    <c:if test="${user != null}">
    <form action="update" method="post">
        </c:if>
        <c:if test="${user == null}">
        <form action="insert" method="post">
            </c:if>
            <table border="1" cellpadding="5">
                <caption>
                    <h2>
                        <c:if test="${user != null}">
                            Edit user
                        </c:if>
                        <c:if test="${user == null}">
                            Add New user
                        </c:if>
                    </h2>
                </caption>
                <tr>
                    <th>Login: </th>
                    <td>
                        <input type="text" name="login" size="45"
                               value="<c:out value='${user.login}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>Password: </th>
                    <td>
                        <input type="password"  name="password" size="45"
                               value="<c:out value='${user.password}' />"
                        />
                    </td>
                </tr>
                <tr>
                    <th>BirthDate: </th>
                    <td>
                        <div hidden="true"> <fmt:formatDate value="${user.birthDate}" pattern="yyyy-MM-dd" var="birthDate1"/></div>

                        <input type="date" name="birthDate" size="5"
                               value="<c:out value='${birthDate1}' />"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="Save" />
                    </td>
                </tr>
            </table>
        </form>
</div>
</body>
</html>
