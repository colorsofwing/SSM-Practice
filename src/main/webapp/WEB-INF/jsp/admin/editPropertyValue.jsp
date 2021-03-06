<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/1/23
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@include file="../include/admin/adminHeader.jsp"%>
<%@include file="../include/admin/adminNavigator.jsp"%>

<title>编辑产品属性值</title>

<script>
    $(function () {
        $("input.pvValue").keyup(function () {
            var value = $(this).val();
            var url = "admin_propertyValue_update";
            var id = $(this).attr("id");
            var parentSpan = $(this).parent("span");
            parentSpan.css("border","1px solid yellow");
            $.post(url,
                {"id":id,"value":value},
                function (result) {
                    if(result=="success"){
                        parentSpan.css("border","1px solid green");
                    }
                    else{
                        parentSpan.css("border","1px solid red");
                    }
                }
            );
        });
    })
</script>

<div class="workingArea">
    <ol class="breadcrumb">
        <li><a href="admin_category_list">所有分类</a></li>
        <li><a href="admin_product_list?cid=${p.category.id}">${p.category.name}</a></li>
        <li class="active">${p.name}</li>
        <li class="active">编辑产品属性</li>
    </ol>

    <div class="editPVDiv">
        <c:forEach items="${pvl}" var="pv">
            <div class="eachPV">
                <span class="pvName">${pv.property.name}</span>
                <span class="pvValue"><input type="text" class="pvValue" id="${pv.id}" value="${pv.value}" style="width: 198px"></span>
            </div>
        </c:forEach>
    </div>
</div>