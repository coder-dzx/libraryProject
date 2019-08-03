package edu.hebtu.servlet;

import edu.hebtu.service.HistoryService;
import edu.hebtu.service.Impl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 11:12
 */
@WebServlet("/adminReturnServlet")
public class AdminReturnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1111);
        String barcode = request.getParameter("barcode");
        String username = request.getParameter("username");
        HistoryService service=new HistoryServiceImpl();
        service.returnBook(username,barcode);
        response.sendRedirect("admin_history.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
