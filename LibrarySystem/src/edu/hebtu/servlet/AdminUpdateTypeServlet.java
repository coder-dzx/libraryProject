package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hebtu.dao.BookDao;
import edu.hebtu.domain.ResultInfo;
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
 * @date 2019/6/2 - 18:16
 */
@WebServlet("/adminUpdateTypeServlet")
public class AdminUpdateTypeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String typename = request.getParameter("typename");
        String typeid = request.getParameter("typeid");
        if(typename==""){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("请输入分类名称后再提交！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        BookService service=new BookServiceImpl();
        service.updateType(typename,typeid);
        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setErrorMsg("类型修改成功");
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
