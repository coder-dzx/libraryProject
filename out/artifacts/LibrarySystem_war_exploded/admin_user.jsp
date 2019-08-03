<%--
  Created by IntelliJ IDEA.
  User: 74757
  Date: 2019/6/1
  Time: 14:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>读者管理</title>
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

<script>
    //加载函数
    $(function () {
        load(1);
        //修改表单的提交事件
        $("#updateuser_submit").submit(function () {
            $.post("adminUpdateUserServlet",$(this).serialize(),function (data) {
                $("#updateModal").modal("hide");//关闭模糊框
                if(data.flag){
                    showInfo(data.errorMsg);
                }else{
                    showInfo(data.errorMsg);
                }
            });
            return false;
        });
        //添加表单的提交事件
        $("#adduser_submit").submit(function () {
            $.post("adminAddUserServlet",$(this).serialize(),function (data) {
                $("#addModal").modal("hide");//关闭模糊框
                if(data.errorMsg){
                    showInfo(data.errorMsg);
                }else{
                    showInfo(data.errorMsg);
                }
            });
            return false;
        });
    });

    //分页查询函数
    function load(currentPage) {

        $.post("adminSelectUserServlet",{currentPage:currentPage},function (pb) {
            $("#totalPage").html(pb.totalPage);
            $("#totalCount").html(pb.totalCount);
            var lis="";
            var beforeNum=pb.currentPage-1;
            if(beforeNum<=0){
                beforeNum=1;
            }
            var firstPage='<li onclick="javascript:load(1)"><a href="javascript:void(0)">首页</a></li> ';
            lis+=firstPage;
            //判断如果是第一页将上一页设置为禁用状态
            if(pb.currentPage==1){
                var beforePage='<li class="disabled" onclick="javascript:load('+beforeNum+')"><a href="javascript:void(0)">上一页</a></li> ';
            }else{
                var beforePage='<li onclick="javascript:load('+beforeNum+')"><a href="javascript:void(0)">上一页</a></li> ';
            }
            lis+=beforePage;

            var begin;
            var end;

            if(pb.totalPage<5){
                begin=1;
                end=pb.totalPage;
            }else{
                begin=pb.currentPage-2;
                end=pb.currentPage+2;
                if(begin<1){
                    begin=1;
                    end=begin+4;
                }
                if(end>pb.totalPage){
                    begin=pb.totalPage-4;
                    end=pb.totalPage;
                }
            }

            for(var i=begin;i<=end;i++){
                var li="";
                if(pb.currentPage==i){
                    li='<li class="active" onclick="javascript:load('+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                }else{
                    li='<li onclick="javascript:load('+i+')"><a href="javascript:void(0)">'+i+'</a></li>';
                }
                lis+=li;
            }
            var nextNum;
            nextNum=pb.currentPage+1;
            if(nextNum>=pb.totalPage){
                nextNum=pb.totalPage;
            }
            if(currentPage==pb.totalPage){
                var nextPage='<li class="disabled" onclick="javascript:load('+nextNum+')"><a href="javascript:void(0)">下一页</a></li>';
            }else{
                var nextPage='<li onclick="javascript:load('+nextNum+')"><a href="javascript:void(0)">下一页</a></li>';
            }
            lis+=nextPage;
            var lastPage='<li onclick="javascript:load('+pb.totalPage+')"><a href="javascript:void(0)">末页</a></li> ';
            lis+=lastPage;
            $("#pagNum").html(lis);

            var list=" <thead>\n" +
                "                        <tr>\n" +
                "                            <th>用户名</th>\n" +
                "                            <th>读者姓名</th>\n" +
                "                            <th>邮箱</th>\n" +
                "                            <th>手机</th>\n" +
                "                            <th>当前借阅数</th>\n" +
                "                            <th>历史借阅数</th>\n" +
                "                            <th>操作</th>\n" +
                "                        </tr>\n" +
                "                        </thead>";
            for(var i=0;i<pb.list.length;i++){
                var data=pb.list[i];
                var li='<tbody>\n' +
                    '                            <td>'+data.username+'</td>\n' +
                    '                            <td>'+data.name+'</td>\n' +
                    '                            <td>'+data.email+'</td>\n' +
                    '                            <td>'+data.phone+'</td>\n' +
                    '                            <td>'+data.currentborrow+'</td>\n' +
                    '                            <td>'+data.historyborrow+'</td>\n' +
                    '                            <td><button type="button" class="btn btn-warning btn-xs" data-toggle="modal" data-target="#updateModal" id="btn_update" onclick="showuserinfo(\''+data.username+'\',\''+data.name+'\',\''+data.email+'\',\''+data.phone+'\',\''+data.currentborrow+'\',\''+data.historyborrow+'\')">修改</button>\n' +
                    '                            <button type="button" class="btn btn-danger btn-xs" onclick="deletebook(\''+data.username+'\')">删除</button></td>\n' +
                    '</tbody>';
                list+=li;
            }
            $("#data_list").html(list);
        });
    }

    //修改按钮函数
    function showuserinfo(username,name,email,phone,currentborrow,historyborrow) {
        $("#updateuser").val(username);
        $("#updatename").val(name);
        $("#updateemail").val(email);
        $("#updatephone").val(phone);
        $("#updatecurrent").val(currentborrow);
        $("#updatehistory").val(historyborrow);
    }
    //删除按钮函数
    function deletebook(username) {
        con = confirm("是否删除?");
        if(con == true) {
            location.href = "adminDeleteUserServlet?username=" + username;
        }
    }

</script>

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
                <div class="col-lg-12">
                    <div class="panel panel-default bootstrap-admin-no-table-panel">
                        <div class="panel-heading">
                            <div class="text-muted bootstrap-admin-box-title">读者管理</div>
                        </div>
                        <div class="bootstrap-admin-no-table-panel-content bootstrap-admin-panel-content collapse in">
                            <form class="form-horizontal" action=" selectServlet" method="post">

                                <div class="col-lg-3 form-group">

                                    <button type="button" class="btn btn-primary" id="btn_add" data-toggle="modal" data-target="#addModal">添加读者</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-12">
                    <table id="data_list" class="table table-hover table-bordered" cellspacing="0" width="100%">
                        <thead>
                        <tr>
                            <th>用户名</th>
                            <th>读者姓名</th>
                            <th>邮箱</th>
                            <th>手机号</th>
                            <th>当前借阅数</th>
                            <th>历史借阅数</th>
                            <th>操作</th>
                        </tr>
                        </thead>
                        <!---在此插入信息-->
                    </table>
                </div>
            </div>
            <nav aria-label="..." style="text-align: center">
                <ul  class="pagination" id="pagNum">
                </ul>
            </nav>
            <div class="page_num_inf" style="text-align: center">
                <h3>共<font color="blue" id="totalPage"></font>页,共<font color="red" id="totalCount"></font>条</h3>
            </div>
        </div>


        <!-- 修改模态框（Modal） -->
        <form class="form-horizontal" id="updateuser_submit" method="post" action="">
            <!--保证样式水平不混乱-->
            <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="updateModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="updateModalLabel">修改读者信息</h4>
                        </div>
                        <div class="modal-body">

                            <!---------------------表单-------------------->

                            <div class="form-group">
                                <label for="updateuser" class="col-sm-3 control-label" >用户名</label>
                                <div class="col-sm-7">
                                    <input type="text" readonly="true" class="form-control" id="updateuser" name="username" placeholder="请输入用户名"> <label class="control-label" for="updateuser" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="updatename" class="col-sm-3 control-label">读者姓名</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="name" id="updatename" required="required"  placeholder="请输入读者姓名">
                                    <label class="control-label" for="updatename" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="updateemail" class="col-sm-3 control-label">邮箱</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="updateemail" name="email" placeholder="请输入邮箱"> <label class="control-label" for="updateemail" style="display: none;"></label>
                                </div>
                            </div>



                            <div class="form-group">
                                <label for="updatephone" class="col-sm-3 control-label">手机号</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="updatephone" name="phone" placeholder="请输入手机号"> <label class="control-label" for="updatephone" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="updatecurrent"  class="col-sm-3 control-label">当前借阅数</label>
                                <div class="col-sm-7">
                                    <input type="text" disabled="true" class="form-control" id="updatecurrent" name="currentsum" placeholder="请输入当前借阅数"> <label class="control-label" for="updatecurrent" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="updatehistory"  class="col-sm-3 control-label">历史借阅数</label>
                                <div class="col-sm-7">
                                    <input type="text" disabled="true" class="form-control" id="updatehistory" name="historysum" placeholder="请输入历史借阅数"> <label class="control-label" for="updatehistory" style="display: none;"></label>
                                </div>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary" id="update_book">修改</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal -->
            </div>

        </form>
        <!-------------------------------------------------------------->

        <!--------------------------------------添加的模糊框------------------------>
        <form class="form-horizontal" id="adduser_submit" method="post" action="">
            <!--保证样式水平不混乱-->
            <!-- 模态框（Modal） -->
            <div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            <h4 class="modal-title" id="myModalLabel1">添加新读者</h4>
                        </div>
                        <div class="modal-body">

                            <!---------------------表单-------------------->

                            <div class="form-group">
                                <label for="addusername" class="col-sm-3 control-label">用户名</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="addusername" required="required" name="username" placeholder="请输入用户名"> <label class="control-label" for="addusername" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="adduserpwd" class="col-sm-3 control-label">密码</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="adduserpwd" required="required" name="password" placeholder="请输入密码"> <label class="control-label" for="adduserpwd" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="addname" class="col-sm-3 control-label">读者姓名</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" name="name" id="addname" required="required"  placeholder="请输入读者姓名">
                                    <label class="control-label" for="addname" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="addemail" class="col-sm-3 control-label">邮箱</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="addemail" required="required" name="email" placeholder="请输入邮箱">
                                    <label class="control-label" for="addemail" style="display: none;"></label>
                                </div>
                            </div>

                            <div class="form-group">
                                <label for="addphone" class="col-sm-3 control-label">手机号</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="addphone" required="required" name="phone" placeholder="请输入手机号">
                                    <label class="control-label" for="addphone" style="display: none;"></label>
                                </div>
                            </div>

                            <!---------------------表单-------------------->
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button type="submit" class="btn btn-primary">添加</button>
                        </div>
                    </div>
                    <!-- /.modal-content -->
                </div>
                <!-- /.modal -->
            </div>

        </form>

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
                                // if (data.flag) {
                                //     $("#updatepwd").modal("hide");//关闭模糊框
                                //     showInfo(data.errorMsg);
                                // } else {
                                $("#updatepwd").modal("hide");//关闭模糊框
                                showInfo(data.errorMsg);
                                // }
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
                                            $("#update_info").modal("hide");//关闭模糊框
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
</div>
</body>
</html>
