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
            <div class="py-5 right-box-name">
                <p class="h3"><i class="fa-solid fa-circle-dot"></i> Quản lý người dùng</p>
            </div>
            <div class="row d-flex justify-content-center" style="width: 100%">
                <div class="col-8">
                    <c:if test="${not empty error}">
                        <div class="alert alert-danger">
                                ${error}
                        </div>
                    </c:if>
                    <form action="${pageContext.request.contextPath}/admin/user/save" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="action" value="create">
                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="form-label">Họ tên</label>
                                <input name="fullname" type="text" class="form-control" placeholder="Full name" required>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Email</label>
                                <input name="email" type="email" class="form-control" placeholder="Email" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-6">
                                <label class="form-label">Tên đăng nhập</label>
                                <input name="username" type="text" class="form-control" placeholder="Username" required>
                            </div>
                            <div class="col-6">
                                <label class="form-label">Mật khẩu</label>
                                <input name="password" type="password" class="form-control" placeholder="Password" required>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-4">
                                <label class="form-label">Số điện thoại</label>
                                <input name="phone" type="text" class="form-control" placeholder="Phone number" required>
                            </div>
                            <div class="col-4">
                                <label for="role" class="form-label">Vai trò</label>
                                <select name="role" id="role" class="form-select">
                                    <c:forEach var="r" items="${roles}">
                                        <option value="${r.toDb()}">
                                            ${r.description}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-4">
                                <label class="form-label">Ngày sinh</label>
                                <input name="birthday" type="date" class="form-control" placeholder="Your birthday">
                            </div>
                        </div>
                        <div class="row mb-3 position-relative" style="max-height: 118px;">
                            <div class="col-4" style="height: 100px">
                                <label for="formFile" class="form-label">Giới tính</label>
                                <div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio1"
                                               value="true">
                                        <label class="form-check-label" for="inlineRadio1">Nam</label>
                                    </div>
                                    <div class="form-check form-check-inline">
                                        <input class="form-check-input" type="radio" name="gender" id="inlineRadio2"
                                               value="false">
                                        <label class="form-check-label" for="inlineRadio2">Nữ</label>
                                    </div>
                                </div>
                            </div>
                            <div class="col-4">
                                <label for="formFile" class="form-label">Avatar</label>
                                <input name="avatar" class="form-control" type="file" id="avatarFile"
                                       accept=".png, .jpg, .jpeg, .webp">
                            </div>
                            <div class="avatar-wrapper col-4 d-flex justify-content-center">
                                <img class="avatar-img rounded-pill"
                                     src="https://i.pinimg.com/1200x/b3/c2/77/b3c2779d6b6195793b72bf73e284b3e8.jpg"
                                     alt="avatar preview" id="avatarPreview" />
                            </div>
                        </div>
                        <div class="mb-3 zindex-toast">
                            <button type="submit" class="btn btn-primary">Tạo mới</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>

<script>
    const input = document.getElementById("avatarFile");
    const preview = document.getElementById("avatarPreview");

    input.onchange = () => {
        if (input.files && input.files[0]) {
            preview.src = URL.createObjectURL(input.files[0]);
        }
    };
</script>