package edu.hebtu.servlet;

import edu.hebtu.service.BookService;
import edu.hebtu.service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/2 - 18:51
 */
@WebServlet("/adminDeleteTypeServlet")
public class AdminDeleteTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typename = request.getParameter("typename");
        BookService service=new BookServiceImpl();
        service.deleteType(typename);
        response.sendRedirect("admin_booktype.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
