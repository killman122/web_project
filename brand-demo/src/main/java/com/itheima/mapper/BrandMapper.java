package com.itheima.mapper;

import com.itheima.pojo.Brand;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface BrandMapper {

    /**
     * 因为只是简单的查询所以直接使用注解的方式编写SQL语句
     *
     * @return
     */
    @Select("select * from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    /**
     * 添加品牌,由于使用的sql语句较为简单所以直接使用注解的方式进行编写
     *
     * @param brand
     */
//    @Insert("insert into tb_brand(brand_name, company_name, ordered, description, status) values(#{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    @Insert("insert into tb_brand values(null,#{brandName}, #{companyName}, #{ordered}, #{description}, #{status})")
    void add(Brand brand);

    /**
     * 根据id查询品牌, 由于需要查询的语句不是非常复杂所以在这里依旧是使用注解的方式根据id查询数据, 数据在获取显示到页面的过程中还是存在获取为空的情况
     * 原因是数据库中的字段名称和在Java实体类中的属性名称不一致, 所以在这里需要使用@Result注解进行映射
     *
     * @param id
     * @return
     */
    @ResultMap("brandResultMap")
    @Select("select * from tb_brand where id = #{id}")
    Brand selectById(Integer id);

    /**
     * 根据id修改品牌, 由于需要修改的语句不是非常复杂所以在这里依旧是使用注解的方式根据id修改数据, 注意在这些mapper中使用的Mybatis中的占位符有两种一种使用$符号一种使用#, 其中使用$符号的可能存在SQL注入的问题,所以一般情况下推荐使用#符号
     * 但是在使用EL表达式的时候必须使用$符号, 所以在这里需要注意
     *
     * @param brand
     */
    @Update("update tb_brand set brand_name = #{brandName}, company_name = #{companyName}, ordered = #{ordered}, description = #{description}, status = #{status} where id = #{id}")
    void update(Brand brand);

    /**
     * 根据id删除品牌, 由于需要删除的语句不是非常复杂所以在这里依旧是使用注解的方式根据id删除数据
     */
    @Delete("delete from tb_brand where id = #{id}")
    void delete(Integer id);
}
