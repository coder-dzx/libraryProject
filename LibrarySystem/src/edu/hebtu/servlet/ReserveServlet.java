package edu.hebtu.servlet;

import edu.hebtu.domain.User;
import edu.hebtu.service.Impl.ReserveServiceImpl;
import edu.hebtu.service.ReserveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.interfaces.RSAKey;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 14:27
 */
@WebServlet("/reserveServlet")
public class ReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        String barcode = request.getParameter("barcode");
        String type = request.getParameter("type");
        String bookname = request.getParameter("bookname");
        String author = request.getParameter("author");
        String place = request.getParameter("place");
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        ReserveService service=new ReserveServiceImpl();
        int count=service.selectBook(barcode);
        if(count!=0){
            response.getWriter().write("<script>alert('已有人预约此书，请等待!');window.location='select.jsp';</script>");
            System.out.println("已有人预约此书，请等待");
        }else{
            service.ReserveBook(barcode,type,bookname,author,place,username);
            response.sendRedirect("reserve.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
