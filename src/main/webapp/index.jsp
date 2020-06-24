<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
</body>
<h1>注册</h1>
<form action="/baseUser/register" method="post">
    用户姓名<input type="text" name="username"></br>
    用户密码<input type="text" name="password"></br>
    <input type="text" name="aa">
    <button type="submit">注册</button>
</form><br>
<h1>登录</h1>
<form action="/baseUser/login" method="post">
    用户姓名<input type="text" name="uuid"></br>
    用户密码<input type="text" name="password"></br>
    <button type="submit">登录</button>
</form>
<a href="/user/getDevId">查询设备列表</a>

<form action="/user/addDev" method="post">
    uuid<input type="text" name="uuid"><br>
    devid<input type="text" name="devid"><br>
    username<input type="text" name="username"><br>
    address<input type="text" name="address"><br>
    devmsg<input type="text" name="devmsg"><br>
    <button type="submit">提交</button>
</form>
<form action="/user/applyAdmin" method="post">
    uuid<input type="text" name="uuid"><br>
    adminMsg<input type="text" name="adminMsg"><br>
    <button type="submit">提交</button>
</form>
<a href="/superUser/getAdminApplyList">查询管理员申请列表</a><br>
<a href="/superUser/getDevApplyList">查询设备申请列表</a><br>
<form action="/device/deviceregister" method="post">
    address<input type="text" name="address"><br>
    password<input type="text" name="password"><br>
    <button type="submit">提交</button>
</form>
<form action="/device/devicelogin" method="post">
    devid<input type="text" name="devid"><br>
    password<input type="text" name="password"><br>
    <button type="submit">提交</button>
</form>
String uuid,String devid,String datatype,String max,String min
<form action="/admin/updateDevData">
    uuid<input type="text" name="uuid"><br>
    devid<input type="text" name="devid"><br>
    datatype<input type="text" name="datatype"><br>
    max<input type="text" name="max"><br>
    min<input type="text" name="min"><br>
    <button type="submit">提交</button>

</form>
<a href="/test/addtime">添加时间</a><br>
<form action="/user/selDevBind" method="post">
    uuid<input type="text" name="uuid"><br>
    <button type="submit">提交</button>
</form><<br>
String superuuid,String uuid,String devid,String flag
<form action="/superUser/handleDevApply" method="post">
    SU<input type="text" name="superuuid"><br>
    uuid<input type="text" name="uuid"><br>
    devid<input type="text" name="devid"><br>
    flag<input type="text" name="flag"><br>
        <button type="submit">提交</button>
</form>
<a href="test.jsp">跳转</a><br>
<a href="/tt">点击</a>
</html>
