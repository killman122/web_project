package com.itheima.web;

import com.itheima.pojo.User;
import com.itheima.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 获取请求参数, 接收用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");//当复选框被选中时通过request对象获取的参数不为空

        //2. 调用service层中的方法, 根据用户名和密码查询用户对象
        UserService userService = new UserService();
        User login_user = userService.login(username, password);

        //3. 判断User是否为空, 为null则表示数据库中无该用户信息
        if (login_user == null) {//登录失败
            //3.1 如果为空, 则表示用户名或密码错误, 跳转到登录页面, 并提示错误信息
            //将错误信息存储到request域中,再通过请求转发的方式将request域中的信息传送到需要进行反馈的jsp页面中 这里不能使用session, 因为session是会话级别的, 会话级别的信息会在会话结束后自动清空
            request.setAttribute("msg", "用户名或密码错误");
            //跳转到login.jsp页面中, 使用请求转发的方式, 因为再request域中存储的数据只能使用转发的方式实现获取
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {//登录成功, 跳转到查询所有的BrandServlet
            //3.2 如果不为空, 则表示用户名和密码正确, 判断是否勾选了记住密码
            if (remember != null) {//if("1".equals(remember))
                //3.2.1 如果勾选了记住密码, 则将用户名和密码存入cookie中
                Cookie cookie_username = new Cookie("cookie_username", username);
                Cookie cookie_password = new Cookie("cookie_password", password);
                //设置cookie的持久化时间
                cookie_username.setMaxAge(60 * 60 * 24 * 7);
                cookie_password.setMaxAge(60 * 60 * 24 * 7);
                //设置cookie的携带路径
                cookie_username.setPath(request.getContextPath());
                cookie_password.setPath(request.getContextPath());
                //将cookie写入到客户端
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }
            //3.2.2 如果没有勾选记住密码, 则将cookie中的信息清空
            else {
                Cookie cookie_username = new Cookie("cookie_username", "");
                Cookie cookie_password = new Cookie("cookie_password", "");
                //设置cookie的持久化时间
                cookie_username.setMaxAge(0);
                cookie_password.setMaxAge(0);
                //设置cookie的携带路径
                cookie_username.setPath(request.getContextPath());
                cookie_password.setPath(request.getContextPath());
                //将cookie写入到客户端
                response.addCookie(cookie_username);
                response.addCookie(cookie_password);
            }

            //3.3 将登录成功的用户信息存入session中
            HttpSession session = request.getSession();
            session.setAttribute("login_user", login_user);//TODO 似乎这里的重定向也能够实现request信息转发保存的功能???好像没有,是直接使用session保持会话的功能

            //3.4 跳转到首页
            response.sendRedirect(request.getContextPath() + "/selectAllServlet");//request.getContextPath()获取虚拟路径
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
