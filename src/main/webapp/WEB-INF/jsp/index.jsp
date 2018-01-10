<%@ page import="java.util.List" %>
<%@ page import="lyons.entity.Goods" %><%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/1/8
  Time: 10:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>SSM框架</title>
</head>
<body>
    <h2>Hello,Wing</h2>
<c:forEach var="item" items="${list}" begin="0" end="${fn:length(list)}">
    序号：${item.getCommodity_number()}<br>
    名称：${item.getCommodity_name()}<br>
    价格：${item.getCommodity_price()}<br>
    库存：${item.getCommodity_balance()}<br>
    产地：${item.getCommodity_made()}<br>
    <hr>
</c:forEach>
    <a href="/html/index.html">验证</a>
<script>
    <%
    List<Goods> list = (List<Goods>) request.getAttribute("list");
    int num = list.size();
    %>
    //alert("<%=num%>");
</script>
</body>
</html>
