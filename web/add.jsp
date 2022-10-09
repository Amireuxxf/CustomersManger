<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- HTML5文档-->

<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>添加用户</title>

    <link rel="icon" type="image/x-icon" href="public/img/30.jpg" />

    <!-- 1. 导入CSS的全局样式 -->
    <link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="bootstrap/js/jquery-3.2.1.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script>

        function backSevletAll() {
            location.href="QueryPageServlet";
        }

    </script>
</head>
<body>
<div class="container" style="width: 350px;">
    <center><h3>新增用户页面</h3></center>
    <form action="${pageContext.request.contextPath}/AddServlet" method="post">
        <div class="form-group">
            <label for="name">姓名：</label>
            <input type="text" class="form-control" id="name" value="${old.name}" name="name" placeholder="请输入姓名"
                   style="width: 270px;">
        </div>

        <div class="form-group">
            <label>性别：</label>

            <input type="radio" name="gender" value="男" checked="checked" <c:if test="${old.gender == '男'}"> checked </c:if> />男
            <input type="radio" name="gender" value="女" <c:if test="${old.gender == '女'}"> checked </c:if> />女
        </div>

        <div class="form-group">
            <label for="age">年龄：</label>
            <input type="text" class="form-control" id="age" value="${old.age}" name="age" placeholder="请输入年龄"
                   style="width: 270px;">
        </div>

        <div class="form-group">
            <label for="address">籍贯：</label>
            <select name="address" class="form-control"  id="address" style="width: 270px;">

                <option value="河南" <c:if test="${old.address == '河南'}"> selected </c:if>>河南</option>
                <option value="山东" <c:if test="${old.address == '山东'}"> selected </c:if>>山东</option>
                <option value="湖南" <c:if test="${old.address == '湖南'}"> selected </c:if>>湖南</option>
                <option value="海南" <c:if test="${old.address == '海南'}"> selected </c:if>>海南</option>
                <option value="黑龙江" <c:if test="${old.address == '黑龙江'}"> selected </c:if>>黑龙江</option>
                <option value="湖北" <c:if test="${old.address == '湖北'}"> selected </c:if>>湖北</option>
                <option value="河北" <c:if test="${old.address == '河北'}"> selected </c:if>>河北</option>
                <option value="山西" <c:if test="${old.address == '山西'}"> selected </c:if>>山西</option>
                <option value="安徽" <c:if test="${old.address == '安徽'}"> selected </c:if>>安徽</option>

            </select>
        </div>

        <div class="form-group">
            <label for="qq">QQ：</label>
            <input type="text" class="form-control" name="qq"  value="${old.qq}" id="qq" placeholder="请输入QQ号码"
                   style="width: 270px;"/>
        </div>

        <div class="form-group">
            <label for="email">Email：</label>
            <input type="text" class="form-control" name="email" value="${old.email}" id="email"  placeholder="请输入邮箱地址"
                   style="width: 270px;"/>
            <font style="color: red;">${msg}</font>
        </div>

        <div class="form-group" style="text-align: center">
            <input class="btn btn-primary" type="submit" value="提交"/>
            <input class="btn btn-default" type="reset" value="重置"/>
            <input class="btn btn-default" type="button" onclick="backSevletAll();" value="返回"/>
        </div>
    </form>
</div>
<script>
    /*清除input框的历史记录*/
    $("input").each(function () {
        $(this).attr("autocomplete","off");
    });
</script>
</body>
</html>