<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2023/3/29
  Time: 21:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<h3>登录页面</h3>
<form action="login">
    u:<input name="username" type="text"> <br/>
    p:<input name="password" type="password"><br/>
    <input type="submit" value="登录">
</form>
<h1>
    search演示
</h1>
<a href="user/find?book_id=100">查询书籍</a>
<a href="user/reg/jack/001">参数</a>
</body>
</html>