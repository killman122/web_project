<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%--引入对应的taglib标签库, 对应的是jstl/core, 对写入的JSTL标签进行解析--%>
<%--标签中前缀的名称任意编写, 但是一般情况下将标签写为c标签, 这里的标签名称对应的属性是prefix, uri属性引入jstl标签库的位置--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<input type="button" value="新增" id="add"><br><%--这里为按钮设置id的作用是可以通过id标识按键再通过js代码实现提交表单--%>
<hr>
<table border="1" cellspacing="0" width="80%">
    <tr>
        <th>序号</th>
        <th>品牌名称</th>
        <th>企业名称</th>
        <th>排序</th>
        <th>品牌介绍</th>
        <th>状态</th>
        <th>操作</th>

    </tr>

    <%--c: forEach 标签中在items属性中填写需要遍历的数据集, var指的是遍历过程中的临时变量, 在使用EL表达式的时候需要向${}中写入变量并且方法为get方法(brand.getId())不能直接填写属性(brand.id)--%>
    <%--这里的id等属性是对应数据库中的属性也是对应于Java中实体类中的属性, 是成员this.id中设置的属性不一定是成员变量的值, 所以在填写id等属性后, 自动转换为大写并通过get方法调用属性返回--%>
    <%--理论上直接调用属性和调用get方法是等效的, 但是在实际的操作中,直接使用属性是更好的选择, 使用get方法还是会存在个体的异常, 所以推荐使用属性的方式直接访问--%>
    <%--属性varStatus指的是遍历过程中的序号索引, varStatus的值为status在下面的代码中, varStatus.index指的是遍历过程中的序号(并且是从0开始计数), status.count指的是遍历过程中的总数从1开始计数--%>
    <c:forEach items="${brands}" var="brand" varStatus="status">
        <tr align="center">
            <td>${status.count}</td>
<%--            <td>${brand.id}</td>--%>
            <td>${brand.brandName}</td>
                <%--            <td>${brand.getCompanyName}</td>--%>
            <td>${brand.companyName}</td>
            <td>${brand.getOrdered()}</td>
                <%--            <td>${brand.getDescription}</td>--%>
            <td>${brand.description}</td>
            <td>${brand.status==1?"启用":"禁用"}</td>
                <%--这里的判断条件可以直接使用三元运算符, 或者是拆解成c:if 语句--%>
                <%--            <td>启用</td>--%>
            <%--<c:if test="${brand.status == 1}">
                <td>启用</td>
            </c:if>
            <c:if test="${brand.status != 1}">
                <td>禁用</td>
            </c:if>--%>
            <td><a href="/brand-demo/selectByIdServlet?id=${brand.id}">修改</a> <a href="/brand-demo/deleteByIdServlet?id=${brand.id}">删除</a></td><%--写在foreach循环中为了每个数据中的行都对应于一个可操作数据--%>
        </tr>
    </c:forEach>

</table>

<script>
    document.getElementById("add").onclick = function () {
        location.href = "/brand-demo/addBrand.jsp";
    }
</script>
</body>
</html>
