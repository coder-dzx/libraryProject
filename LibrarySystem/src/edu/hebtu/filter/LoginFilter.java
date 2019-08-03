package edu.hebtu.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author DuZengXin
 * @date 2019/6/18 - 14:18
 */
/*
敏感词汇过滤器
 */
@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter( ServletRequest req,  ServletResponse resp,  FilterChain chain) throws ServletException, IOException {
        //强制转换
        HttpServletRequest request = (HttpServletRequest) req;
        //获取资源请求路径
        String uri = request.getRequestURI();
        //判断是否包含登陆相关资源路径，要注意排除掉 css/js图片/验证码等资源
        if(uri.contains("/login.jsp")||uri.contains("/loginServlet")||uri.contains("/register.jsp")||uri.contains("/registerServlet")||uri.contains("/css/")||uri.contains("/js/")||uri.contains("/fonts/")||uri.contains("/checkCode")){
            //包含，用户就是想登陆。
            chain.doFilter(req, resp);
        }else{
            //不包含，需要验证用户是否登陆
            //3.从session中获取user
            Object user = request.getSession().getAttribute("user");
            if(user !=null){
                //用户已经登陆放行
                chain.doFilter(req, resp);
            }else{
                //没有登陆。跳转登陆页面
                request.setAttribute("login_msg","您尚未登陆，请登录");
                request.getRequestDispatcher("/login.jsp").forward(req,resp);

            }
        }
    }


    public void init(FilterConfig config) throws ServletException {

    }

}
