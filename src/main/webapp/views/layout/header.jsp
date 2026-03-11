<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 1/6/2026 Time: 2:26 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.header"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    const confirmLogout = () => {
        Swal.fire({
            title: '<fmt:message key="header.logout.confirm.title"/>',
            text: '<fmt:message key="header.logout.confirm.text"/>',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: '<fmt:message key="header.logout.confirm.ok"/>',
            cancelButtonText: '<fmt:message key="header.logout.confirm.cancel"/>'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = '${pageContext.request.contextPath}/logout';
            }
        });
    }
</script>

<!-- Header -->
<header style="background-color: #f2f2f2;">
    <div class="container d-flex justify-content-between align-items-center">
        <div class="logo-app d-flex justify-content-center">
            <a href="${pageContext.request.contextPath}/home">Blog App</a>
        </div>
        <nav class="navbar navbar-expand-lg bg-body-tertiary ms-4">
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <div class="navbar-nav">
                    <a class="nav-link" href="${pageContext.request.contextPath}/home">
                        <fmt:message key="header.home"/>
                    </a>
                    <div class="nav-item dropdown ps-4 fw-lighter">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="header.language"/>
                        </a>

                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item bg-transparent fs-6" href="?lang=vi">
                                    <fmt:message key="header.lang.vi"/>
                                </a>
                            </li>
                            <li>
                                <hr class="dropdown-divider">
                            </li>
                            <li>
                                <a class="dropdown-item bg-transparent fs-6" href="?lang=en">
                                    <fmt:message key="header.lang.en"/>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </nav>
        <div class="btn-login-box d-flex justify-content-center align-items-center">
            <c:choose>
<%--                Chưa đăng nhập--%>
                <c:when test="${empty sessionScope.user}">
                    <a class="button-80" href="${pageContext.request.contextPath}/login">
                        <fmt:message key="header.login"/>
                    </a>
                </c:when>

<%--                Đã đăng nhập--%>
                <c:otherwise>
                    <div class="dropdown">
                        <button class="btn btn-secondary dropdown-toggle"
                                type="button"
                                data-bs-toggle="dropdown"
                                aria-expanded="false">
                                ${sessionScope.user.fullname}
                        </button>

                        <ul class="dropdown-menu">
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin">
                                    <fmt:message key="header.admin"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/create-post">
                                    <fmt:message key="header.post"/>
                                </a>
                            </li>
                            <li>
                                <a class="dropdown-item text-danger pt-2"
                                   href="javascript:void(0)"
                                   onclick="confirmLogout()">
                                    <fmt:message key="header.logout"/>
                                </a>
<%--                                <a class="dropdown-item text-danger pt-2" href="${pageContext.request.contextPath}/logout"--%>
<%--                                   onclick="return confirm('Bạn có chắc chắn muốn đăng xuất không?')">--%>
<%--                                    Đăng xuất--%>
<%--                                </a>--%>
                            </li>
                        </ul>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</header>