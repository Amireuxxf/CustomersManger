<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: Amireux
  Date: 2022/9/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>管理员登录</title>
    <link rel="stylesheet" href="bootstrap-3.4.1-dist/css/bootstrap.min.css">
    <link rel="shortcut icon" href="public/img/30.jpg" />

    <!-- 1. 导入CSS的全局样式 -->
    &lt;%&ndash;<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">&ndash;%&gt;

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员登录</h3>
    <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text"  name="username"   class="form-control" id="user" placeholder="请输入用户名" title="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" class="form-control" id="password" placeholder="请输入密码" title="请输入密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="ckimg" class="form-control" id="verifycode" placeholder="请输入验证码" title="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode();">
                <img src="${pageContext.request.contextPath}/Checkcode" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" value="登录">

        </div>
    </form>

        <div class="form-group" style="text-align: center;">
            没有帐号? 点击<a href="${pageContext.request.contextPath}/regist.jsp"> 注册</a>
        </div>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span class="glyphicon glyphicon-remove"></span>
        </button>
        <strong>${msg}</strong>
    </div>
</div>

</body>
&lt;%&ndash;<!-- 2. jQuery导入，建议使用1.9以上的版本 -->
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<!-- 3. 导入bootstrap的js文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>&ndash;%&gt;
<!-- jQuery (Bootstrap 的所有 JavaScript 插件都依赖 jQuery，所以必须放在前边) -->
<script src="bootstrap-3.4.1-dist/js/jquery-3.4.1.js"></script>
<!-- 加载 Bootstrap 的所有 JavaScript 插件。你也可以根据需要只加载单个插件。 -->
<script src="bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
<script>

    /*刷新验证码*/
    $("#vcode").click(function () {
        this.src="Checkcode?"+new Date().getTime();
    });

    /*清除input框的历史记录*/
    $("input").each(function () {
        $(this).attr("autocomplete","off");
    });
</script>
</html>--%>


<%--
  Created by IntelliJ IDEA.
  User: Amireux
  Date: 2022/9/27
  Time: 10:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>登录注册</title>
    <link rel="icon" type="image/x-icon" href="public/img/30.jpg" />
    <%--<script src="bootstrap/js/jquery-3.2.1.min.js" charset="utf-8"></script>--%>
    <script src="bootstrap-3.4.1-dist/js/jquery-3.4.1.js"></script>
    <style>
        * {
            box-sizing: border-box;
        }
        body {
            font-family: 'Montserrat',sans-serif;
            background-image: linear-gradient(120deg,#3498db,#8e44ad);;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: -20px 0 50px;
        }

        h1 {
            font-weight: bold;
            margin: 0;
        }
        p {
            font-size: 14px;
            line-height: 20px;
            letter-spacing: .5px;
            margin: 20px 0 30px;
        }
        span {
            font-size: 12px;
        }
        a {
            color: #333;
            font-size: 14px;
            text-decoration: none;
            margin: 15px 0;
        }
        .container {
            background: #fff;
            border-radius: 10px;
            box-shadow: 0 14px 28px rgba(0, 0, 0, .25), 0 10px 10px rgba(0, 0, 0, .22);
            position: relative;
            overflow: hidden;
            width: 768px;
            max-width: 100%;
            min-height: 480px;

        }

        .form-container form {
            background: #fff;
            display: flex;
            flex-direction: column;
            padding: 0 50px;
            height: 100%;
            justify-content: center;
            text-align: center;
        }
        .social-container {
            margin: 20px 0;
        }

        .social-container a {
            border: 1px solid #ddd;
            border-radius: 50%;
            display: inline-flex;
            justify-content: center;
            align-items: center;
            margin: 0 5px;
            height: 40px;
            width: 40px;
        }

        .social-container a:hover {
            background-color: #eee;

        }

        .txtb {
            border-bottom: 2px solid #adadad;
            position: relative;
            margin: 10px 0;
        }

        .txtb input {
            font-size: 15px;
            color: #333;
            border: none;
            width: 100%;
            outline: none;
            background: none;
            padding: 0 3px;
            height: 35px;
        }

        .txtb span::before {
            content: attr(data-placeholder);
            position: absolute;
            top: 50%;
            left: 5px;
            color: #adadad;
            transform: translateY(-50%);
            transition: .5s;
        }
        .txtb span::after {
            content: '';
            position: absolute;
            left: 0%;
            top: 100%;
            width: 0%;
            height: 2px;
            background-image: linear-gradient(120deg,#3498db,#8e44ad);
            transition: .5s;
        }

        .focus + span::before {
            top: -5px;
        }

        .focus + span::after {
            width: 100%;
        }

        button {
            border-radius: 20px;
            border: 1px solid #ff4b2b;
            background: #ff4b2b;
            color: #fff;
            font-size: 12px;
            font-weight: bold;
            padding: 12px 45px;
            letter-spacing: 1px;
            text-transform: uppercase;
            transition: transform 80ms ease-in;
            cursor: pointer;
        }

        button:active {
            transform: scale(.95);
        }

        button:focus {
            outline: none;
        }

        button.ghost {
            background: transparent;
            border-color: #fff;
        }

        .form-container {
            position: absolute;
            top: 0;
            height: 100%;
            transition: all .6s ease-in-out;
        }

        .form-container button {
            background: linear-gradient(120deg, #3498db, #8e44ad);
            border: none;
            background-size: 200%;
            color: #fff;
            transition: .5s;
        }

        .form-container button:hover {
            background-position: right;
        }

        .sign-in-container {
            left: 0;
            width: 50%;
            z-index: 2;
        }
        .sign-in-container form a {
            position: relative;
            left: 100px;
        }
        .sign-up-container {
            left: 0;
            width: 50%;
            z-index: 1;
            opacity: 0;
        }
        .sign-up-container button {
            margin-top: 20px;
        }
        .overlay-container {
            position:absolute;
            top: 0;
            left: 50%;
            width: 50%;
            height: 100%;
            overflow: hidden;
            transition: transform .6s ease-in-out;
            z-index: 100;
        }
        .overlay {
            background-image: linear-gradient(120deg,#3498db,#8e44ad);
            color: #fff;
            position: relative;
            left: -100%;
            height: 100%;
            width: 200%;
            transform: translateY(0);
            transition: transform .6s ease-in-out;
        }
        .overlay-panel {
            position: absolute;
            top: 0;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            padding: 0 40px;
            height: 100%;
            width: 50%;
            text-align: center;
            transform: translateY(0);
            transition: transform .6s ease-in-out;
        }
        .overlay-right {
            right: 0;
            transform: translateY(0);

        }

        .overlay-left {
            transform: translateY(-20%);
        }

        .container.right-panel-active .sign-in-container {
            transform: translateY(100%);
        }

        .container.container.right-panel-active .overlay-container {
            transform: translateX(-100%);
        }
        .container.right-panel-active .sign-up-container {
            transform: translateX(100%);
            opacity: 1;
            z-index: 5;
        }
        .container.container.right-panel-active .overlay {
            transform: translateX(50%);
        }
        .container.container.right-panel-active .overlay-left {
            transform: translateY(0);
        }
        .container.container.right-panel-active .overlay-right {
            transform: translateY(20%);
        }


    </style>
</head>
<body>
<div class="container" id="login-box">


    <div class="form-container sign-up-container">
        <form id="formid" action="${pageContext.request.contextPath}/RegistUserServlet" method="post">
            <h1>注册</h1>
            <div class="txtb">
                <input type="text" name="username">
                <span data-placeholder="用户名" ></span>
            </div>
            <div class="txtb">
                <input type="text" name="nickname">
                <span data-placeholder="昵称" ></span>
            </div>
            <div class="txtb">
                <input type="password" name="password">
                <span data-placeholder="密码" ></span>
            </div>
            <div class="txtb">
                <input type="password" name="repassword">
                <span data-placeholder="确认密码" ></span>
            </div>
            <div class="txtb">
                <input type="text" name="ckimg" >
                <span data-placeholder="验证码"></span>
                <img style="position:absolute;margin-left: -110px" src="${pageContext.request.contextPath}/Checkcode" title="看不清点击刷新" id="vcodes"/>
            </div>
            <button>注册</button>
        </form>
    </div>
    <div class="form-container sign-in-container">
        <form action="LoginServlet" method="post">
            <h1>登录</h1>
            <div class="txtb">
                <input type="text" name="username">
                <span data-placeholder="用户名" ></span>
            </div>
            <div class="txtb">
                <input type="password" name="password" >
                <span data-placeholder="密码"></span>
            </div>
            <div class="txtb">
                <input type="text" name="ckimg" >
                <span data-placeholder="验证码"></span>
                    <img style="position:absolute;margin-left: -110px" src="${pageContext.request.contextPath}/Checkcode" title="看不清点击刷新" id="vcode"/>
            </div>
            <a href="#">忘记密码？</a>
            <button>登录</button>
        </form>
    </div>

    <div class="overlay-container">
        <div class="overlay">
            <div class="overlay-panel overlay-left">
                <h1>已有账号？</h1>
                <p>请使用您的账号进行登录</p>
                <button class="ghost" id="signIn" >登录</button>
            </div>
            <div class="overlay-panel overlay-right">
                <h1>没有账号?</h1>
                <p>立即注册加入我们，和我们一起开始旅程吧</p>
                <button class="ghost" id="signUp">注册</button>
            </div>
        </div>

    </div>
</div>

</body>
<script>

    /*刷新验证码*/
    $("#vcode").click(function () {
        this.src="Checkcode?"+new Date().getTime();
    });
    $("#vcodes").click(function () {
        this.src="Checkcode?"+new Date().getTime();
    });

    /*清除input框的历史记录*/
    $("input").each(function () {
        $(this).attr("autocomplete","off");
    });

    $("#signUp").click(function(){
        $("#login-box").addClass('right-panel-active')
    })

    $("#signIn").click(function(){
        $("#login-box").removeClass('right-panel-active')
    })

    $(".txtb input").on("focus",function(){
        $(this).addClass("focus")
    })

    $(".txtb input").on("blur",function(){
        if($(this).val() == '')
            $(this).removeClass("focus")
    })

</script>

</html>
