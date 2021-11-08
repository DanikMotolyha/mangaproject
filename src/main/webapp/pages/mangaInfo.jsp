<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 07.11.2021
  Time: 7:30
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${manga_data.name}</title>
</head>
<body>
<jsp:include page="templates/header.jsp"/>
<div class="container">
    <div class="text-center h1 p-3">${manga_data.name}</div>
    <div class="row justify-content-sm-start">
        <div class="col-md-auto">
            <img id="grid_manga_avatar" class="mx-auto" src="/image?command=image&id=${manga_data.avatar.id}">
            <div class="text-center p-2">post date: ${manga_data.postDate}</div>
            <div class="text-center p-2">status: ${manga_data.mangaStatus.getName()}</div>
        </div>
        <div class="col-sm-6">
            <div class="h3 p-4">description</div>
            <div class="p-4">${manga_data.description}</div>
        </div>
        <div class="col-auto">
            <form action="<c:url value="/Controller"/>" method="post">
                <input type="hidden" name="command" value="go_to_read_chapter_page"/>
                <input type="hidden" name="manga" value="${manga_data.id}"/>
                <input type="hidden" name="chapter" value="1"/>
                    <input type="submit" name="submit" class="btn btn-outline-dark"
                           value="Start read from first chapter"/>
            </form>
        </div>
    </div>
</div>
<jsp:include page="templates/footer.jsp"/>
</body>
</html>
