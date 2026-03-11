<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 1/7/2026 Time: 9:35 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<jsp:include page="header.jsp"/>
<main>
    <div class="container">
        <h1 class="h1 my-4">Đăng bài</h1>
        <form method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create">
            <div class="row mb-3">
                <div class="col-4 d-flex justify-content-center">
                    <img class="img-post"
                         src="https://i.pinimg.com/736x/af/a5/17/afa517e31c84fe6aa77f4a95bff22b78.jpg"
                         alt="avatar preview" id="avatarPreview" />
                </div>
                <div class="col-8">
                    <label class="form-label">Tiêu đề</label>
                    <input name="title" type="text" class="form-control" placeholder="Title"
                           value="">
                    <hr />
                    <label class="form-label">Nội dung</label>
                    <textarea name="content" class="form-control" style="height: 200px"></textarea>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-4"></div>
                <div class="col-4">
                    <label for="inputState" class="form-label">Thẻ loại</label>
                    <select name="categoryId" class="form-select">
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}">${c.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-4">
                    <label for="formFile" class="form-label">Ảnh đính kèm</label>
                    <input name="avatar" class="form-control" type="file" id="avatarFile"
                           accept=".png, .jpg, .jpeg, .webp">
                </div>
            </div>
            <div class="mb-3 d-flex justify-content-end">
                <a class="btn btn-secondary m-3" href="${pageContext.request.contextPath}/home">Hủy bỏ</a>
                <button type="submit" class="btn btn-primary m-3">Đăng bài</button>
            </div>
        </form>
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