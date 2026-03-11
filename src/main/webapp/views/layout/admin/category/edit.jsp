<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 1/23/2026 Time: 1:09 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<!-- Form dialog  -->
<div class="dialog-newsletter modal fade" id="subscribeModal2" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title"> Loại tin tức </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/category/update">
                    <div class="mb-3">
                        <label class="form-label"> Mã loại </label>
                        <input type="text" name="id" id="edit-id" class="form-control" readonly>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Tên loại</label>
                        <input type="text" name="name" id="edit-name" class="form-control">
                    </div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-bs-dismiss="modal"> Hủy </button>
                        <button type="submit" class="btn btn-warning"> Sửa </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>