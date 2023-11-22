package com.itheima.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet(value = "/bServlet")
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取cookie, 使用request对象调用get方法获取并返回一个Cookie类型的数组
        Cookie[] cookies = request.getCookies();

        //遍历数组获取数组中的每一个cookie
        for (Cookie cookie : cookies) {
            //获取cookie中的数据
//            System.out.println("页面中的cookie值为"+cookie.getName()+"----"+cookie.getValue());
            if (cookie.getName().equals("username")) {

                //在获取到对应的cookie后进行解码
                String decode_date = cookie.getValue();
                String decode = URLDecoder.decode(decode_date, "utf-8");

                System.out.println("cookie中的数据为"+cookie.getName()+"----"+decode);
                break;//当查找到需要的数据后直接退出for循环
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
