<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<jsp:include page="header.jsp"/>
<main class="container" style="min-height: 100px;">
    <div class="row mt-4 mb-5 d-flex justify-content-center">
        <div class="col-9 left-box-content">
            <div class="row author-post d-flex justify-content-center text-center my-3 mx-auto" style="width: 400px">
                <div class="col-3 d-flex justify-content-center my-3">
                    <img class="author-img"
                         src="${author.avatar != null
                        ? pageContext.request.contextPath.concat('/static/img/uploads/').concat(author.avatar)
                        : 'https://i.pinimg.com/150x150/default.jpg'}"
                         style="width: 150px; height: 150px;" alt="">
                </div>
                <p class="p-1 fs-5">${author.fullname}</p>
            </div>
            <div>
                <ul class="trending-post">
                    <c:forEach var="n" items="${posts}">
                        <li style="height:140px">
                            <a href="${pageContext.request.contextPath}/post?id=${n.id}" class="row">
                                <div class="col-3">
                                    <img class="post-img"
                                         src="${pageContext.request.contextPath}/static/img/uploads/${n.image}">
                                </div>
                                <div class="col-9">
                                    <p>${n.title}</p>
                                    <span class="author">${n.authorName}</span>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
        </div>
    </div>
</main>