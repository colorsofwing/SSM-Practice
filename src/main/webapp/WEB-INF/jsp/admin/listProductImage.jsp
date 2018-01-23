<%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/1/23
  Time: 10:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<title>产品图片管理</title>

<script>
    $(function () {
        $(".addFormSingle").submit(function () {
            if(!checkEmpty("filepathSingle","图片文件"))
                return false;
            return true;
        });
        $(".addFormDetail").submit(function () {
            if(!checkEmpty("filepathDetail","图片文件"))
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
        <li class="active">产品图片管理</li>
    </ol>

    <table class="addPictureTable" align="center">
        <tr>
            <td class="addPictureTableTD">
                <div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                            <tr class="success">
                                <th>ID</th>
                                <th>产品单个图片缩略图</th>
                                <th>删除</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${pi_s}" var="pic">
                                <tr>
                                    <td>${pic.id}</td>
                                    <td>
                                        <a title="点击查看原图" href="img/productSingle/${pic.id}.jpg"><img src="img/productSingle/${pic.id}.jpg" height="50px"></a>
                                    </td>
                                    <td>
                                        <a deleteLink="true" href="admin_productImage_delete?id=${pic.id}"><span class="glyphicon glyphicon-trash"></span></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary">单个</b> 图片</div>
                        <div class="panel-body">
                            <form class="addFormSingle" action="admin_productImage_add" method="post" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 尺寸400X400 为佳</td>
                                    </tr>
                                    <tr>
                                        <td align="center"><input id="filepathSingle" name="image" type="file" accept="image/*"></td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="pid" value="${p.id}">
                                            <input type="hidden" name="type" value="type_single">
                                            <button type="submit" class="btn btn-success">提  交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </td>
            <td class="addPictureTableTD">
                <div>
                    <table class="table table-striped table-bordered table-hover  table-condensed">
                        <thead>
                        <tr class="success">
                            <th>ID</th>
                            <th>产品详情图片缩略图</th>
                            <th>删除</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${pi_d}" var="pic">
                            <tr>
                                <td>${pic.id}</td>
                                <td>
                                    <a title="点击查看原图" href="img/productDetail/${pic.id}.jpg"><img src="img/productDetail/${pic.id}.jpg" height="50px"></a>
                                </td>
                                <td>
                                    <a deleteLink="true" href="admin_productImage_delete?id=${pic.id}"><span class="glyphicon glyphicon-trash"></span></a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="panel panel-warning addPictureDiv">
                        <div class="panel-heading">新增产品<b class="text-primary">详情</b> 图片</div>
                        <div class="panel-body">
                            <form class="addFormDetail" action="admin_productImage_add" method="post" enctype="multipart/form-data">
                                <table class="addTable">
                                    <tr>
                                        <td>请选择本地图片 宽度790 为佳</td>
                                    </tr>
                                    <tr>
                                        <td align="center"><input id="filepathDetail" name="image" type="file" accept="image/*"></td>
                                    </tr>
                                    <tr class="submitTR">
                                        <td align="center">
                                            <input type="hidden" name="pid" value="${p.id}">
                                            <input type="hidden" name="type" value="type_detail">
                                            <button type="submit" class="btn btn-success">提  交</button>
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </div>
                    </div>
                </div>
            </td>
        </tr>
    </table>
</div>

<%@ include file="../include/admin/adminFooter.jsp"%>