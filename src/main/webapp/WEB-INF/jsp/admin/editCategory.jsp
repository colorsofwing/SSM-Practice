<%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/1/18
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"
         pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<title>编辑分类</title>

<script>
    $(function () {
        $("#editForm").submit(function () {
            if(!checkEmpty("name","分类名称"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="/admin_category_list">所有分类</a></li>
        <li class="active">编辑分类</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑分类</div>
        <div class="panel-body">
            <form method="post" id="editForm" action="admin_category_update" enctype="multipart/form-data">
                <table class="editTable">
                    <tr>
                        <td>分类名称</td>
                        <td><input id="name" name="name" type="text" class="form-control" value="${c.name}"></td>
                    </tr>
                    <tr>
                        <td>分类图片</td>
                        <td><input id="categoryPic" name="image" type="file" accept="image/*"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${c.id}">
                            <button type="submit" class="btn btn-success">提  交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>

<%@ include file="../include/admin/adminFooter.jsp"%>
