package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hebtu.domain.ResultInfo;
import edu.hebtu.domain.User;
import edu.hebtu.service.Impl.UserServiceImpl;
import edu.hebtu.service.UserService;
import sun.org.mozilla.javascript.internal.Function;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.LinkedTransferQueue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author DuZengXin
 * @date 2019/6/16 - 16:49
 */
@WebServlet("/adminAddUserServlet")
public class AdminAddUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultInfo info=new ResultInfo();
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");
        if(!username.matches("^\\w{3,6}$")||!password.matches("^\\w{6,20}$")||!email.matches("^\\w+@\\w+\\.\\w+$")||!phone.matches("^1[3|4|5|7|8][0-9]{9}$")||!name.matches("^[\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,10}$")){
            if(!username.matches("^\\w{3,6}$")){
                info.setFlag(false);
                info.setErrorMsg("请输入正确的用户名");
            }else if(!password.matches("^\\w{6,20}$")){
                info.setFlag(false);
                info.setErrorMsg("请输入正确的密码");
            }else if(!name.matches("^[\\u4E00-\\u9FA5\\uf900-\\ufa2d·s]{2,10}$")){
                info.setFlag(false);
                info.setErrorMsg("请输入正确的姓名");
            }else if(!email.matches("^\\w+@\\w+\\.\\w+$")){
                info.setFlag(false);
                info.setErrorMsg("请输入正确的邮箱");
            }else if(!phone.matches("^1[3|4|5|7|8][0-9]{9}$")){
                info.setFlag(false);
                info.setErrorMsg("请输入正确的手机号");
            }
        }else{
            User user=new User();
            user.setUsername(username);
            user.setName(name);
            user.setEmail(email);
            user.setPhone(phone);
            user.setPassword(password);
            UserService service=new UserServiceImpl();
            boolean flag=service.addUser(user);

            if(flag){
                info.setFlag(true);
                info.setErrorMsg("添加成功");
            }else{
                info.setFlag(false);
                info.setErrorMsg("添加失败");
            }
        }
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);

        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
