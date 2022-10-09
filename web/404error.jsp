<%--
  Created by IntelliJ IDEA.
  User: Amireux
  Date: 2022/9/25
  Time: 17:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <meta http-equiv="refresh" content="10;URl = http://localhost:8080/login.jsp">
    <script>
        var time = 10;
        window.onload = function () {
            window.setInterval("showTime()",1000);
        };
        function showTime() {
            document.getElementById("spanid").innerHTML = time;
            time--;
        }
    </script>
</head>
<body>
<div align="center">
    <h1>您访问的页面不存在！</h1>
    <span id="spanid" style="font-size: 20px">10</span>
    秒后跳到&nbsp;&nbsp;<a href="login.jsp">登录</a>
    <br>
    <img src="public/img/404.jpg">
</div>
</body>
</html>
