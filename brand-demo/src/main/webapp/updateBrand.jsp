<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 24834
  Date: 2023/11/21
  Time: 16:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>修改品牌</title>
</head>
<body>
<h3>修改品牌</h3><%--只有将数据存储在request域中, 并且进行了数据的转发后才能使用el表达式--%>
<form action="/brand-demo/updateServlet" method="post"><%--使用EL表达式将从数据库查询到的数据在页面中显示, 需要使用标签的value属性来设置默认显示的值,在value的设置处使用EL表达式--%>

    <%--在表单中通过隐藏域提交id, 否则无法确定要修改的数据是属于数据库中的哪一行中的数据, 通过隐藏域可以确定要提交的数据属于哪一行的数据 --%>
    <input type="hidden" name="id" value="${brand.id}">

    品牌名称：<input name="brandName" value="${brand.brandName}"><br>
    企业名称：<input name="companyName" value="${brand.companyName}"><br>
    排序：<input name="ordered" value="${brand.ordered}"><br>
    描述信息：<textarea rows="5" cols="20" name="description">${brand.description}</textarea><br><%--在textarea文本域中如果需要写入默认的数值需要将这里的描述信息直接写在标签中间就行,这个标签似乎没有value属性--%>
    状态：<%--在这里需要使用c: if 判断这个单选标签是否被选中, 主要是判断value值是0还是1, 如果是0则标识禁用且被选中,如果是1则标识启用的单选被选中, 这里可以直接在条件中使用三目运算符, 但是使用三元运算符后显示的数据的长度会非常大--%>
    <c:if test="${brand.status == 0}">
        <input type="radio" name="status" value="0" checked>禁用
        <input type="radio" name="status" value="1">启用<br>
    </c:if>
    <c:if test="${brand.status == 1}">
        <input type="radio" name="status" value="0">禁用
        <input type="radio" name="status" value="1" checked>启用<br>
<%--    <input type="radio" name="status" value="0" checked>禁用&lt;%&ndash;在input-radio标签中填写属性check能够实现多选或者单选的默认选中状态&ndash;%&gt;--%>
<%--    <input type="radio" name="status" value="1">启用<br>--%>
    </c:if>
    <input type="submit" value="提交">
</form>
</body>
</html>