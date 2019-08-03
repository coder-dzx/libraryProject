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
 * @date 2019/5/25 - 10:39
 */
@WebServlet("/updateInfoServlet")
public class UpdateInfoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String name = request.getParameter("name");
        String phone=request.getParameter("phone");
        String email=request.getParameter("email");
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        UserService service=new UserServiceImpl();
        service.updateInfo(name,phone,email,username);
        ResultInfo info=new ResultInfo();
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
