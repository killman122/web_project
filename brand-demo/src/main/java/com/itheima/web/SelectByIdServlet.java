package com.itheima.web;

import com.itheima.pojo.Brand;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/selectByIdServlet")
public class SelectByIdServlet extends HttpServlet {
    //通过工具类获取SqlSessionFactory工厂
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();

    //创建一个BrandService对象
    BrandService brandService = new BrandService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接收id
        String id = request.getParameter("id");

        //通过service查询数据
        Brand brand = brandService.selectById(Integer.valueOf(id));/*这里使用parseInt()的方式也能够将数据从字符串转换为int*/

        //存储到request中,之后这里的数据会被转发到update.jsp中在通过getAttribute()方法获取,在这里转发后的数据的名字是第一个参数取名为brand
        request.setAttribute("brand",brand);

        //转发数据到update.jsp中, 在转发后使用getxx方法进行存储的进行获取吗?还是直接通过el表达式进行获取?
        request.getRequestDispatcher("/updateBrand.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
