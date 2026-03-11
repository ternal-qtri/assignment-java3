<%--
  Created by IntelliJ IDEA.
  User: QuocTriz
  Date: 12/31/2025
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.login"/>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${not empty error}">
    <script>
        const msg = '${error}';
        if (msg) {
            Swal.fire({
                icon: 'error',
                title: '<fmt:message key="login.error.title"/>',
                text: '${error}'
            });
        }
    </script>
</c:if>

<jsp:include page="header.jsp"/>
<main class="row d-flex justify-content-center align-items-center m-0">
    <div class="login-box-outside col-4">
        <div class="login-box">
            <div class="login-title d-flex flex-column align-items-center">
                <label for=""> <fmt:message key="login.welcome"/> </label>
                <p> <fmt:message key="login.subtitle"/> </p>
            </div>
            <div class="login-form">
                <form action="${pageContext.request.contextPath}/login" method="post">
                    <div class="my-4">
                        <label for="exampleInputEmail1" class="form-label">
                            <fmt:message key="login.username"/>
                        </label>
                        <input name="username" type="text" class="form-control" id="exampleInputEmail1">
                    </div>
                    <div class="mb-4">
                        <label for="exampleInputPassword1" class="form-label">
                            <fmt:message key="login.password"/>
                        </label>
                        <input name="password" type="password" class="form-control" id="exampleInputPassword1">
                    </div>
                    <div class="mb-3 form-check">
                        <input type="checkbox" class="form-check-input" id="remember" name="remember">
                        <label class="form-check-label" for="remember">
                            <fmt:message key="login.remember"/>
                        </label>
                    </div>
                    <button type="submit" class="btn-login">
                        <fmt:message key="login.submit"/>
                    </button>
                </form>
            </div>
        </div>
    </div>
</main>