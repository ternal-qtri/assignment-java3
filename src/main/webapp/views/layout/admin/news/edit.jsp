<%-- Created by IntelliJ IDEA. User: QuocTriz. Date: 1/7/2026 Time: 9:35 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="fn" uri="jakarta.tags.functions" %>

<jsp:include page="../../header.jsp"/>
<main>
    <div class="container">
        <h1 class="h1 my-4">Đăng bài</h1>
        <form action="${pageContext.request.contextPath}/admin/news/update" method="post" enctype="multipart/form-data">
            <input type="hidden" name="id" value="${news.id}">
            <div class="row mb-3">
                <div class="col-4 d-flex justify-content-center">
                    <img class="img-post"
                         src="${news.image != null ? pageContext.request.contextPath.concat('/static/img/uploads/').concat(news.image) : 'https://i.pinimg.com/736x/af/a5/17/afa517e31c84fe6aa77f4a95bff22b78.jpg'}"
                         alt="avatar preview" id="avatarPreview" />
                </div>
                <div class="col-8">
                    <label class="form-label">Tiêu đề</label>
                    <input name="title" type="text" class="form-control" placeholder="Title"
                           value="${news.title}">
                    <hr />
                    <label class="form-label">Nội dung</label>
                    <textarea name="content" class="form-control" style="height: 200px">${news.content}</textarea>
                </div>
            </div>
            <div class="row mb-3">
                <div class="col-4"></div>
                <div class="col-3">
                    <label for="inputState" class="form-label">Thẻ loại</label>
                    <select name="categoryId" class="form-select">
                        <c:forEach var="c" items="${categories}">
                            <option value="${c.id}" ${c.id == news.categoryId ? 'selected' : ''}>
                                    ${c.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col-3">
                    <label for="formFile" class="form-label">Ảnh đính kèm</label>
                    <input name="image" class="form-control" type="file" id="avatarFile"
                           accept=".png, .jpg, .jpeg, .webp">
                </div>
                <div class="col-2 row text-center">
                    <label class="form-label col-8">Trạng thái</label>
                    <div class="col-12 d-flex justify-content-start">
                        <div class="checkbox-wrapper-8">
                            <input type="checkbox" name="isHome" value="true" id="edit-enabled" class="tgl tgl-skewed" ${news.home ? 'checked' : ''}>
                            <label for="edit-enabled" data-tg-on="Trang chủ" data-tg-off="Ẩn" class="tgl-btn "></label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="mb-3 d-flex justify-content-end">
                <a class="btn btn-secondary m-3" href="${pageContext.request.contextPath}/admin/news">Hủy bỏ</a>
                <button type="submit" class="btn btn-warning m-3">Cập nhật</button>
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