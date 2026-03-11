<%--Created by IntelliJ IDEA. User: QuocTriz Date: 17-02-2026 Time: 21:07--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid" style="padding: 0; margin: 0; border: 0;">
        <div class="collapse navbar-collapse bg-secondary-subtle py-2" id="navbarNav">
            <ul class="navbar-nav mx-auto">
                <c:forEach var="c" items="${categories}" varStatus="st">
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/post-detail?id=${news.id}">${c.name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>