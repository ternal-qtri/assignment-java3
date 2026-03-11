<%-- Created by IntelliJ IDEA. User: QuocTriz Date: 1/23/2026 Time: 1:09 AM --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.subscribe"/>
<!-- Form dialog  -->
<div class="dialog-newsletter modal fade" id="subscribeModal" tabindex="-1" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header">
                <h5 class="modal-title"> <fmt:message key="subscribe.title"/> </h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                <form method="post" action="${pageContext.request.contextPath}/admin/newsletter/create">
                    <div class="mb-3">
                        <label class="form-label"> <fmt:message key="subscribe.name"/> </label>
                        <input type="text" name="fullname" class="form-control" placeholder="Tran Van B" required>
                    </div>
                    <div class="mb-3 row">
                        <div class="col-8">
                            <label class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" placeholder="example123@gmail.com" required>
                        </div>
                        <div class="col-1"></div>
                        <div class="col-3 row">
                            <label class="form-label col-12">Trạng thái</label>
                            <div class="col-12 d-flex justify-content-center">
                                <div class="checkbox-wrapper-8">
                                    <input type="checkbox" name="enabled" value="true" id="cb3-8" class="tgl tgl-skewed" checked>
                                    <label for="cb3-8" data-tg-on="<fmt:message key="subscribe.status.on"/>" data-tg-off="<fmt:message key="subscribe.status.off"/>" class="tgl-btn"></label>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary" data-bs-dismiss="modal"> <fmt:message key="subscribe.cancel"/> </button>
                        <button type="submit" class="btn btn-primary"> <fmt:message key="subscribe.submit"/> </button>
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