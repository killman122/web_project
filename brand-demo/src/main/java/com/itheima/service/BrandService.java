package com.itheima.service;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

import static com.itheima.util.SqlSessionFactoryUtils.sqlSessionFactory;

public class BrandService {
    //在service层中创建的方法需要和在mapper中创建的Java抽象接口中的方法一致, 一般情况下在这里使用返回值类型等都和接口中的一致和接口的实现方法非常的相似
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //为了防止重复创建工厂在调用方法时, 所以直接将方法写在类的内层方法的外层

    /**
     * 查询所以
     *
     * @return
     */
    public List<Brand> selectAll() {
        //调用BrandMapper接口中的方法, 有点像是将原本写的Servlet中的方法写到了这里, 但是这里的方法是在接口中定义的, 在接口的实现类中实现的

        //2.获取对应的sqlSession,这里启用自动提交事务, 在openSession()方法中传入一个boolean类型变量并取值为true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.通过sqlSession获取对应的Mapper对象, 在这里是BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //4. 调用方法,将原来的通过servlet直接调用mapper中的方法改为了通过service调用mapper中的方法,在从servlet中调用service中的方法,这样就将servlet中的代码进行了简化降低了耦合度在这种类似三层架构的地方
        List<Brand> brandList = mapper.selectAll();
//        System.out.println(brandList);

        //关闭sqlSession
        sqlSession.close();

        return brandList;

    }

    /**
     * 添加品牌
     *
     * @param brand
     */
    public void add(Brand brand) {
        //1. 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //2. 获取对应的mapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3. 调用添加对应的方法
        mapper.add(brand);

        //4. 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 根据id查询品牌
     *
     * @param id
     * @return
     */
    public Brand selectById(Integer id) {
        //1. 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //2. 获取对应的mapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3. 调用对应的方法
        Brand brand = mapper.selectById(id);

        //4. 关闭sqlSession
        sqlSession.close();

        return brand;

    }

    /**
     * 根据id修改品牌
     *
     * @param brand
     */
    public void update(Brand brand) {
        //1. 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //2. 获取对应的mapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3. 调用对应的方法
        mapper.update(brand);

        //4. 关闭sqlSession
        sqlSession.close();
    }

    /**
     * 根据id删除品牌
     */
    public void delete(Integer id){
        //1. 获取sqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //2. 获取对应的mapper对象
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);

        //3. 调用对应的方法
        mapper.delete(id);

        //4. 关闭sqlSession
        sqlSession.close();
    }
}
