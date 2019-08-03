package edu.hebtu.servlet;

import edu.hebtu.domain.History;
import edu.hebtu.domain.User;
import edu.hebtu.service.HistoryService;
import edu.hebtu.service.Impl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/5/31 - 14:39
 */
@WebServlet("/borrowServlet")
public class BorrowServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String barcode = request.getParameter("barcode");
        HttpSession session=request.getSession();
        User user =(User) session.getAttribute("user");
        String username = user.getUsername();
        HistoryService service=new HistoryServiceImpl();
        int sum=service.findBorrowByUsername(barcode,username);
        if(sum!=0)
        {
         response.getWriter().write("<script>alert('你已借阅此书，无法继续借阅!');window.location='select.jsp';</script>");
        }else{
            service.borrowBook(barcode,username);
            response.sendRedirect("borrow.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
