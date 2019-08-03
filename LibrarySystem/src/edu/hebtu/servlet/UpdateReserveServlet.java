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

/**
 * @author DuZengXin
 * @date 2019/6/9 - 16:08
 */
@WebServlet("/updateReserveServlet")
public class UpdateReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        ReserveService service=new ReserveServiceImpl();
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        String barcode = request.getParameter("barcode");
        service.deleteReserve(username,barcode);
        response.sendRedirect("reserve.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
