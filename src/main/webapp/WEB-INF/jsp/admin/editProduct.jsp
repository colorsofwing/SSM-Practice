<%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/1/22
  Time: 14:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<title>编辑产品</title>

<script>
    $(function () {
        $("#editForm").submit(function () {
            if(!checkEmpty("product_name","产品名称"))
                return false;
            if(!checkEmpty("originalPrice","原价格"))
                return false;
            if(!checkEmpty("promotePrice","优惠价格"))
                return false;
            if(!checkEmpty("stock","库存"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.cid}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">编辑产品</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑产品</div>
        <div class="panel-body">
            <form method="post" action="admin_product_update" id="editForm">
                <table class="editTable">
                    <tr>
                        <td>产品名称</td>
                        <td><input type="text" id="product_name" name="name" class="form-control" value="${p.name}"></td>
                    </tr>
                    <tr>
                        <td>产品小标题</td>
                        <td><input type="text" id="subTitle" name="subTitle" class="form-control" value="${p.subTitle}"></td>
                    </tr>
                    <tr>
                        <td>原价格</td>
                        <td><input type="text" id="originalPrice" name="originalPrice" class="form-control" value="${p.originalPrice}"></td>
                    </tr>
                    <tr>
                        <td>优惠价格</td>
                        <td><input type="text" id="promotePrice" name="promotePrice" class="form-control" value="${p.promotePrice}"></td>
                    </tr>
                    <tr>
                        <td>库存</td>
                        <td><input type="text" id="stock" name="stock" class="form-control" value="${p.stock}"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="hidden" name="cid" value="${p.cid}">
                            <button type="submit" class="btn btn-success">提  交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>