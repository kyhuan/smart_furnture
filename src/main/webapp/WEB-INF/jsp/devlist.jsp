<%--
  Created by IntelliJ IDEA.
  User: Jimmy.Z
  Date: 2020/3/31
  Time: 18:10
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
        <th>uid</th>
        <th>sid</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${devlist}" var="devlist">
        <tr>
            <td>${devlist.id}</td>
            <td>${devlist.uid}</td>
            <td>${devlist.sid}</td>
            <td><a href="/user/devshowdo/${devlist.uid}/${devlist.sid}">操作</a> </td>
        </tr>
    </c:forEach>

</table>
</body>
</html>
