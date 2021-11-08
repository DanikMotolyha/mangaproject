<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 07.11.2021
  Time: 11:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chapter ${chapter.name}</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-auto">
            <select class="class=form-select" aria-label="Default select example"  name="chapter_list">
                <c:forEach items="${chapterList}" var="chapter_elem">
                    <option value="${chapterList.id}">
                        chapterList.value
                    </option>
                </c:forEach>
            </select>
        </div>
        <div class="col-auto">
            <select class="class=form-select" aria-label="Default select example" name="chapter_image_list">
                <c:forEach items="${chapterImagesList}" var="chapterImage">
                    <option value="${chapterImagesList.id}">
                        chapterImagesList.value
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div>

    </div>
</div>

</body>
</html>
