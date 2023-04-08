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
<a href="vote/vote1?name=wzx">获取参数</a>
<form action="vote/vote2">
    号码:<input name="id" type="text"> <br/>
    名字:<input name="name" type="password"><br/>
    宠物号码:<input name="pet.id" type="text"> <br/>
    宠物名字:<input name="pet.name" type="password"><br/>
    <input type="submit" value="登录vote2">
</form>
<h3>serlvet</h3>
<form action="vote/vote4">
    号码:<input name="id" type="text"> <br/>
    名字:<input name="name" type="password"><br/>
    <input type="submit" value="登录">
</form>
<br/>
<h3>session域</h3>
<form action="session/s1">
    号码:<input name="id" type="text"> <br/>
    名字:<input name="name" type="password"><br/>
    宠物号码:<input name="pet.id" type="text"> <br/>
    宠物名字:<input name="pet.name" type="password"><br/>
    <input type="submit" value="登录vote2">
</form>
</body>
</html>