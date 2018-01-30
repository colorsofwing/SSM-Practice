<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/1/28
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<nav class="top">
    <div class="top_middle">
        <a href="${contextPath}">
            <span class="glyphicon glyphicon-home redColor" style="color: #c40000;margin: 0px"></span>
            天猫首页
        </a>

        <span>喵，欢迎来到天猫</span>

        <c:choose>
            <c:when test="${!empty user}">
                <a href="login">${user.name}</a>
            </c:when>
            <c:otherwise>
                <a href="login">请登录</a>
            </c:otherwise>
        </c:choose>

        <c:choose>
            <c:when test="${!empty user}">
                <a href="forelogout">退出</a>
            </c:when>
            <c:otherwise>
                <a href="register">免费注册</a>
            </c:otherwise>
        </c:choose>

        <span class="pull-right">
        <a href="forebought">我的订单</a>
        <a href="forecart">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
            购物车<strong>${cartTotalItemNumber}</strong>件
        </a>
        </span>
    </div>
</nav>
