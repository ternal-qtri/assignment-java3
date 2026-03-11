<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 12/31/2025 Time: 1:05 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>Trang chủ</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
          integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- Css -->
    <link rel="stylesheet" href="<c:url value='/static/css/main.css' />">

</head>
<body>
<div class="webapp container-fluid">
    <!-- Header -->
    <jsp:include page="header.jsp"/>
    <!-- Main  -->
    <main class="container">
        <div class="row">
            <div class="col-8 post-detail">
                <p class="mt-3 fs-6"><fmt:formatDate value="${news.postedDate}" pattern="dd/MM/yyyy"/></p>
                <h1 class="h3 pt-3 pb-1">${news.title}</h1>
                <img class="my-3" src="static/img/uploads/${news.image}"
                    alt="${news.title}">
                <p class="post-detail-text py-3" style="white-space: pre-line;">
                    ${news.content}
                </p>
                <div class="pb-5 pt-3">
                    <p class="fw-bold fs-5 text-end pe-5">${news.authorName}</p>
                </div>
            </div>
            <div class="col-4" style="height: 100%;">
                <div class="row author-post">
                    <p class="h4 p-2">Tác giả</p>
                    <div class="col-3">
                        <img class="author-img" src="${pageContext.request.contextPath}/static/img/uploads/${author.avatar}" alt="">
                    </div>
                    <div class="col-8 d-flex flex-row align-items-center">
                        <div class="author-info">
                            <a class="py-1 text-decoration-none d-flex flex-row align-items-center" href="${pageContext.request.contextPath}/author?id=${author.id}">
                                <p class="p-1 fs-5 ">${news.authorName}</p>
                                <i class="fa-regular fa-eye"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="related">
                    <p class="h4 p-2">Bài viết liên quan</p>
                    <c:forEach var="r" items="${relatedNews}">
                        <a class="related-post row text-decoration-none text-secondary my-2"
                           href="${pageContext.request.contextPath}/post-detail?id=${r.id}">

                            <div class="col-4">
                                <img class="related-post-img"
                                     src="${pageContext.request.contextPath}/static/img/uploads/${r.image}"
                                     alt="${r.title}">
                            </div>

                            <div class="col-8 related-posts d-flex flex-column justify-content-center">
                                <p class="fw-bold">
                                    <c:choose>
                                        <c:when test="${fn:length(r.title) > 60}">
                                            ${fn:substring(r.title, 0, 60)}...
                                        </c:when>
                                        <c:otherwise>
                                            ${r.title}
                                        </c:otherwise>
                                    </c:choose>
                                </p>
                                <div class="py-2">
                                        ${r.authorName}
                                    -
                                    <fmt:formatDate value="${r.postedDate}" pattern="dd/MM/yyyy"/>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </main>
    <%@ include file="footer.jsp"%>
</div>
<%@ include file="formDialog.jsp"%>
</body>
</html>
