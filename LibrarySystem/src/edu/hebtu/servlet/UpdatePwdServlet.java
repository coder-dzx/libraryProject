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
 * @date 2019/5/25 - 8:56
 */
@WebServlet("/updatePwdServlet")
public class UpdatePwdServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session=request.getSession();
        User user =(User) session.getAttribute("user");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        if(password2.equals(password)){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("新密码与原密码相同！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            System.out.println(json);
            return;
        }
        UserService service=new UserServiceImpl();
        boolean flag = service.updatePwd(user.getUsername(), password, password2);
        if(!flag){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("原密码错误！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            System.out.println(json);
            return;
        }

        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setErrorMsg("修改成功！");
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
