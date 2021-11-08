<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 26.09.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>
<head>
    <link rel="stylesheet" href="/styles/home.css">
</head>
<jsp:include page="templates/header.jsp"/>
<div class="container p-0">
    <div id="grid_manga">
        <c:forEach items="${manga_list_data}" var="manga">
            <a class="text-decoration-none text-black" href="/Controller?command=go_to_manga_info_page&id=${manga.id}">
                <div id="grid_manga_elem" class="shadow-lg">
                    <div><img id="grid_manga_avatar" src="/image?command=image&id=${manga.idAvatar}"></div>
                    <div class="text-center h3">${manga.name}</div>
                    <div class="text-center">${manga.postDate}</div>
                    <div class="text-center">${manga.status.getName()}</div>
                </div>
            </a>
        </c:forEach>
    </div>
    <div class="text-center">Page : ${current_page} of ${max_pages}</div>
    <div class="text-center">
        <%--For displaying Previous link except for the 1st page --%>
        <c:if test="${current_page gt 0}">
            <td><a href="/Controller?command=go_to_home_page&page=${current_page - 1}">Previous</a></td>
        </c:if>
        <%--For displaying Next link --%>
        <c:if test="${current_page lt max_pages}">
            <td><a href="/Controller?command=go_to_home_page&page=${current_page + 1}">Next</a></td>
        </c:if>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
