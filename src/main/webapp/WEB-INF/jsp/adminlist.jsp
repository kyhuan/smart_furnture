<%--
  Created by IntelliJ IDEA.
  User: Jimmy.Z
  Date: 2020/3/31
  Time: 11:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table >
    <tr>
        <th>id</th>
        <th>username</th>
        <th>status</th>
        <th>uuid</th>
    </tr>
    <c:forEach items="${adminlist}" var="adminlist">
        <tr>
            <td>${adminlist.id}</td>
            <td>${adminlist.username}</td>
            <td>${adminlist.status}</td>
            <td>${adminlist.uuid}</td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
