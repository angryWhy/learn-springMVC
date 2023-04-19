
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>json提交</title>
    <!-- 引入jquery -->
    <script type="text/javascript" src="jquery-3.6.0.min.js"></script>

    <!-- 编写jquery代码和ajax请求 -->
    <script type="text/javascript">
        $(function () {
            //给id="getJson"绑定点击事件
            $("#getJson").click(function () {
                //console.log("ok ....")

                var url = this.href;
                var args = {"time": new Date};//这是老师要发送数据,为了防止页面缓存
                $.post(
                    url,
                    args,
                    function (data) {//data 就是返回的数据,是json格式=>如果是多个json数据，可以遍历
                        console.log("dataa= ", data);
                        console.log("dog.name=", data.name)
                        console.log("dog.addresss=", data.address)
                    },
                    "json"
                );
                return false;//这里我们返回false,就不使用href默认机制
            })

            //绑定按钮点击事件, 提交json数据
            //springmvc 可以在在台將json轉成對象
            $("button[name='butt1']").click(function () {
                //目标:将userName 和 age 封装成json字符串，发送给目标方法
                var url = "/spring/save2";
                var userName = $("#userName").val();
                var password = $("#password").val();
                //将json对象转成json字符串
                var args = JSON.stringify({"userName": userName, "password": password});
                $.ajax({
                    url: url,
                    data: args,
                    type: "POST",
                    success: function (data) {
                        console.log("返回的data= ", data);
                    },
                    //下面这个contentType参数,是指定发送数据的编码和格式
                    contentType: "application/json;charset=utf-8"
                })
            })
        })
    </script>

</head>
<body>
<h1>请求一个json数据</h1>
<%--老师处理
1.当用户点击超链接时，我们发出一个ajax请求
2. 接收到请求后，我们查看这个数据
3. 使用老韩前面讲过的jquery发出ajax请求知识
--%>
<a href="<%=request.getContextPath()%>/json/dog" id="getJson">点击获取json数据</a>

<h1>发出一个json数据</h1>
u:<input id="userName" type="text"><br/>
a:<input id="password" type="text"><br/>
<button name="butt1">添加用户</button>

<h1>下载文件的测试 </h1>
<a href="<%=request.getContextPath()%>/downFile">点击下载文件</a>

<h1>上传文件的测试 </h1>
<%--multipart/form-data表示表单数据多个组成，二进制文件--%>
<form action="?" enctype="multipart/form-data">
    文件上传<input type="submit" value="上传文件"/>
</form>
</body>
</html>