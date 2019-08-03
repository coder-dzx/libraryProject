package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hebtu.domain.ResultInfo;
import edu.hebtu.domain.User;
import edu.hebtu.service.Impl.UserServiceImpl;
import edu.hebtu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/5/20 - 14:07
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String check = request.getParameter("check");
        HttpSession session=request.getSession();
        String checkcode = (String)session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkcode==null||!check.equalsIgnoreCase(checkcode)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("验证码错误！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserService service=new UserServiceImpl();
        User user=service.login(username,password);

        ResultInfo info=new ResultInfo();

        //判断用户是否存在
        if(user==null){
            info.setFlag(false);
            info.setErrorMsg("用户名或密码输入错误，请重新输入！");
        }
        //判断用户是否激活
        if(user!=null && "N".equals(user.getStatus())){
            info.setFlag(false);
            info.setErrorMsg("请激活后登陆！");
        }
        //用户存在且激活成功
        if(user!=null&&"Y".equals(user.getStatus())){
            info.setFlag(true);
            info.setErrorMsg(String.valueOf(user.getGrade()));
            request.getSession().setAttribute("user",user);
        }

        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),info);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
