<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<% request.setCharacterEncoding("UTF-8");%>
<% response.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link href="<%=request.getContextPath()%>/layui/layui/css/layui.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/layui/layui/layui.js"></script>
    <script src="jquery-3.5.1.js" type="text/javascript" charset="utf-8"></script>
    <script src="<%=request.getContextPath()%>/utils/tools.js"></script>
    <style>
        #login {
            width: 100%;
            height: auto;
        }
        a{
           text-decoration: none;
            color: #6abdf6;
        }

        .input {
            height: 35px;
            width: 350px;
            border-radius: 10px;
        }

        #login_form {
            padding: 30px;
            border-radius: 15px;
            width: 30%;
            background-color: #d1d5d6;
            border: 5px red;
            opacity: .9;

        }

        .commit {
            background-color: #72c9d6;
            margin-top: 100px;
            width: 200px;
            font-family: 楷体,serif;
            font-size: 30px;
            height: 50px;
            border-radius: 10px;
            text-shadow: 5px 6px 7px dimgrey;

        }

        .text {
            font-weight: bold;
            font-style: italic;
            text-align: left;
        }

        button:link {
            background-color: #72c9d6;
        }

        button:visited {
            background-color: aquamarine;
        }

        button:hover {
            background-color: #8c7fff;
        }

        button:active {
            background-color: coral;
        }

        .logo {
            color: #0ed78b;
            font-size: 40px;
            text-shadow: 10px 10px 10px #6abdf6;
        }

        #body_bg {
            background-size: 100%;
            background-image: url("/img/123.jpg");
        }

        #bg {
            width: 200px;
            height: 200px;
        }

        .copyright {
            color: #f50000;
            font-weight: bold;
            font-size: 30px;
        }
    </style>
</head>
<body id="body_bg">
<div id="login" align="center">
    <h1 class="logo">用户登录</h1>
    <h1 class="computer"><a href="#"><img id="bg" src="/img/computer.png" alt=""></a></h1>
    <form action="<%=request.getContextPath()%>/main.do?name=login" id="login_form" name="userform" method="post">
        <p>
            <lable class="text">用户名</lable>
            <br>
            <input type="text" class="input" name="username" maxlength="18" onkeyup="this.value=this.value.replace(/[^\w_]/g,'')" oninput="if(value.length>18)value=value.slice(0,18)">
            <!--            /*-->
            <!--            *property="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"-->
            <!--            */-->
        </p>
        <p>
            <lable class="text">密码</lable>
            <br>
            <input type="text" class="input" onkeyup="this.value=this.value.replace(/[^\w_]/g,'')" name="password" property="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$" onkeyup="this.value=this.value.replace(/[^\w_]/g,'')" oninput="if(value.length>22)value=value.slice(0,22)">
        </p>
        <p style="display: inline"><a href="register.jsp"><h3>还没有账号？去注册</h3></a></p>
        <p><input type="button" class="commit" value="登录" onclick=javascript:f()></p>
    </form>

    <h5 class="copyright">${param.err}</h5>
</div>
<script type="text/javascript" language="JavaScript">
    function f() {
        $("#login_form").submit();
    }
</script>

</body>
</html>