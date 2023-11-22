package com.itheima.web;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(value = "/aServlet")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //发送cookie

        String value = "张三";
        //进行编码
        String encode_date = URLEncoder.encode(value, "utf-8");

        //1. 创建Cookie对象
        Cookie cookie = new Cookie("username",encode_date);

        //设置cookie的存活时间, 比如说cookie存一周
        cookie.setMaxAge(60*60*24*7);

        //2. 发送cookie
        response.addCookie(cookie);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
