<%--
  Created by IntelliJ IDEA.
  User: QuocTriz
  Date: 1/6/2026
  Time: 3:15 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="../header.jsp"/>
<main class="container-fluid">
    <div class="row">
        <div class="col-2 left-box-admin">
        <div class=" btn-admin-box">
            <div class="col-12 btn-admin active">
                <a href="${pageContext.request.contextPath}/admin">
                    <i class="fa-solid fa-toolbox"></i> Trang quản lý
                </a>
            </div>
            <div class="col-12 btn-admin">
                <a href="${pageContext.request.contextPath}/admin/user">
                    <i class="fa-solid fa-user"></i> Người dùng
                </a>
            </div>
            <div class="col-12 btn-admin">
                <a href="${pageContext.request.contextPath}/admin/category">
                    <i class="fa-solid fa-list"></i> Loại tin tức
                </a>
            </div>
            <div class="col-12 btn-admin">
                <a href="${pageContext.request.contextPath}/admin/news">
                    <i class="fa-solid fa-newspaper"></i> Tin tức
                </a>
            </div>
            <div class="col-12 btn-admin">
                <a href="${pageContext.request.contextPath}/admin/newsletter">
                    <i class="fa-solid fa-envelope"></i> Tài khoản nhận tin
                </a>
            </div>
        </div>
    </div>
    <div class="col-10 p-0 right-box-admin">
        <div class="py-5 right-box-name">
            <p class="h3"><i class="fa-solid fa-circle-dot"></i> Trang quản lý</p>
        </div>
        <div class="box-control">
            <div class="h5 pt-3 ps-3">Tổng quan</div>
            <hr />
            <div class="row">
                <div class="box-control-item col-4 d-flex flex-column align-items-center">
                    <p>Người dùng</p>
                    <span class="text-primary">${totalUsers}</span>
                    <a href="${pageContext.request.contextPath}/admin/user">Xem tất cả</a>
                </div>
                <div class="box-control-item col-4  d-flex flex-column align-items-center">
                    <p>Quản trị viên</p>
                    <span class="text-success">${totalAdmins}</span>
                </div>
                <div class="box-control-item col-4  d-flex flex-column align-items-center">
                    <p>Phóng viên</p>
                    <span class="text-warning">${totalReporters}</span>
                </div>
                <div>
                    <hr />
                </div>
            </div>
        </div>
        <div class="box-control">
            <div class="row">
                <div class="box-control-item col-4 d-flex flex-column align-items-center">
                    <p>Loại tin tức</p>
                    <span class="text-danger">${totalCategories}</span>
                    <a href="${pageContext.request.contextPath}/admin/category">Xem tất cả</a>
                </div>
                <div class="box-control-item col-4  d-flex flex-column align-items-center">
                    <p>Tin tức</p>
                    <span class="text-info">${totalNews}</span>
                    <a href="${pageContext.request.contextPath}/admin/news">Xem tất cả</a>
                </div>
                <div class="box-control-item col-4  d-flex flex-column align-items-center">
                    <p>Tài khoản nhận tin</p>
                    <span class="text-body-secondary">${totalNewletters}</span>
                    <a href="${pageContext.request.contextPath}/admin/newsletter">Xem tất cả</a>
                </div>
                <div class="mb-4">
                    <hr />
                </div>
            </div>
        </div>
    </div>
    </div>
</main>