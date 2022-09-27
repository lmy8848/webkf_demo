<%--
  Created by IntelliJ IDEA.
  User: NJQ-PC
  Date: 2022/9/24
  Time: 13:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HomePage</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/main.css">
</head>
<body>
<%--<a href="<%=request.getContextPath()%>/main.do">Home</a>--%>
<form action="<%=request.getContextPath()%>/main.do" method="get">
    <input type="hidden" name="name" value="select">
    <input type="submit" value="查询">
</form>

</body>
</html>
