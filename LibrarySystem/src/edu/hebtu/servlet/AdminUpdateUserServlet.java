package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hebtu.domain.ResultInfo;
import edu.hebtu.service.Impl.UserServiceImpl;
import edu.hebtu.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/16 - 15:49
 */
@WebServlet("/adminUpdateUserServlet")
public class AdminUpdateUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        if(username==""||name==""||email==""||phone==""){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("请将信息填写完整后再提交！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            System.out.println("用户信息不完整");
            return;
        }
        UserService service=new UserServiceImpl();
        service.updateUserInfo(username,name,email,phone);
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setErrorMsg("修改成功");
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
