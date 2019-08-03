<%--
  Created by IntelliJ IDEA.
  User: 74757
  Date: 2019/5/26
  Time: 19:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>管理员界面</title>
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" href="css/bootstrap-admin-theme.css">
    <link rel="stylesheet" href="css/bootstrap-admin-theme.css">
    <script src="js/jquery-3.3.1.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap-dropdown.min.js"></script>
    <script src="js/click.js"></script>
</head>

<script src="js/jquery-3.3.1.js"></script>
<script src="js/bootstrap.min.js"></script>

<body class="bootstrap-admin-with-small-navbar">
<nav class="navbar navbar-inverse navbar-fixed-top bootstrap-admin-navbar bootstrap-admin-navbar-under-small" role="navigation">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div class="collapse navbar-collapse main-navbar-collapse">
                    <a class="navbar-brand" href="admin.jsp"><strong>欢迎使用河北师范大学图书馆管理系统</strong></a>
                    <ul class="nav navbar-nav navbar-right">
                        <li class="dropdown">
                            <a href="#" role="button" class="dropdown-toggle" data-hover="dropdown"> <i class="glyphicon glyphicon-user"></i>欢迎您，${user.username}<i class="caret"></i></a>
                            <ul class="dropdown-menu">
                                <li><a href="#updateinfo" data-toggle="modal">个人资料</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="#updatepwd" data-toggle="modal">修改密码</a></li>
                                <li role="presentation" class="divider"></li>
                                <li><a href="login.jsp">退出</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</nav>

<div class="container">
    <!-- left, vertical navbar & content -->
    <div class="row">
        <!-- left, vertical navbar -->
        <div class="col-md-2 bootstrap-admin-col-left">
            <ul class="nav navbar-collapse collapse bootstrap-admin-navbar-side">
                <li>
                    <a href="admin_book.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 图书管理</a>
                </li>
                <li>
                    <a href="admin_user.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 读者管理</a>
                </li>
                <li>
                    <a href="admin_booktype.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 图书分类管理</a>
                </li>
                <li>
                    <a href="admin_borrow.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 图书借阅信息</a>
                </li>
                <li>
                    <a href="admin_history.jsp"><i class="glyphicon glyphicon-chevron-right"></i> 图书归还信息</a>
                </li>

            </ul>

        </div>

        <!-- content -->
        <div class="col-md-10">
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书管理</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>根据图书编号、类型、名称、作者、总数、馆藏地任意条件进行模糊查询图书基本信息</li>
                                <li>添加、修改、删除图书信息</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书分类管理</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>根据分类名称查询图书分类信息</li>
                                <li>添加、修改、删除图书分类信息</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书借阅</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>展示所有读者正在借阅图书的信息、借阅日期、截止还书日期、是否逾期等</li>
                                <li>对于逾期未还读者还书进行罚款操作(收费标准：3元/天)</li>
                            </ul>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">图书归还</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>展示所有读者的借阅历史信息</li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">

                <div class="col-md-6">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">读者管理</div>
                        </div>
                        <div class="bootstrap-admin-panel-content">
                            <ul>
                                <li>查询所有读者的基本信息</li>
                                <li>添加、修改、删除读者信息</li>
                            </ul>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>


<!---------------------------------------------------------->

<form class="form-horizontal" id="update_pwd" method="post" action="">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updatepwd" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="myModalLabel">
                        修改密码
                    </h4>
                </div>
                <div class="modal-body">

                    <!--正文-->
                    <input type="hidden" name="tip" value="1">
                    <input type="hidden" name="url" value="index">
                    <div class="form-group">
                        <label for="oldPwd" class="col-sm-3 control-label">原密码</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" name="password" id="oldPwd"  placeholder="请输入原密码">
                            <label class="control-label" for="oldPwd" style="display: none"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="newPwd" class="col-sm-3 control-label">新密码</label>
                        <div class="col-sm-7">
                            <input type="password" class="form-control" name="password2" id="newPwd"  placeholder="请输入新密码">
                            <label class="control-label" for="newPwd" style="display: none"></label>
                        </div>
                    </div>

                    <!--正文-->
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">
                        关闭
                    </button>

                    <button type="submit" id="update_sub" class="btn btn-primary" >
                        修改
                    </button>

                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<script>
    $(function () {
        //修改密码
        $("#update_pwd").submit(function () {
            var password1=$("#oldPwd").val();
            var password2=$("#newPwd").val();

            var update_password=/^\w{6,20}$/;
            var flag=update_password.test(password1)&&update_password.test(password2);
            if(flag){
                if($("#oldPwd").val()!=""&&$("#newPwd").val()!=""){
                    $.post("updatePwdServlet", $(this).serialize(), function (data) {
                        $("#updatepwd").modal("hide");//关闭模糊框
                        showInfo(data.errorMsg);
                    });
                }
            }else{
                $("#updatepwd").modal("hide");//关闭模糊框
                showInfo("密码长度在6-20位哦！");
            }
            return false;
        });

        //修改信息
        $("#update_info").submit(function () {
            var name=$("#name").val();
            var phone=$("#phone").val();
            var email=$("#email").val();
            var reg_name=/^[\u4E00-\u9FA5\uf900-\ufa2d·s]{2,10}$/;
            var flag1=reg_name.test(name);
            var reg_phone=/^1[3|4|5|7|8][0-9]{9}$/;
            var flag2=reg_phone.test(phone);
            var reg_email=/^\w+@\w+\.\w+$/;
            var flag3=reg_email.test(email);

            if($("#name").val()!=""&&$("#phone").val()!=""&&$("#email").val()!=""){
                if(flag1){
                    if(flag2){
                        if(flag3){
                            $("#update_info").submit(function () {
                                $.post("updateInfoServlet",$(this).serialize(),function (data) {
                                    $("#updateinfo").modal("hide");//关闭模糊框
                                    showInfo(data.errorMsg);
                                });
                            });

                        }else{
                            $("#updateinfo").modal("hide");//关闭模糊框
                            showInfo("请输入正确的邮箱！");
                        }
                    }else{
                        $("#updateinfo").modal("hide");//关闭模糊框
                        showInfo("请输入正确的手机号！");
                    }
                }else{
                    $("#updateinfo").modal("hide");//关闭模糊框
                    showInfo("请输入正确的姓名！");
                }
            }
            return false;
        });

        $("#btn_info_close").click(function () {
            location.reload();
        });
    });
    function showInfo(msg) {
        $("#div_info").html(msg);
        $("#modal_info").modal("show");
    }


    $('#modal_info').on('hide.bs.modal',function() {//提示模糊框隐藏时候触发
        location.reload();  	//刷新当前页面
    });

</script>


<!-------------------------------------------------------------->

<!-------------------------个人资料模糊框------------------------------------->

<form class="form-horizontal" id="update_info" method="post" action="">   <!--保证样式水平不混乱-->
    <!-- 模态框（Modal） -->
    <div class="modal fade" id="updateinfo" tabindex="-1" role="dialog" aria-labelledby="ModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                        &times;
                    </button>
                    <h4 class="modal-title" id="ModalLabel">
                        个人资料
                    </h4>
                </div>

                <div class="modal-body">

                    <!--正文-->
                    <input type="hidden" name="tip" value="2">
                    <input type="hidden" name="url" value="index">
                    <div class="form-group">
                        <label for="name" class="col-sm-3 control-label">姓名</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="name" name="name" placeholder="请输入您的姓名" value="${user.name}" >
                            <label class="control-label" for="name" style="display: none"></label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="phone" class="col-sm-3 control-label">手机号</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入您的手机号" value="${user.phone}" >
                            <label class="control-label" for="phone" style="display: none"></label>
                        </div>
                    </div>


                    <div class="form-group">
                        <label  class="col-sm-3 control-label">邮箱</label>
                        <div class="col-sm-7">
                            <input type="text" class="form-control" id="email" name="email"  placeholder="请输入您的邮箱" value="${user.email}">
                            <label class="control-label" for="email" style="display: none"></label>
                        </div>
                    </div>

                    <!--正文-->

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭
                    </button>
                    <button type="submit" class="btn btn-primary" >
                        修改
                    </button>
                </div>
            </div><!-- /.modal-content -->
        </div><!-- /.modal -->
    </div>

</form>
<!-------------------------------------------------------------->



<div class="modal fade" id="modal_info" tabindex="-1" role="dialog" aria-labelledby="addModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="infoModalLabel">提示</h4>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-lg-12" id="div_info"></div>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" id="btn_info_close" data-dismiss="modal">关闭</button>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

