package edu.hebtu.servlet;

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
 * @date 2019/6/16 - 16:29
 */
@WebServlet("/adminDeleteUserServlet")
public class AdminDeleteUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        UserService service=new UserServiceImpl();
        service.deleteUser(username);
        response.sendRedirect("admin_user.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
