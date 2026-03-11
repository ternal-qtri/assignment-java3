<%--
  Created by IntelliJ IDEA.
  User: QuocTriz
  Date: 1/6/2026
  Time: 7:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>
<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>


<script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
<c:if test="${not empty error}">
    <script>
        const msg = '${error}';
        if (msg) {
            Swal.fire({
                icon: 'error',
                title: 'Không thể xóa',
                text: 'Không thể xóa user đã có bài viết trong hệ thống!'
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
                <div class="col-12 btn-admin active">
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
            <div class="py-5 right-box-name d-flex justify-content-between">
                <p class="h3"><i class="fa-solid fa-circle-dot"></i> Quản lý người dùng</p>
                <a href="${pageContext.request.contextPath}/admin/user/create" class="btn btn-primary"><i class="fa-solid fa-plus"></i> Thêm</a>
            </div>
            <div>
                <table class="table table-striped table-hover">
                    <thead>
                    <tr class=" text-center">
                        <th class="py-2">#</th>
                        <th class="py-2">Username</th>
                        <th class="py-2">Họ tên</th>
                        <th class="py-2">Email</th>
                        <th class="py-2">Điện thoại</th>
                        <th class="py-2">Ngày sinh</th>
                        <th class="py-2">Giới tính</th>
                        <th class="py-2">Vai trò</th>
                        <th class="py-2">Trạng thái</th>
                        <th class="py-2">Ngày tạo</th>
                        <th class="py-2">Hành động</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="u" varStatus="st">
                        <tr class="text-center">
                            <th style="width: 50px;">${st.index + 1}</th>

                            <td>${u.username}</td>
                            <td>${u.fullname}</td>
                            <td>${u.email}</td>
                            <td>${u.phone}</td>
                            <td><fmt:formatDate value="${u.birthday}" pattern="dd/MM/yyyy"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.gender}">
                                        Nam
                                    </c:when>
                                    <c:otherwise>
                                        Nữ
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.role.description == 'Quản trị viên'}">
                                        <span class="badge bg-success fs-6">
                                            Quản trị viên
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-secondary fs-6">
                                            Phóng viên
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <c:choose>
                                    <c:when test="${u.status}">
                                        <span class="badge bg-success fs-6 table-active">
                                                Hoạt động
                                        </span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger fs-6">
                                                Tạm dừng
                                        </span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td><fmt:formatDate value="${u.birthday}" pattern="dd/MM/yyyy"/></td>
                            <td class="" style="width: 150px; text-align: center;">
                                <form action="${pageContext.request.contextPath}/admin/user/edit" method="get" class="d-inline">
                                    <input type="text" hidden="hidden" value="${u.id}" name="id">
                                    <button type="submit" class="btn d-inline-flex align-items-center"
                                            style="height: 30px; ">
                                        <!-- <i class="fa-regular fa-pen-to-square"></i> -->
                                        <svg xmlns="http://www.w3.org/2000/svg" width="15" height="15"
                                             viewBox="0 0 1025 1023">
                                            <path fill="#000000"
                                                  d="M896.428 1023h-768q-53 0-90.5-37.5T.428 895V127q0-53 37.5-90t90.5-37h576l-128 127h-384q-27 0-45.5 19t-18.5 45v640q0 27 19 45.5t45 18.5h640q27 0 45.5-18.5t18.5-45.5V447l128-128v576q0 53-37.5 90.5t-90.5 37.5zm-576-464l144 144l-208 64zm208 96l-160-159l479-480q17-16 40.5-16t40.5 16l79 80q16 16 16.5 39.5t-16.5 40.5z" />
                                        </svg>
                                    </button>
                                </form>

                                <form action="${pageContext.request.contextPath}/admin/user/delete" method="post" class="d-inline"
                                      onsubmit="return confirm('Xoá user ${u.fullname}?')">
                                    <input type="text" hidden="hidden" value="${u.id}" name="id">
                                    <button type="submit" class="btn d-inline-flex align-items-center"
                                            style="height: 30px;">
                                        <!-- <i class="fa-solid fa-xmark"></i> -->
                                        <svg width="15" height="15" xmlns="http://www.w3.org/2000/svg"
                                             viewBox="0 0 24 24">
                                            <path fill="#000000"
                                                  d="M19.45 7.5H4.55a.5.5 0 0 0-.5.54l1.28 14.14a2 2 0 0 0 2 1.82h9.34a2 2 0 0 0 2-1.82L20 8a.5.5 0 0 0-.5-.54Zm-9.2 13a.75.75 0 0 1-1.5 0v-9a.75.75 0 0 1 1.5 0Zm5 0a.75.75 0 0 1-1.5 0v-9a.75.75 0 0 1 1.5 0ZM22 4h-4.75a.25.25 0 0 1-.25-.25V2.5A2.5 2.5 0 0 0 14.5 0h-5A2.5 2.5 0 0 0 7 2.5v1.25a.25.25 0 0 1-.25.25H2a1 1 0 0 0 0 2h20a1 1 0 0 0 0-2M9 3.75V2.5a.5.5 0 0 1 .5-.5h5a.5.5 0 0 1 .5.5v1.25a.25.25 0 0 1-.25.25h-5.5A.25.25 0 0 1 9 3.75" />
                                        </svg>
                                    </button>
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