package com.itheima.web;

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(value = "/selectAllServlet")/*相当于导航的路径中的URL*/
public class SellectAllServlet extends HttpServlet {
    //只需要创建一次对象, 所以将对象创建放在类的外层
    BrandService brandService = new BrandService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1. 调用对应的BrandService中的方法完成查询
        List<Brand> brandList = brandService.selectAll();

        //2. 将集合存入到request域中
        request.setAttribute("brands", brandList);

        //3. 转发到brand.jsp页面
        request.getRequestDispatcher("/brand.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
