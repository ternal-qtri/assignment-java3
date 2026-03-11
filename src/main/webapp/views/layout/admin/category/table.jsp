<%--
  Created by IntelliJ IDEA.
  User: QuocTriz
  Date: 1/6/2026
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<script>
    document.addEventListener("DOMContentLoaded", function () {
        const buttons = document.querySelectorAll(".btn-edit-category");

        buttons.forEach(btn => {
            btn.addEventListener("click", function () {
                document.querySelector("#edit-id").value = this.dataset.id;
                document.querySelector("#edit-name").value = this.dataset.name;
            });
        });
    });
</script>

<c:if test="${not empty error}">
    <script>
        const msg = '${error}';
        if (msg) {
            Swal.fire({
                icon: 'error',
                title: 'Lỗi thao tác',
                text: '${error}'
            });
        }
    </script>
</c:if>

<jsp:include page="../../header.jsp"/>
<main class="container-fluid">
    <div class="row">
        <div class="col-2 left-box-admin">
            <div class=" btn-admin-box">
                <div class="col-12 btn-admin">
                    <a href="${pageContext.request.contextPath}/admin">
                        <i class="fa-solid fa-toolbox"></i> Trang quản lý
                    </a>
                </div>
                <div class="col-12 btn-admin">
                    <a href="${pageContext.request.contextPath}/admin/user">
                        <i class="fa-solid fa-user"></i> Người dùng
                    </a>
                </div>
                <div class="col-12 btn-admin active">
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
            <div class="py-5 right-box-name d-flex justify-content-between">
                <p class="h3"><i class="fa-solid fa-circle-dot"></i> Quản lý loại tin tức</p>
                <a href="#" class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#subscribeModal"><i class="fa-solid fa-plus"></i> Thêm</a>
            </div>
            <div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr class=" text-center">
                        <th class="px-4 py-2">#</th>
                        <th class="px-4 py-2">Mã loại</th>
                        <th class="px-4 py-2">Tên loại</th>
                        <th class="px-4 py-2">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="c" items="${categories}" varStatus="st">
                        <tr class="text-center">
                            <th style="width:50px">${st.index + 1}</th>
                            <td>${c.id}</td>
                            <td>${c.name}</td>
                            <td style="width:250px">
                                <form action="${pageContext.request.contextPath}/admin/category/edit" method="post" class="d-inline">
                                    <input type="hidden" name="id" value="${c.id}">
                                    <button type="button"
                                            class="btn btn-sm btn-edit-category"
                                            data-bs-toggle="modal"
                                            data-bs-target="#subscribeModal2"
                                            data-id="${c.id}"
                                            data-name="${c.name}">
                                        Sửa
                                    </button>
                                </form>

                                <form action="${pageContext.request.contextPath}/admin/category/delete" method="post"
                                      class="d-inline"
                                      onsubmit="return confirm('Xoá loại ${c.name}?')">
                                    <input type="hidden" name="id" value="${c.id}">
                                    <button class="btn btn-sm text-danger">Xoá</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</main>

<%--Form category--%>
<jsp:include page="form.jsp"/>
<jsp:include page="edit.jsp"/>