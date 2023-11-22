<%--
  Created by IntelliJ IDEA.
  User: 24834
  Date: 2023/11/22
  Time: 18:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>欢迎注册</title>
    <link href="css/register.css" rel="stylesheet">
</head>
<body>

<div class="form-div">
    <div class="reg-content">
        <h1>欢迎注册</h1>
        <span>已有帐号？</span> <a href="login.html">登录</a>
    </div>
    <form id="reg-form" action="/brand-demo/registerServlet" method="post">
        <table>
            <tr>
                <td>用户名</td>
                <td class="inputs">
                    <input name="username" type="text" id="username">
                    <br>
                    <span id="username_err" class="err_msg">${register_name}</span>
                </td>

            </tr>
            <tr>
                <td>密码</td>
                <td class="inputs">
                    <input name="password" type="password" id="password">
                    <br>
                    <span id="password_err" class="err_msg" style="display: none">密码格式有误</span><%--将display设置为none表示无法显示,设置为block表示块状显示,实际上也就可以显示了--%>
                </td>
            </tr>
            <tr>
                <td>验证码</td>
                <td class="inputs">
                    <input name="checkCode" type="text" id="checkCode">
                    <span id="checkCode_err" class="err_msg" style="display: none">验证码输入错误</span>
                    <img id="checkCodeImg" src="/brand-demo/checkCodeServlet">
                    <a href="#" id="changeImg">看不清？</a>
                </td>
            </tr>

        </table>
        <div class="buttons">
            <input value="注 册" type="submit" id="reg_btn">
        </div>
        <br class="clear">
    </form>

</div>

<script>
    document.getElementById("changeImg").onclick = function () {
        let milliseconds = new Date().getMilliseconds();//使用js中的Date类中的getMilliseconds()方法实现时间戳的获取
        //document.getElementById("checkCodeImg").src = "/brand-demo/checkCodeServlet?1";//由于这里访问图片的路径和原有的访问图片的路径一致, 所以图片在默认的情况下被缓存, 所以需将路径进行修改在请求的url后面增加参数等操作
        document.getElementById("checkCodeImg").src = "/brand-demo/checkCodeServlet?"+milliseconds;//由于这里访问图片的路径和原有的访问图片的路径一致, 所以图片在默认的情况下被缓存, 所以需将路径进行修改在请求的url后面增加参数等操作
    }

    document.getElementById("checkCodeImg").onclick = function (){
        document.getElementById("checkCodeImg").src = "/brand-demo/checkCodeServlet?"+new Date().getMilliseconds();
    }

    if ("${check_code_msg}"==="验证码输入错误"){/*这里的el表达式好像可以更改为*/
        // document.getElementById("checkCode_err").style.display = "block";
        alert("验证码输入错误");
    }
</script>
</body>
</html>
