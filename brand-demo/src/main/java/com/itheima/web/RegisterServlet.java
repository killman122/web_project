package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/registerServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户填写的用户名和密码信息, 因为在这里通过register.jsp页面中进行了表单的转发, 所以这里可以通过直接导入的方式进行数据的处理
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户输入的验证码, 这里由于使用Session的方式进行存储,所以直接使用request.getSession().getAttribute("check_code")方法获取session中的验证码信息
        String check_code = (String) request.getSession().getAttribute("check_code");

        //获取用户在网页前端的jsp页面的表单中输入的验证码, 再将用户在前端页面的jsp表单中输入的验证码和session中的验证码进行比较, 如果相同则进行用户的注册, 如果不同则提示用户验证码输入错误
        String checkCode = request.getParameter("checkCode");
        if (checkCode.equalsIgnoreCase(check_code)) {//用户的验证码和session中存储的验证码相同
            //调用Service层方法完成用户信息的保存和用户的注册
            UserService userService = new UserService();
            boolean register_info = userService.register(user);

            //判断注册成功与否并返回给前端页面中响应
            if (register_info) {//注册成功,跳转到登录页面
                request.setAttribute("register_msg", "注册成功, 请登录");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            } else {//注册失败, 返回为false则提示用户名存在并转发到注册页面中
                request.setAttribute("register_name", "用户名已存在");
                request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
        } else {//用户输入的验证码和seesion中存储的验证码不同
            request.setAttribute("check_code_msg", "验证码输入错误");
            request.getRequestDispatcher("/register.jsp").forward(request, response);

        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
