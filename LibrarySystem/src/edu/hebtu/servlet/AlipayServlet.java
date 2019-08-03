package edu.hebtu.servlet;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import edu.hebtu.util.AlipayConfig;
import edu.hebtu.util.UuidUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author DuZengXin
 * @date 2019/6/17 - 15:24
 */
@WebServlet("/alipayServlet")
public class AlipayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.GETWAY, AlipayConfig.APP_ID, AlipayConfig.APP_PRIVATE_KEY, "json", "UTF-8", AlipayConfig.ALIPAY_PUBLIC_KEY, "RSA2"); //获得初始化的AlipayClient

        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();//创建API对应的request
        //支付完成之后跳转到自己的项目中某一个页面
        String barcode = request.getParameter("barcode");
        String username = request.getParameter("username");
        String time = request.getParameter("time");
        double day = Math.ceil(Double.parseDouble(time)/86400000);//查看超期天数，不够一天按一天计算
        double amount=day*3;//超期一天收费3元
        if(username==null){
            alipayRequest.setReturnUrl("http://localhost/returnServlet?barcode="+barcode);
            alipayRequest.setNotifyUrl("http://localhost/borrow.jsp");//在公共参数中设置回跳和通知地址
        }else{
            alipayRequest.setReturnUrl("http://localhost/adminReturnServlet?barcode="+barcode+"&username="+username);
            alipayRequest.setNotifyUrl("http://localhost/admin_borrow.jsp");//在公共参数中设置回跳和通知地址
        }
        //支付完成以后异步通知的url

        String uuid = UuidUtil.getUuid();
        alipayRequest.setBizContent("{" +
                "    \"out_trade_no\":\""+uuid+"\"," +
                "    \"product_code\":\"FAST_INSTANT_TRADE_PAY\"," +
                "    \"total_amount\":"+amount+"," +
                "    \"subject\":\"【河北师范大学图书管理系统】超期缴费\"," +
                "    \"body\":\"收费标准：(3元/天)\"," +
                "    \"passback_params\":\"merchantBizType%3d3C%26merchantBizNo%3d2016010101111\"," +
                "    \"extend_params\":{" +
                "    \"sys_service_provider_id\":\"2088511833207846\"" +
                "    }"+
                "  }");//填充业务参数
        String form="";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
