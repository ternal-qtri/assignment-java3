<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.home"/>

<%--Header--%>
<jsp:include page="header.jsp"/>
<jsp:include page="navbar.jsp"/>
<!-- Main  -->
<main class="container" style="height: 100px;">
    <div class="row mt-4 mb-5">
        <div class="col-9 left-box-content">
            <div class="trending-title">
                <p class="h3">- <fmt:message key="home.trending" /> -</p>
            </div>
            <div>
                <ul class="trending-post">
                    <c:forEach var="news" items="${homeNews}">
                        <li style="height: 140px;">
                            <a href="${pageContext.request.contextPath}/post-detail?id=${news.id}" class="row">
                                <div class="col-3">
                                    <img class="post-img"
                                         src="static/img/uploads/${news.image}"
                                         alt="${news.image}">
                                </div>
                                <div class="col-9">
                                    <p>${news.title}</p>
                                    <span class="author">${news.authorName}</span>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="col-3 right-box-content">
            <div class="top-posts mt-4 d-block">
                <div class="top-posts-box">
                    <p class="h5"> <fmt:message key="home.top"/> </p>
                    <div class="top5-posts">
                        <ul class="top-posts-list">
                            <c:forEach var="topNews" items="${topViews}">
                                <li><a href="${pageContext.request.contextPath}/post-detail?id=${topNews.id}">${topNews.title}</a></li>
                            </c:forEach>
                        </ul>
                    </div>
                    <hr />
                </div>
                <div class="top-posts-box">
                    <p class="h5"> <fmt:message key="home.latest"/> </p>
                    <div class="top5-posts">
                        <ul class="top-posts-list">
                            <c:forEach var="n" items="${latestNews}">
                                <li>
                                    <a href="${pageContext.request.contextPath}/post-detail?id=${n.id}">
                                        <c:choose>
                                            <c:when test="${fn:length(n.title) > 50}">
                                                ${fn:substring(n.title, 0, 50)}...
                                            </c:when>
                                            <c:otherwise>
                                                ${n.title}
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                    <hr />
                </div>
                <div class="top-posts-box">
                    <p class="h5"> <fmt:message key="home.viewed"/> </p>
                    <div class="top5-posts">
                        <ul class="top-posts-list">
                            <li><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit.</a></li>
                            <li><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit.</a></li>
                            <li><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit.</a></li>
                            <li><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit.</a></li>
                            <li><a href="#">Lorem ipsum dolor sit amet consectetur adipisicing elit.</a></li>
                        </ul>
                    </div>
                    <hr />
                </div>
                <div class="top-posts-box">
                    <p class="h5"> <fmt:message key="home.subscribe"/> </p>
                    <a class="subscribe" href="#" data-bs-toggle="modal" data-bs-target="#subscribeModal">
                        <i class="fa-regular fa-hand-point-right"></i>
                        <fmt:message key="home.subscribe.link"/>
                    </a>
                </div>
            </div>
        </div>
    </div>
</main>

<%--Form Newsletter--%>
<jsp:include page="formDialog.jsp"/>

<%--Footer--%>
<jsp:include page="footer.jsp"/>
