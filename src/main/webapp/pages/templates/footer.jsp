<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 27.09.2021
  Time: 15:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>


<fmt:message key="locale.footer.info" var="footer_info_about_project"/>
<fmt:message key="locale.footer.links.message" var="footer_links_message"/>
<head>
    <link rel="stylesheet" href="/styles/footer.css">
</head>
<div class="container footer mt-5 shadow-lg">
    <div class="row justify-content-between p-3">
        <div class="col mx-auto">
            <h3 style="color: #6c757d">${footer_info_about_project}</h3>
        </div>
        <div class="row mx-auto">
                <div class="col-auto h5 p-2" style="color: #6c757d">${footer_links_message}</div>
                <a class="col-auto p-2" href="https://vk.com/duni_k"><img class="footer-links-image" src="../../static/vk.svg"/></a>
                <a class="col-auto p-2" href="https://www.linkedin.com/in/daniil-motolyha-9528a31b0/"><img class="footer-links-image"
                                                                                      src="../../static/linkedin.svg"/></a>
        </div>
    </div>
</div>