<%--
  Created by IntelliJ IDEA.
  User: 24834
  Date: 2023/11/22
  Time: 8:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--jstl标签--%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>login</title>
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
<div id="loginDiv" style="height: 350px">
    <form action="/brand-demo/loginServlet" id="form" autocomplete="off"><%--这里的表单的信息都需要交给servlet中处理数据, 由于是浏览器的上的操作所以需要添加虚拟路径--%>
        <h1 id="loginMsg">LOGIN IN</h1>
        <div id="errorMsg">${msg} ${register_msg}</div>
        <p>Username:<input id="username" name="username" type="text" value="${cookie.cookie_username.value}"></p>
        <p>Password:<input id="password" name="password" type="password" value="${cookie.cookie_password.value}"></p>
        <p>Remember:<input id="remember" name="remember" type="checkbox"></p>
        <div id="subDiv">
            <input type="submit" class="button" value="login up">
            <input type="reset" class="button" value="reset">&nbsp;&nbsp;&nbsp;
            <a href="register.jsp">没有账号？</a>
        </div>
    </form>
</div>
</body>
</html>
