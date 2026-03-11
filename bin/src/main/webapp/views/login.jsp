<%--
  Created by IntelliJ IDEA.
  User: QuocTriz
  Date: 12/31/2025
  Time: 1:05 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/7.0.1/css/all.min.css"
          integrity="sha512-2SwdPD6INVrV/lHTZbO2nodKhrnDdJK9/kg2XD1r9uGqPo1cUbujc+IYdlYdEErWNu69gVcYgdxlmVmzTWnetw=="
          crossorigin="anonymous" referrerpolicy="no-referrer"/>
    <!-- Css -->
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css' />">
</head>
<body>
<div class="webapp container-fluid">
    <!-- Header -->
    <header style="background-color: #f2f2f2;">
        <div class="container d-flex justify-content-between align-items-center">
            <div class="logo-app d-flex justify-content-center">
                <a href="#">Blog App</a>
            </div>
            <nav class="navbar navbar-expand-lg bg-body-tertiary">
                <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-link" aria-current="page" href="#">Home</a>
                        <a class="nav-link" href="#">Features</a>
                        <a class="nav-link" href="#">Pricing</a>
                        <a class="nav-link" href="#">Pricing</a>
                    </div>
                </div>
            </nav>
            <div class="btn-login-box d-flex justify-content-center align-items-center">
                <!-- <a class="button-80" href="#">Đăng nhập</a> -->
                <div class="dropdown ">
                    <button class="user-login btn btn-secondary dropdown-toggle" type="button"
                            data-bs-toggle="dropdown" aria-expanded="false">
                        Quốc Trí
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">Trang quản lý</a></li>
                        <li><a class="dropdown-item" href="#">Trang đăng tin</a></li>
                        <li><a class="dropdown-item text-danger pt-2" href="#">Đăng xuất</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </header>

    <main class="row d-flex justify-content-center align-items-center">
        <div class="login-box-outside col-4">
            <div class="login-box">
                <div class="login-title d-flex flex-column align-items-center">
                    <label for="">Chào mừng trở lại</label>
                    <p>Hãy đăng nhập tài khoản của bạn</p>
                </div>
                <div class="login-form">
                    <form>
                        <div class="my-4">
                            <label for="exampleInputEmail1" class="form-label">Tên tài khoản</label>
                            <input name="username" type="text" class="form-control" id="exampleInputEmail1">
                        </div>
                        <div class="mb-4">
                            <label for="exampleInputPassword1" class="form-label">Mật khẩu</label>
                            <input name="password" type="password" class="form-control" id="exampleInputPassword1">
                        </div>
                        <div class="mb-3 form-check">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Lưu đăng nhập</label>
                        </div>
                        <button type="submit" class="btn-login">Đăng nhập</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>
