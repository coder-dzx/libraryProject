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
 * @date 2019/6/2 - 16:56
 */
@WebServlet("/adminDeleteBookServlet")
public class AdminDeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String barcode = request.getParameter("barcode");
        BookService service=new BookServiceImpl();
        service.deleteBook(barcode);
        response.sendRedirect("admin_book.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
