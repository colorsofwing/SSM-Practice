<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/1/30
  Time: 21:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div class="simpleSearchOutDiv">
    <a href="${contextPath}">
        <img src="img/site/simpleLogo.png" id="simpleLogo" class="simpleLogo">
    </a>

    <form method="post" action="foresearch">
        <div class="simpleSearchDiv pull-right">
            <input type="text" name="keyword" placeholder="平衡车 原汁机">
            <button type="submit" class="searchButton">搜天猫</button>
            <div class="searchBelow">
                <c:forEach items="${cs}" var="c" varStatus="st">
                    <c:if test="${st.count>=8 and st.count<=11}">
                        <span>
                            <a href="forecategory?cid=${c.id}">${c.name}</a>
                            <c:if test="${st.count!=11}">
                                <span>|</span>
                            </c:if>
                        </span>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </form>
    <div style="clear: both"></div>
</div>
