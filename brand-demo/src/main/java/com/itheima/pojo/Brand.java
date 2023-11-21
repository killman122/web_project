package com.itheima.pojo;

/**
 * 品牌实体类: 根据数据库中的数据的类型和组成构造Java中的实体类, 一般情况下包括有参构造器和无参构造器和set以及get方法以及重写的toString方法
 *
 * create table tb_brand
 * (
 *     -- id 主键
 *     id           int primary key auto_increment,
 *     -- 品牌名称
 *     brand_name   varchar(20),
 *     -- 企业名称
 *     company_name varchar(20),
 *     -- 排序字段
 *     ordered      int,
 *     -- 描述信息
 *     description  varchar(100),
 *     -- 状态：0：禁用  1：启用
 *     status       int
 * );
 * 一般情况下将数据库中的int类型数据转换为Java中的Integer类型,为了防止使用int类型数据在为构造赋值的时候默认值为0
 */

public class Brand {
    // id 主键
    private Integer id;
    // 品牌名称
    private String brandName;
    // 企业名称
    private String companyName;
    // 排序字段
    private Integer ordered;
    // 描述信息
    private String description;
    // 状态：0：禁用  1：启用
    private Integer status;


    public Brand() {
    }

    public Brand(Integer id, String brandName, String companyName, Integer ordered, String description, Integer status) {
        this.id = id;
        this.brandName = brandName;
        this.companyName = companyName;
        this.ordered = ordered;
        this.description = description;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getOrdered() {
        return ordered;
    }

    public void setOrdered(Integer ordered) {
        this.ordered = ordered;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", brandName='" + brandName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", ordered=" + ordered +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
