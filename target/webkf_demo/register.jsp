<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>register</title>
    <style type="text/css">
        #back {
            background-image: url('<%=request.getContextPath()%>/img/2019_08_26_78568_1566791538._large.jpg');
            background-size: 100%;
        }

        * {
            margin: 0 auto;
        }

        .register {
            background: #d1d5d6;
            width: 50%;
            height: auto;
            margin-top: 15%;
            padding: 30px;
            border-radius: 15px;
            opacity: .8;

        }

        a {
            text-decoration: none;
            color: white;
        }

        .put {
            text-align: center;
        }

        input {
            padding: 10px 15px;
            margin: 10px;
            font-size: 17px;
            border-radius: 7px;
        }

        #btn {
            height: 50px;
            border-radius: 7px;
            background: #343A40;
            color: white;
            margin-top: 50px;
            font-size: 25px;
        }

        #btn:hover {
            background: #565d63;
        }
    </style>
    <script>
        function register() {
            let uname = $("#uname");
            let pwd = $("#pwd").value;
            let pwd1 = $("#pwd1").value;
            if (pwd.eq(pwd1)){
                // window.location.href="";
            }else {
               layer.msg('两次密码不一致！');
            }
        }
    </script>
</head>
<body id="back"
      style="background-image: url('<%=request.getContextPath()%>/img/2019_08_26_78568_1566791538._large.jpg')">
<div class="register">
    <div style="text-align: center">
        <a href="#"><h3><b style="font-family: 华文隶书,serif;font-size: 70px">麒玖网络</b></h3></a>
        <a href="#"><h4><b>欢迎使用麒玖网络</b></h4></a>
    </div>
    <form action="#">
        <div class="put">
            <input  id="uname" type="text" style="width: 50%" placeholder="用户名"
                   onkeyup="this.value=this.value.replace(/[^\w_]/g,'')" name="username" >
        </div>
        <div class="put">
            <input id="pwd1" type="text" style="width: 50%" placeholder="新的密码" property="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"
                   onkeyup="this.value=this.value.replace(/[^\w_]/g,'')">
        </div>
        <div class="put">
            <input id="pwd" type="text" style="width: 50%" placeholder="确认密码" property="^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,10}$"
                   onkeyup="this.value=this.value.replace(/[^\w_]/g,'')" name="password">
        </div>

    </form>
    <div class="put">
        <button id="btn" style="width: 60%">注册</button>
    </div>
</div>

</body>
</html>