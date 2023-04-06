<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2023/4/2
  Time: 15:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买成功</title>
    主人名字：${requestScope.master.name}
    主人id：${requestScope.master.id}
    宠物名字：${requestScope.master.pet.name}
    宠物id：${requestScope.master.pet.id}
    地区：${requestScope.address}
</head>
<body>
购买成功
</body>
</html>
