package edu.hebtu.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
 * @date 2019/6/2 - 15:11
 */
@WebServlet("/adminUpdateBookServlet")
public class AdminUpdateBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String barcode = request.getParameter("card");
        String type = request.getParameter("type");
        String bookname = request.getParameter("name");
        String author = request.getParameter("author");
        String sum = request.getParameter("num");
        String place = request.getParameter("place");
        if(barcode==""||type==""||bookname==""||author==""||sum==""||place==""){
            ResultInfo info=new ResultInfo();
            info.setFlag(false);
            info.setErrorMsg("请将信息填写完整后再提交！");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            System.out.println("图书信息不完整");
            return;
        }
        BookService service=new BookServiceImpl();
        service.updateBook(barcode,type,bookname,author,sum,place);

        ResultInfo info=new ResultInfo();
        info.setFlag(true);
        info.setErrorMsg("修改成功");
        ObjectMapper mapper=new ObjectMapper();
        String json = mapper.writeValueAsString(info);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
