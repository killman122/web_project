package com.itheima.web;
import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/addServlet")
public class AddServlet extends HttpServlet {
    BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理post请求中的乱码问题 TODO 不知道为什么中文乱码问题在post处发送端处理,而不是在显示端
        request.setCharacterEncoding("UTF-8");

        //1. 获取请求参数
        String brandName = request.getParameter("brandName");
        String companyName = request.getParameter("companyName");
        String ordered = request.getParameter("ordered");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        //2. 接收表单提交的数据,封装成Brand对象
        Brand brand = new Brand();
        brand.setBrandName(brandName);
        brand.setCompanyName(companyName);
        brand.setOrdered(Integer.parseInt(ordered));
        brand.setDescription(description);
        brand.setStatus(Integer.valueOf(status));
//        brand.setStatus(Integer.parseInt(status));

        //3. 调用BrandService中的方法完成添加
        brandService.add(brand);//在Service包下提供add()方法将数据进行添加, 原本这个方法是直接在Servlet和Mapper中进行编写的, 现在将这个方法写在了Service中, 在Servlet中调用Service中的方法, 在Service中调用Mapper中的方法, 这样就将Servlet中的代码进行了简化, 降低了耦合度

        //4. 重定向到查询所有的Servlet
//        response.sendRedirect(request.getContextPath() + "electAllServlet");

        //转发到查询所有的Servlet
        request.getRequestDispatcher("/selectAllServlet").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
