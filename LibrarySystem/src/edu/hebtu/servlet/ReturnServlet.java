package edu.hebtu.servlet;

import edu.hebtu.domain.Reserve;
import edu.hebtu.domain.User;
import edu.hebtu.service.HistoryService;
import edu.hebtu.service.Impl.HistoryServiceImpl;
import edu.hebtu.service.Impl.ReserveServiceImpl;
import edu.hebtu.service.ReserveService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/1 - 12:05
 */
@WebServlet("/returnServlet")
public class ReturnServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        String barcode = request.getParameter("barcode");
        HistoryService service=new HistoryServiceImpl();
        ReserveService reserveService=new ReserveServiceImpl();
        Reserve reserve=reserveService.findReserveBook(barcode);
        if(reserve==null){
            service.returnBook(username,barcode);
        }else{
            service.returnBook(username,barcode);
            String reservename = reserve.getUsername();
            service.borrowBook(barcode,reservename);
            reserveService.deleteReserve(reservename,barcode);
        }
        response.sendRedirect("history.jsp");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
