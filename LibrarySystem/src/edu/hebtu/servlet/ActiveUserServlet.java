package edu.hebtu.servlet;

import edu.hebtu.service.Impl.UserServiceImpl;
import edu.hebtu.service.UserService;
import org.omg.PortableInterceptor.RequestInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 17:53
 */
@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if(code!=null){
            UserService service=new UserServiceImpl();
            boolean flag=service.active(code);
            String msg=null;
            if(flag){
                //激活成功
                msg="激活成功，请<a href='login.jsp'>登陆</a>";
            }else {
                //激活失败
                msg="<script language='javascript'>alert('激活失败，请联系管理员');</script>";

            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
