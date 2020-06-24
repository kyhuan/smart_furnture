<%--
  Created by IntelliJ IDEA.
  User: Jimmy.Z
  Date: 2020/3/30
  Time: 13:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%--该页面是超级管理员来处理管理员请求的--%>
    <title>Title</title>
</head>
<body>
    <table >
        <tr>
            <th>id</th>
            <th>username</th>
            <th>uuid</th>
            <th>操作</th>
            <th>删除</th>
        </tr>
        <c:forEach items="${admin}" var="admin">
            <tr>
                <td>${admin.id}</td>
                <td>${admin.username}</td>
                <td>${admin.uuid}</td>
                <td><a href="/user/admindo/${admin.uuid}">操作</a> </td>
                <td>删除</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
