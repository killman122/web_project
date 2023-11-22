package com.itheima.service;

import com.itheima.mapper.UserMapper;
import com.itheima.pojo.User;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    //在service层中创建的方法需要和在mapper中创建的Java抽象接口中的方法一致, 一般情况下在这里使用返回值类型等都和接口中的一致和接口的实现方法非常的相似
    SqlSessionFactory sqlSessionFactory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //为了防止重复创建工厂在调用方法时, 所以直接将方法写在类的内层方法的外层

    /**
     * 登录方法
     *
     * @param username
     * @param password
     * @return
     */
    public User login(String username, String password) {
        //调用BrandMapper接口中的方法, 有点像是将原本写的Servlet中的方法写到了这里, 但是这里的方法是在接口中定义的, 在接口的实现类中实现的

        //2.获取对应的sqlSession,这里启用自动提交事务, 在openSession()方法中传入一个boolean类型变量并取值为true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.通过sqlSession获取对应的Mapper对象, 在这里是UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //调用方法
        User user = mapper.select(username, password);

        //关闭sqlSession,释放资源
        sqlSession.close();

        return user;
    }

    /**
     * 注册方法
     * @return
     */
    public boolean register(User user) {
        //调用BrandMapper接口中的方法, 有点像是将原本写的Servlet中的方法写到了这里, 但是这里的方法是在接口中定义的, 在接口的实现类中实现的

        //2.获取对应的sqlSession,这里启用自动提交事务, 在openSession()方法中传入一个boolean类型变量并取值为true
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        //3.通过sqlSession获取对应的Mapper对象, 在这里是UserMapper
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

       //4. 判断用户名是否存在于数据库中
        User u = mapper.selectByUsername(user.getUsername());

        if (u==null) {
            //表示用户名不存在于数据库中的表中, 实现注册功能
            mapper.add(user);
            //return true;
        }/*else {
            return false;
        }*/

        //关闭sqlSession对象
        sqlSession.close();
        return u==null;//在这里使用直接判断用来返回避免了通过if和else的嵌套实现的
    }
}