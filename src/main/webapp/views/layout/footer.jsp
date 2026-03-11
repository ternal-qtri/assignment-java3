<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<fmt:setLocale value="${sessionScope.lang != null ? sessionScope.lang : 'vi'}"/>
<fmt:setBundle basename="i18n.footer"/>

<footer class="container-fluid footer">
    <div class="container footer-top">
        <div class="row gy-4">
            <div class="col-4 footer-about">
                <a href="#" class="logo d-flex align-items-center">
                    <span class="sitename">Blog - App</span>
                </a>
                <div class="footer-contact pt-3">
                    <p>FPT Polytechnic</p>
                    <p>Nguyễn Quốc Trí</p>
                </div>
            </div>

            <div class="col-4 footer-links">
                <h4><fmt:message key="footer.category"/></h4>
                <ul>
                    <c:forEach var="c" items="${categories}" varStatus="st">
                        <li><a href="#"> ${c.name} </a></li>
                    </c:forEach>
                </ul>
            </div>

            <div class="col-4 footer-links ">
                <h4> <fmt:message key="footer.subscribe"/> </h4>
                <ul>
                    <li>
                        <a href="#" data-bs-toggle="modal" data-bs-target="#subscribeModal">
                            <fmt:message key="footer.subscribe.link"/>
                        </a>
                    </li>
                </ul>
                <div class="logo-school">
                </div>
            </div>
        </div>
    </div>
</footer>