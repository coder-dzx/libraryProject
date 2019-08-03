<%--
  Created by IntelliJ IDEA.
  User: 74757
  Date: 2019/5/20
  Time: 13:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆界面</title>
    <style type="text/css">
    body{background-color:black;}
    #content{width:300px;height:50px;position:absolute;top:30%;left:50%;margin-top:-25px;margin-left:-150px;font:16px/50px Microsoft YaHei;color:red;text-align:center;}
    .sbxg{position:absolute;top:0;left:0;width:100%;height:100%;cursor:none;}
    #canvas{z-index:-10;}
    #bgdiv{z-index:-9;}
    .button {
        -webkit-transition-duration: 0.4s; /* Safari */
        transition-duration: 0.4s;
        font-size: 16px;
        border: 2px solid red;
    }
    .button:hover {
        background-color:blue; /* Green */
        color: red;
    }
</style>
    <div id="bgdiv" class="sbxg"></div>
    <canvas id="canvas" class="sbxg"></canvas>
    <script src="js/mousehover.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/jquery-3.3.1.min.js"></script>
    <link href="css/bootstrap-theme.css">
    <link href="css/bootstrap.css">
    <link href="css/bootstrap-admin-theme.css">

</head>
<body>
<div id="content">
    <h2>欢迎登录图书馆管理系统</h2>
    <form action="" method="post" id="log_submit">
            账&nbsp;&nbsp;&nbsp;号：
            <input type="text" id="username"name="username" placeholder="请输入账号"/><br>
            密&nbsp;&nbsp;&nbsp;码：
            <input type="password" id="password" name="password" placeholder="请输入密码"/><br>
            验证码：
            <input name="check" type="text" placeholder="验证码" style="height:30px;width:68px;vertical-align:middle">
            <img style="vertical-align:middle" src="checkCode" alt="" onclick="changeCheckCode(this)"><br>
        <font color="white">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;没有账号？</font> <a href="register.jsp" style="color:red;">立即注册</a><br>
            <script type="text/javascript">
            //图片点击事件
            function changeCheckCode(img) {
                img.src="checkCode?"+new Date().getTime();
            }
            </script>
            <input type="submit" class="button"  value="登&nbsp;&nbsp;&nbsp;&nbsp;录"/>
        <div id="errorMsg" style="color: red;font-size: large"></div>
        <%=request.getAttribute("login_msg")==null?"":request.getAttribute("login_msg")%>
    </form>
    <script>
        $(function () {
            $("#log_submit").submit(function () {
                if($("#username").val()!="" && $("#password").val()!=""){
                $.post("loginServlet",$(this).serialize(),function (data) {
                    if(data.flag){
                        if(data.errorMsg=="1"){
                            location.href="user.jsp";
                        }else{
                            location.href="admin.jsp";
                        }
                    }else{
                        $("#errorMsg").html(data.errorMsg);
                    }
                });
                }
                return false;
            });

        });
    </script>
</div>
</body>
</html>
