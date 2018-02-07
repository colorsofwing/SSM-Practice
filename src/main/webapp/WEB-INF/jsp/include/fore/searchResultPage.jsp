<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/2/3
  Time: 21:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div id="searchResult">

    <div class="searchResultDiv">

        <div class="searchProducts">

            <c:forEach items="${ps}" var="p">
                <div class="productUnit" price="${p.promotePrice}">
                    <div class="productUnitFrame">

                        <a href="foreproduct?pid=${p.id}">
                            <img src="img/productSingle_middle/${p.imageId}.jpg" class="productImage">
                        </a>
                        <span class="productPrice">
                        ¥<fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/>
                        </span>
                        <a class="productLink" href="foreproduct?pid=${p.id}">
                                ${fn:substring(p.name,0,21)}
                        </a>

                        <a class="tmallLink" href="foreproduct?pid=${p.id}">
                            天猫专卖
                        </a>

                        <div class="productInfo">
                            <span class="monthDeal">月成交<span class="productDealNumber">${p.salesCount}笔</span></span>
                            <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                            <span class="wangwang"><img src="img/site/wangwang.png"></span>
                        </div>

                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty ps}">
                <div class="noMatch">没有满足条件的产品</div>
            </c:if>

            <div style="clear: both"></div>

        </div>

    </div>

</div>