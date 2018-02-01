<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/2/1
  Time: 23:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<title>${p.name}</title>

<div class="categoryPictureInProductPageDiv">
    <img src="img/category/${p.category.id}.jpg" class="categoryPictureInProductPage">
</div>

<div class="productPageDiv">
    <%@include file="imgAndInfo.jsp" %>
    <%@include file="productReview.jsp" %>
    <%@include file="productDetail.jsp" %>
</div>

