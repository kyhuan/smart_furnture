<%--
  Created by IntelliJ IDEA.
  User: Jimmy.Z
  Date: 2020/3/28
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/user/login" method="post">
    <%--用户姓名<input type="text" name="username" ></br>--%>
    用户id  <input type="text" name="uuid"></br>
    用户密码<input type="text" name="password"></br>

    <button type="submit">登录</button>
</form>
</body>
</html>
