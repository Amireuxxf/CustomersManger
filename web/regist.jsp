<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Amireux
  Date: 2022/9/19
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>

    <title>管理员注册</title>
    <link rel="shortcut icon" href="public/img/30.jpg" />
    <!-- 1. 导入CSS的全局样式 -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">


    <style>
        #eye{
            position: relative;
            left: 345px;
            top: -25px;
        }
    </style>

</head>
<body>
<div class="container" style="width: 400px;">
    <h3 style="text-align: center;">管理员注册</h3>
    <form id="formid" action="${pageContext.request.contextPath}/RegistUserServlet" method="post">
        <div class="form-group">
            <label for="user">用户名：</label>
            <input type="text" onblur="checkUserName()" name="username" value="${requestScope.user.username}" class="form-control" id="user" placeholder="请输入用户名"/>
        </div>

        <div class="form-group">
            <label for="nickname">昵称：</label>
            <input type="text"  name="nickname" value="${requestScope.user.nickname}" class="form-control" id="nickname" placeholder="请输入昵称"/>
        </div>

        <div class="form-group">
            <label for="password">密码：</label>
            <input type="password" name="password" value="${requestScope.user.password}"  class="form-control" id="password" placeholder="请输入密码"/>
            <span class="glyphicon glyphicon-eye-close" onclick="showhide()" id="eye" ></span>
        </div>

        <div class="form-group">
            <label for="repassword">确认密码：</label>
            <input type="password" name="repassword" value="${requestScope.user.password}" class="form-control" onblur="ckpwd()" id="repassword" placeholder="确认密码"/>
        </div>

        <div class="form-inline">
            <label for="vcode">验证码：</label>
            <input type="text" name="ckimg" class="form-control"   id="verifycode" placeholder="请输入验证码" style="width: 120px;"/>
            <a href="javascript:refreshCode();">
                <img src="${pageContext.request.contextPath}/Checkcode" title="看不清点击刷新" id="vcode"/>
            </a>
        </div>
        <hr/>
        <div class="form-group" style="text-align: center;">
            <input class="btn btn btn-primary" type="submit" id="submit" value="注册">

        </div>
    </form>
        <div class="form-group" style="text-align: center;">
            已有帐号,点击 <a href="${pageContext.request.contextPath}/login.jsp">登录</a>

        </div>

    <!-- 出错显示的信息框 -->
    <div class="alert alert-warning alert-dismissible" role="alert">
        <button type="button" class="close" data-dismiss="alert" >
            <span class="glyphicon glyphicon-remove"></span>
        </button>
        <strong id="strongid">${msg}</strong>
        <span id="errMsg" style="color: red"></span>
    </div>
</div>



</body>
<!-- 2. jQuery导入，建议使用1.9以上的版本 -->
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<!-- 3. 导入bootstrap的js文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>

<script>
    /*刷新验证码*/
    $("#vcode").click(function () {
        this.src="Checkcode?"+new Date().getTime();
    });

    /*清除input框的历史记录*/
    $("input").each(function () {
        $(this).attr("autocomplete","off");
    });


    /*显示与不显示密码*/
    $("#eye").click(function () {
        var $password = $("#password");
        var $repassword = $("#repassword");

        var $eye = $("#eye");

        if($password.attr("type") == "password"){
            $password.attr("type","text");
            $repassword.attr("type","text");
            $eye.attr("class","glyphicon glyphicon-eye-open")

        }else if($password.attr("type") == "text"){
            $password.attr("type","password");
            $repassword.attr("type","password");
            $eye.attr("class","glyphicon glyphicon-eye-close")
        }
    });

    /*如果校验不通过，不能进行提交*/
    $("#formid").click(function () {
        if( checkUserName() && ckpwd()){
            $("#formid").submit();
        }else{
            return false;
        }
    });


    var $errMsg =  $("#errMsg");

    /*校验用户名*/
    function checkUserName() {
        //获取输入信息
        var username = $("#user").val();
        //正则
        var regExp = new RegExp("^\\w{3,12}$");
        if(!regExp.test(username) ){
            $errMsg.html("用户名必须满足3~12位字母/数据/下划线");
            return false;
        }else{
            $errMsg.html("");
            return true;
        }

    }

    /*TODO 校验昵称*/

    /*校验两次密码是否一致*/
    function ckpwd() {
        var password = $("#password").val();
        var repassword = $("#repassword").val();

        if( password == "" && repassword ==""){
            $errMsg.html("密码不能为空");
            return false;
        }

        //比较两个内容是否一致
        if(password != repassword){
            $errMsg.html("两次输入的密码不致！");
            return false;
        }else {
            $errMsg.html("");
            return true;
        }
    }


</script>
</html>