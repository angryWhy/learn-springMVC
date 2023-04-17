<%--
  Created by IntelliJ IDEA.
  User: HUAWEI
  Date: 2023/4/16
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>springMVC数据格式</title>
</head>
<body>
<form:form action="save" method="post" modelAttribute="monster">
    编号:<form:input path="id"/> <form:errors path="id"/> <br/>
    年龄:<form:input path="age"/> <form:errors path="age"/> <br/>
    名字:<form:input path="name"/><form:errors path="name"/> <br/>
    生日:<form:input path="birthday"/><form:errors path="birthday"/> <br/>
    薪水:<form:input path="salary"/><form:errors path="salary"/> <br/>
    <input type="submit" value="添加妖怪"/>
</form:form>
</body>
</html>
