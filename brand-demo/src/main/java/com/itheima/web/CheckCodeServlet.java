package com.itheima.web;

import com.itheima.util.CheckCodeUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * 在这里实现调用验证码的工具类实现生成验证码
 */
@WebServlet(value = "/checkCodeServlet")
public class CheckCodeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //使用response的字节输出流实现将生成的图片文件发送的前端的jsp页面中显示
        //生成验证码主要使用工具类CheckCodeUtil中的验证方法outputVerifyImage()方法中进行
        ServletOutputStream outputStream = response.getOutputStream();
        String check_code = CheckCodeUtil.outputVerifyImage(100, 50, outputStream, 4);
        System.out.println(check_code);

        //将验证码存入session中为后期实现验证码的校验做准备, 但是在这里需要注意的是, 在这里存入的验证码是在生成验证码的工具类中生成的, 所以在这里需要将生成验证码的工具类导入到这里
        //也可以不将创建的验证码存入session中, 直接在生成验证码的工具类中返回生成的验证码, 然后在这里直接将生成的验证码返回给前端页面中,或者是通过js或者是在Java后台(这里是在RegisterServlet的Servlet)中进行验证码的校验
        HttpSession session = request.getSession();//获取session对象, 主要是通过-使用request对象中的getSession()方法获取session对象
        session.setAttribute("check_code", check_code);//将验证码的信息传入到指定的session名称的session的value中,在这里的session取名为check_code

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
