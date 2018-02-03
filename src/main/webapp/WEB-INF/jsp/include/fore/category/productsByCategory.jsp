<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/2/3
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<c:if test="${empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="100"/>
</c:if>

<c:if test="${!empty param.categorycount}">
    <c:set var="categorycount" scope="page" value="${param.categorycount}"/>
</c:if>

<div class="categoryProducts">
    <c:forEach items="${c.products}" var="p" varStatus="st">
        <c:if test="${st.count<=categorycount}">
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

                    <div class="show1 productInfo">
                        <span class="monthDeal">月成交<span class="productDealNumber">${p.salesCount}笔</span></span>
                        <span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
                        <span class="wangwang">
                            <a href="#nowhere" class="wangwanglink"><img src="img/site/wangwang.png"></a>
                        </span>
                    </div>
                </div>
            </div>
        </c:if>
    </c:forEach>
    <div style="clear: both"></div>
</div>