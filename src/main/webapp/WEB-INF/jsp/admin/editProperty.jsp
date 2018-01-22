<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/1/21
  Time: 21:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/admin/adminHeader.jsp"%>
<%@ include file="../include/admin/adminNavigator.jsp"%>

<title>编辑属性</title>

<script>
    $(function () {
        $("#editForm").submit(function () {
            if(!checkEmpty("property_name","属性名称"))
                return false;
            return true;
        });
    });
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_property_list?cid=${p.cid}">${p.category.name}</a></li>
        <li>${p.name}</li>
        <li class="active">编辑属性</li>
    </ol>

    <div class="panel panel-warning editDiv">
        <div class="panel-heading">编辑属性</div>
        <div class="panel-body">
            <form method="post" action="admin_property_update" id="editForm">
                <table class="editTable">
                    <tr>
                        <td>属性名称</td>
                        <td><input type="text" id="property_name" name="name" class="form-control" value="${p.name}"></td>
                    </tr>
                    <tr class="submitTR">
                        <td colspan="2" align="center">
                            <input type="hidden" name="id" value="${p.id}">
                            <input type="hidden" name="cid" value="${p.cid}">
                            <button class="btn btn-success" type="submit">提  交</button>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </div>
</div>
