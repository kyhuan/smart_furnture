<%--
  Created by IntelliJ IDEA.
  User: Jimmy.Z
  Date: 2020/3/28
  Time: 20:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/adddevice" method="post">
    设备地址<input type="text" name="address">
    <button type="submit">提交</button>
</form>
<a href="/admin/apply">申请</a><br>
<a href="/user/admin">查询申请列表</a><br>
<a href="/admin/isadmin">查询是否管理员</a><br>
<a href="/user/searchadmin">查询所有管理员</a><br>
<a href="/user/devshow">查询管理员要添加的设备</a>
<form action="/admin/adddev" method="post">
    设备编号<input type="text" name="uuid"><br>
    <button type="submit">提交</button>
</form>
<jsp:forward page="../../test.jsp"/>dianji
</body>
</html>
