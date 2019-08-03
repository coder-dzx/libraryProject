<%--
  Created by IntelliJ IDEA.
  User: 74757
  Date: 2019/5/20
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册界面</title>
    <style type="text/css">
        body{background-color:black;}
        #content{width:300px;height:50px;position:absolute;top:20%;left:50%;margin-top:-25px;margin-left:-150px;font:16px/50px Microsoft YaHei;color:red;text-align:center;}
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
            background-color:blue;
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
    <h1>注册用户</h1>
    <form action="" id="reg_submit"method="post">
        账&nbsp;&nbsp;&nbsp;号：
        <input type="text" id="username" name="username" placeholder="请输入账号"/><br>
        密&nbsp;&nbsp;&nbsp;码：
        <input type="text" id="password"name="password" placeholder="请输入密码"/><br>
        姓&nbsp;&nbsp;&nbsp;名：
        <input type="text" id="name" name="name" placeholder="请输入姓名"/><br>
        邮&nbsp;&nbsp;&nbsp;箱：
        <input type="text" id="email" name="email" placeholder="请输入邮箱"/><br>
        手&nbsp;&nbsp;&nbsp;机：
        <input type="text" id="phone" name="phone" placeholder="请输入手机"/><br>
        验证码：
        <input name="check" type="text" placeholder="验证码" style="height:30px;width:68px;vertical-align:middle">
        <img style="vertical-align:middle" src="checkCode" alt="" onclick="changeCheckCode(this)"><br>
        <script type="text/javascript">
            //图片点击事件
            function changeCheckCode(img) {
                img.src="checkCode?"+new Date().getTime();
            }
            function reg_return() {
                location.href="login.jsp";
            }
        </script>
        <input type="submit" class="button"  value="注&nbsp;&nbsp;&nbsp;&nbsp;册"/>&nbsp;&nbsp;&nbsp;
        <input type="button" class="button" onclick="reg_return()" value="返&nbsp;&nbsp;&nbsp;&nbsp;回"/>
        <h3><div id="errorMsg" style="color: red;font-size: large"></div></h3>
    </form>
</div>
<script>
    //校验用户名
    function checkUsername() {
        var username=$("#username").val();
        var reg_username=/^\w{3,6}$/;
        var flag=reg_username.test(username);
        if(flag){
            $("#username").css("border","");
        }else{
            $("#username").css("border","3px solid red");
        }
        return flag;
    }
    //校验密码
    function checkPassword() {
        var password=$("#password").val();
        var reg_password=/^\w{6,20}$/;
        var flag=reg_password.test(password);
        if(flag){
            $("#password").css("border","");
        }else{
            $("#password").css("border","3px solid red");
        }
        return flag;
    }
    //校验姓名
    function checkName() {
        var name=$("#name").val();
        var reg_name=/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,10}$/;
        var flag=reg_name.test(name);
        if(flag){
            $("#name").css("border","");
        }else{
            $("#name").css("border","3px solid red");
        }
        return flag;
    }
    //校验邮箱
    function checkEamil() {
        var email=$("#email").val();
        var reg_email=/^\w+@\w+\.\w+$/;
        var flag=reg_email.test(email);
        if(flag){
            $("#email").css("border","");
        }else{
            $("#email").css("border","3px solid red");
        }
        return flag;
    }
    //校验手机
    function checkPhone() {
        var phone=$("#phone").val();
        var reg_phone=/^1[3|4|5|7|8][0-9]{9}$/;
        var flag=reg_phone.test(phone);
        if(flag){
            $("#phone").css("border","");
        }else{
            $("#phone").css("border","3px solid red");
        }
        return flag;
    }

$(function () {
    $("#username").blur(checkUsername);
    $("#password").blur(checkPassword);
    $("#name").blur(checkName);
    $("#email").blur(checkEamil);
    $("#phone").blur(checkPhone);
    $("#reg_submit").submit(function () {
        if(checkUsername()&&checkPassword()&&checkName()&&checkEamil()&&checkPhone()){
            $.post("registerServlet",$(this).serialize(),function (data) {
                if(data.flag){
                    alert("注册成功！请前往邮箱激活后登陆");
                }else{
                    $("#errorMsg").html(data.errorMsg);
                }
            });
        }
        return false;
    });
});
</script>
</body>
</html>
