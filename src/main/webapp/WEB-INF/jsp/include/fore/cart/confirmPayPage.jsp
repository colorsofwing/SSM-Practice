<%--
  Created by IntelliJ IDEA.
  User: 中船浙江
  Date: 2018/2/7
  Time: 15:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div class="confirmPayPageDiv">
    <div class="confirmPayImageDiv">
        <img src="img/site/comformPayFlow.png">
        <div class="confirmPayTime1">
            <fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
        </div>
        <div class="confirmPayTime2">
            <fmt:formatDate value="${o.payDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
        </div>
        <div class="confirmPayTime3">
            yyyy-MM-dd HH:mm:ss
        </div>
    </div>

    <div class="confirmPayOrderInfoDiv">
        <div class="confirmPayOrderInfoText">我已收到货，同意支付宝付款</div>
    </div>

    <div class="confirmPayOrderItemDiv">
        <div class="confirmPayOrderItemText">订单信息</div>
        <table class="confirmPayOrderItemTable">
            <thead>
            <th colspan="2">宝贝</th>
            <th width="120px">单价</th>
            <th width="120px">数量</th>
            <th width="120px">商品总价</th>
            <th width="120px">运费</th>
            </thead>
            <tbody>
            <c:forEach items="${o.orderItems}" var="oi" varStatus="st">
                <tr>
                    <td><img width="50px" src="img/productSingle_middle/${oi.product.imageId}.jpg"></td>
                    <td class="confirmPayOrderItemProductLink">
                        <a href="#nowhere">${oi.product.name}</a>
                    </td>
                    <td class="orderItemProductInfoPartTD" width="100px">
                        <div class="orderListItemProductOriginalPrice">
                            ¥<fmt:formatNumber type="number" value="${oi.product.originalPrice}"
                                               minFractionDigits="2"></fmt:formatNumber>
                        </div>
                        <div class="orderListItemProductPrice">
                            ¥<fmt:formatNumber type="number" value="${oi.product.promotePrice}"
                                               minFractionDigits="2"></fmt:formatNumber>
                        </div>
                    </td>
                    <td>${oi.number}</td>
                    <c:if test="${st.count==1}">
                        <td rowspan="${fn:length(o.orderItems)}">
                            <div>¥<fmt:formatNumber minFractionDigits="2" type="number" value="${o.total}"/></div>
                        </td>
                        <td rowspan="${fn:length(o.orderItems)}"><span>快递 ： 0.00 </span></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div class="confirmPayOrderItemText pull-right">
            实付款： <span class="confirmPayOrderItemSumPrice">¥<fmt:formatNumber type="number" value="${o.total}" minFractionDigits="2"/></span>
        </div>
    </div>

    <div class="confirmPayOrderDetailDiv">

        <table class="confirmPayOrderDetailTable">
            <tr>
                <td>订单编号：</td>
                <td>${o.orderCode}<img width="23px" src="img/site/confirmOrderTmall.png"></td>
            </tr>
            <tr>
                <td>卖家昵称：</td>
                <td>天猫商铺 <span class="confirmPayOrderDetailWangWangGif"></span></td>
            </tr>
            <tr>
                <td>收货信息：</td>
                <td>${o.address},${o.receiver},${o.mobile},${o.post}</td>
            </tr>
            <tr>
                <td>成交时间：</td>
                <td><fmt:formatDate value="${o.createDate}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
            </tr>
        </table>

    </div>

    <div class="confirmPayButtonDiv">
        <div class="confirmPayWarning">请收到货后，再确认收货！否则您可能钱货两空！</div>
        <a href="foreorderConfirmed?oid=${o.id}">
            <button class="confirmPayButton">确认支付</button>
        </a>
    </div>
</div>