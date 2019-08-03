package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hebtu.domain.PageBean;
import edu.hebtu.domain.Reserve;
import edu.hebtu.domain.User;
import edu.hebtu.service.Impl.ReserveServiceImpl;
import edu.hebtu.service.ReserveService;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/9 - 15:20
 */
@WebServlet("/selectReserveServlet")
public class SelectReserveServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReserveService service=new ReserveServiceImpl();
        request.setCharacterEncoding("utf-8");
        String bookname = request.getParameter("book_name");
        System.out.println(bookname);
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr=request.getParameter("pageSize");
        HttpSession session=request.getSession();
        User user = (User)session.getAttribute("user");
        String username = user.getUsername();
        //当前页
        int currentPage=0;
        if(currentPageStr!=null && currentPageStr.length()>0){
            currentPage=Integer.parseInt(currentPageStr);
        }else{
            currentPage=1;
        }
        //显示条数
        int pageSize=0;
        if(pageSizeStr!=null&&pageSizeStr.length()>0){
            pageSize=Integer.parseInt(pageSizeStr);
        }else{
            pageSize=5;
        }
        PageBean<Reserve> pb=service.PageQuery(currentPage,pageSize,bookname,username);
        ObjectMapper mapper=new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),pb);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
