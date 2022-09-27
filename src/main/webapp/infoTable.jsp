<%@ page import="java.util.List" %>
<%@ page import="com.crud.webkf.bean.User" %>
<%@page isELIgnored = "false" %>
<%--
  Created by IntelliJ IDEA.
  User: NJQ-PC
  Date: 2022/9/24
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>信息表</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
    <link href="<%=request.getContextPath()%>/layui/layui/css/layui.css" rel="stylesheet">
    <script src="<%=request.getContextPath()%>/layui/layui/layui.js">
    </script>
    <script src="jquery-3.5.1.js">
    </script>
    <script>
        function del(o,id) {
            console.log(id);
            const element = o.parentElement.parentElement;
            const number = parseInt(element.id.replace("item", ""));
            window.location.reload();
            window.location.href="<%=request.getContextPath()%>/main.do?name=delete&id="+id;
        }

        function addUser(o) {
            window.location.href="<%=request.getContextPath()%>/addUser.jsp?title=添加用户&mark=add";
        }

        function checkbtn(o) {

        }
        function allcheckbtn(o) {

        }

        function Previous() {
            window.location.href="<%=request.getContextPath()%>/main.do?page=${param.page}&name=Previous";
        }

        function next() {
            window.location.href="<%=request.getContextPath()%>/main.do?page=${param.page}&name=limitquery";
        }
    </script>
</head>
<body>
<script src="<%=request.getContextPath()%>/layui/layui/layui.js">

</script>
<% int size = 0; %>

<%
    List<User> list = (List<User>) request.getAttribute("list");
%>
<form action="#" method="post" id="userlist">
    <table class="layui-table">
        <tr id="item<%=size++%>">
            <th><input type="checkbox" onclick="allcheckbtn(this)">全选</th>
            <th><b>编号</b></th>
            <th>userID</th>
            <th>name</th>
            <th>ename</th>
            <th>personID</th>
            <th>sex</th>
            <th>credit</th>
            <th>city</th>
            <th><b>操作</b></th>

        </tr>
        <% for (User user : list) {%>
        <tr id="item<%=size++%>">
            <td><input type="checkbox" id="check<%=size-1%>" onclick="checkbtn(this)"></td>
            <td><b></b><%=size-1%></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getUserId()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getName()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getEname()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getPersonId()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getSex()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getCredit()%>"></td>
            <td><input class="data<%=size-1%>" disabled="disabled" type="text" value="<%=user.getCity()%>"></td>
            <td>
                <input  class="layui-btn" type="button" value="修改" onclick="x(this)">
                <input  class="layui-btn" type="button" value="删除" onclick="del(this,<%=user.getUserId()%>)">
            </td>
        </tr>

        <% } %>
    </table>
</form>
<form id="field" action="<%=request.getContextPath()%>/addUser.jsp?title=用户信息修改" method="post">
    <input id="field1" type="hidden" name="userId" value="">
    <input id="field2" type="hidden" name="uname" value="">
    <input id="field3" type="hidden" name="ename" value="">
    <input id="field4" type="hidden" name="personId" value="">
    <input id="field5" type="hidden" name="sex" value="">
    <input id="field6" type="hidden" name="credit" value="">
    <input id="field7" type="hidden" name="city" value="">
    <input id="field8" type="hidden" name="mark" value="">
</form>
<div>
    <button type="button" class="layui-btn" onclick="Previous()">
        <i class="layui-icon layui-icon-left"></i>
    </button>
    <span>第${param.page==null?"1":param.page}页/共<%=request.getAttribute("pagesize")%>页</span>
    <button type="button" class="layui-btn" onclick="next()">
        <i class="layui-icon layui-icon-right"></i>
    </button>
    <button class="layui-btn layui-btn-lg" onclick="addUser(this)">添加用户</button>
</div>

</body>
<script>
    let nodeListOf = document.querySelectorAll("input");
    for (let x of nodeListOf) {
        x.disable=true;
    }
    function x(o) {
        <% request.setCharacterEncoding("UTF-8");%>
        const element = o.parentElement.parentElement;

        const number = parseInt(element.id.replace("item", ""));
        let elementsByClassName = document.getElementsByClassName("data" + number);
        let i;
        for (i = 0; i <elementsByClassName.length; i++) {
            document.getElementById("field" + (i+1)).value=elementsByClassName[i].value;
        }
        document.getElementById("field" + (i+1)).value="update";
        field.submit();
    }
</script>
</html>
