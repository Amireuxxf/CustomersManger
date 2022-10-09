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
    <title>用户管理系统</title>
    <link rel="shortcut icon" href="public/img/30.jpg" />
    <!-- 1. 导入CSS的全局样式 -->
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <style>
        #header{
            background-color: gold;
        }
        th,td{
            text-align: center;
            height: 70px;

        }
    </style>
</head>
<body>
<%--如果session中没有username信息，请登录--%>
<c:if test="${empty sessionScope.user.username}">
    未登录   请<a href="login.jsp">登录</a>
</c:if>

<%--判断session对象中没有username,来源于登录成功之后写入的session--%>
<c:if test="${not empty sessionScope.user.username}">

    <div class="container">

        <br>
        <span style="font-size: 16px">当前用户： <span style="color: red;">${sessionScope.user.nickname}</span></span>

        <a style="float: right;font-size: 16px" href="LoginOutServlet">[退出]</a>

        <h1 style="text-align: center; font-weight: bold">用户管理系统</h1>

            <%--搜索功能--%>
            <%--过滤搜索中的空格 onkeyup事件，用户输入空格后自动清除空格--%>
        <div style="float: left; margin-bottom: -8px">
            <form action="QueryPageServlet" method="post" class="form-inline">
                <div class="form-group">
                    <label for="exampleInputName2" style="font-size: 16px">姓名:</label>
                    <input onkeyup="this.value=this.value.replace(/[, ]/g,'')" type="text" class="form-control" name="name" value="${map.name[0]}" id="exampleInputName2" placeholder="搜索的姓名">
                </div>
                <div class="form-group">
                    <label for="exampleInputName3" style="font-size: 16px">籍贯:</label>
                    <input onkeyup="this.value=this.value.replace(/[, ]/g,'')" type="text" class="form-control" name="address"  value="${map.address[0]}" id="exampleInputName3" placeholder="搜索的籍贯">
                </div>
                <div class="form-group">
                    <label for="exampleInputEmail2" style="font-size: 16px">Email:</label>
                    <input onkeyup="this.value=this.value.replace(/[, ]/g,'')" type="text" class="form-control" name="email"  value="${map.email[0]}" id="exampleInputEmail2" placeholder="搜索的Email">
                </div>
                <button type="submit" class="btn btn-default">
                    <span class="glyphicon glyphicon-search"></span> 搜索
                </button>
            </form>
        </div>

            <%--添加，选中删除，刷新--%>
        <div style="float: right; margin-bottom: 2px">
            <a type="button" class="btn btn-primary" href="add.jsp">新增用户</a>
            <a type="button" class="btn btn-primary" id="selectDelete">删除选中</a>
            <a type="button" class="btn btn-primary glyphicon glyphicon-refresh" href="QueryPageServlet"></a>
        </div>

            <%--数据显示--%>
        <form id="formID" action="SelectDeleteServlet" method="post">
            <table border="1px" style="font-size: 18px;" class="table table-bordered table-hover">
                <tr id="header">
                    <th>
                        <label style="display: block; padding: 5px">
                            <input type="checkbox"  id="chall">
                        </label>

                    </th>
                    <th>编号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>籍贯</th>
                    <th>QQ</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${requestScope.pg.list}" var="list">
                    <tr>
                        <td>
                            <label>
                                <input type="checkbox" name="checkbox" class="checkbox"  value="${list.id}">
                            </label>
                        </td>
                        <td>${list.id}</td>
                        <td>${list.name}</td>
                        <td>${list.gender}</td>
                        <td>${list.age}</td>
                        <td>${list.address}</td>
                        <td>${list.qq}</td>
                        <td>${list.email}</td>
                        <td>
                                <%--修改按钮--%>
                            <a type="button" class="btn btn-primary glyphicon glyphicon-pencil" href="UpdateServlet?id=${list.id}"></a>
                                <%--删除按钮--%>
                            <a type="button" class="btn btn-primary glyphicon glyphicon-trash" onclick="return confirm('确定要删除我吗？')" href="DeleteServlet?id=${list.id}"></a>

                        </td>

                    </tr>
                </c:forEach>
            </table>
        </form>

        <%--无搜索内容时给出提示--%>
        <div style="text-align: center">
            <c:if test="${empty pg.list}">
                <font style="color: red;font-size: 30px">没有搜索到内容！</font>
            </c:if>
        </div>

        <%--分页--%>
        <div style="text-align: center;margin-top: -20px">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li>
                        <span>当前页${pg.pageNum}/${pg.totalPages}</span>
                        <a href="javascript:void(0)" onclick="go(1)">首页</a>
                        <a href="javascript:void(0)" aria-label="Previous" onclick="go(${pg.beforePage})">
                            <span aria-hidden="true">上一页</span>
                        </a>
                    </li>

                        <c:forEach items="${pg.pageBar}" var="bar">

                        <c:if test="${pg.pageNum == bar}">
                            <li class="active"><a href="javascript:void(0)" onclick="go(${bar})">${bar}</a></li>
                        </c:if>

                        <c:if test="${pg.pageNum != bar}">
                            <li><a href="javascript:void(0)" onclick="go(${bar})">${bar}</a></li>
                        </c:if>
                        </c:forEach>

                    <li>
                        <a href="javascript:void(0)" aria-label="Next" onclick="go(${pg.nextPage})">
                            <span aria-hidden="true">下一页</span>
                        </a>
                        <a href="javascript:void(0)" onclick="go(${pg.totalPages})">尾页</a>
                        <span>共${pg.totalCounts}条</span>
                        <span style="height: 33px;border-radius: 5px">
                            <input type="text" id="sinp"  style="width: 35px;height: 22px;outline:none;text-align: center;">
                            <button role="button" href="javascript:void(0)" onclick="gos()">跳转</button>
                        </span>
                    </li>
                </ul>
            </nav>
        </div>


    </div>
</c:if>

</body>
<!-- 2. jQuery导入，建议使用1.9以上的版本 -->
<script src="bootstrap/js/jquery-3.2.1.min.js"></script>
<!-- 3. 导入bootstrap的js文件 -->
<script src="bootstrap/js/bootstrap.min.js"></script>
<script>


    <%--分页跳转函数--%>
    function gos() {
        var value = document.getElementById("sinp").value.replace(/\ +/g, '').replace(/[\r\n]/g, '');
        if (value == "" || isNaN(value)){
            alert("请输入有效数字！");
        } else if (value > ${pg.totalPages}){
            alert("页码超出范围，请重新输入！")
        } else {
            go(value);
        }
    }


    function go(pageNum){
        location.href = "/QueryPageServlet?name=${map.name[0]}&address=${map.address[0]}&email=${map.email[0]}&pageNum=" + pageNum;
    }

    /*清除input所有历史记录*/
    $("input").each(function(){
        $(this).attr("autocomplete","off");
    });

    /*反选/全选全不选 jq写法*/
    $("#chall").click(function () {
        $(":checkbox").prop("checked",this.checked);
    });


    /*批量删除*/
    var str = "";
    $("#selectDelete").click(function () {
        //获了所有复选框
        var check = $(".checkbox");
        //遍历复选框
        $(check).each(function () {
            //哪个复选框被选中
            if(this.checked){
                str += this.value+","
            }
        });

        if(str == ''){
            alert("您还没有选中，请选择您要删除的内容！")
        }else {
            var bol = window.confirm("你要删除的编号为："+str);
            if(bol){
                <%--$("#formID").action="${pageContext.request.contextPath}/SelectDeleteServlet";--%>
                $("#formID").submit();
            }
        }

    });


</script>

</html>
