<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 1/23/2026 Time: 1:09 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.subscribe"/>

<div class="dialog-newsletter modal fade" id="subscribeModal2" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title"> Sửa thông tin tài khoản nhận tin </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/newsletter/save">
                    <input type="hidden" name="id" value="edit">
                    <div class="mb-3">
                        <label class="form-label">Họ tên</label>
                        <input type="text" name="fullname" id="edit-fullname" class="form-control" required>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-8">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" id="edit-email" class="form-control" readonly>
                        </div>
                        <div class="col-1"></div>
                        <div class="col-3 row">
                            <label class="form-label col-12">Trạng thái</label>
                            <div class="col-12 d-flex justify-content-center">
                                <div class="checkbox-wrapper-8">
                                    <input type="checkbox" name="enabled" value="true" id="edit-enabled" class="tgl tgl-skewed" checked>
                                    <label for="edit-enabled" data-tg-on="<fmt:message key="subscribe.status.on"/>" data-tg-off="<fmt:message key="subscribe.status.off"/>" class="tgl-btn"></label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-bs-dismiss="modal"> <fmt:message key="subscribe.cancel"/> </button>
                        <button type="submit" class="btn btn-warning"> Cập nhật </button>
                    </div>
                </form>
            </div>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                        ${error}
                </div>
            </c:if>
        </div>
    </div>
</div>