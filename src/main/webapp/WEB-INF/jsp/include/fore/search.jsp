<%--
  Created by IntelliJ IDEA.
  User: Wing
  Date: 2018/1/28
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" isELIgnored="false" %>

<div class="simpleSearchOutDiv">
    <a href="${contextPath}">
        <img src="img/site/logo.gif" id="logo" class="logo">
    </a>

    <form method="post" action="foresearch">
        <div class="searchDiv">
            <input name="keyword" type="text" value="${param.keyword}" placeholder="黑框眼镜 裤腰带">
            <button type="submit" class="searchButton">搜索</button>
            <div class="searchBelow">
                <c:forEach items="${cs}" var="c" varStatus="st">
                    <c:if test="${st.count>=5 and st.count<=8}">
                    <span>
                        <a href="forecategory?cid=${c.id}">
                                ${c.name}
                        </a>
                        <c:if test="${st.count!=8}">
                            <span>|</span>
                        </c:if>
                    </span>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </form>
</div>

