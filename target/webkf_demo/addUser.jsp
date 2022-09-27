<%--
  Created by IntelliJ IDEA.
  User: NJQ-PC
  Date: 2022/9/26
  Time: 17:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored = "false" %>
<% request.setCharacterEncoding("UTF-8");%>
<html>
<head>
    <title>添加用户</title>
    <meta charset="UTF-8">

<%--    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">--%>
    <link href="<%=request.getContextPath()%>/layui/layui/css/layui.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/layui/layui/layui.js"></script>
    <script src="jquery-3.5.1.js">
    </script>
</head>
<body>
<script src="<%=request.getContextPath()%>/layui/layui/layui.js"></script>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend><%=request.getParameter("title")%>
    </legend>
</fieldset>

<form action="<%=request.getContextPath()%>/main.do?name=${param.mark}" class="layui-form" method="post">
    <div class="layui-form-item">
        <label class="layui-form-label">用户ID</label>
        <div class="layui-input-block">
            <input type="text" name="userId" autocomplete="off" placeholder="请输入userId" class="layui-input" value="${param.userId}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">姓名</label>
        <div class="layui-input-block">
            <input type="text" name="addname" autocomplete="off" placeholder="请输入name" class="layui-input" value="${param.uname}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">英文名</label>
        <div class="layui-input-block">
            <input type="text" name="ename" autocomplete="off" placeholder="请输入ename" class="layui-input" value="${param.ename}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">身份证号码</label>
        <div class="layui-input-block">
            <input type="text" name="personId" autocomplete="off" placeholder="请输入personId" class="layui-input" value="${param.personId}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">单选框</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
            <input type="radio" name="sex" value="禁" title="禁用" disabled="">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">信用积分</label>
        <div class="layui-input-block">
            <input type="number" name="credit" autocomplete="off" placeholder="请输入credit" class="layui-input" value="${param.credit}">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">城市</label>
        <div class="layui-input-block">
            <input type="text" name="city" autocomplete="off" placeholder="请输入City" class="layui-input" value="${param.city}">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="demo1">立即提交</button>
            <button type="reset" class="layui-btn layui-btn-primary">重置</button>
        </div>
    </div>
</form>

<script>
    form.verify({
        userId: function(value){
            if(value.length < 2){
                return '标题至少得2个字符啊';
            }
        }
        ,personId: [
            /^[\S]{6,12}$/
            ,'密码必须6到12位，且不能出现空格'
        ]
    });
</script>
</body>
</html>
