<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 27.09.2021
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<form id="signUp" action="<c:url value="/Controller"/>" method="post">
    <div class="form-group">
        <input type="hidden" name="command" value="sign_up"/>
        <label for="login_input">ЛОГИН</label>
        <input
                name="login"
                id="login_input"
                type="text" class="form-control"
                pattern="^[a-zA-Z0-9_]{5,16}$"
                required=""
                maxlength="16"
                placeholder="логин"/>
    </div>
    <div class="form-group">
        <label for="email">email</label>
        <input
                name="email"
                id="email"
                class="form-control"
                required=""
                pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                placeholder="email"/>
    </div>
    <div class="form-group">
        <input type="submit" name="submit" class="btn btn-outline-dark"
               value="вход">
    </div>
    <c:if test="${duplicate_email}">
        <div class="alert alert-warning">
            такая почта уже зарегестрирована,<br/> попытайтесь еще раз
        </div>
    </c:if>
    <c:if test="${duplicate_login}">
        <div class="alert alert-warning">
            такой логин уже зарегестрирован,<br/> попытайтесь еще раз
        </div>
    </c:if>
</form>
<jsp:include page="../templates/header.jsp"/>
</body>
</html>