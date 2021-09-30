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
<body>
<form action="<c:url value="/Controller"/>" method="post">
    <div class="form-group">
        <input type="hidden" name="command" value="sign_in"/>
        <label for="login_input">ЛОГИН</label>
        <input
                name="login"
                id="login_input"
                type="text" class="form-control"
                pattern="^[a-zA-Z0-9_]{5,16}$"
                maxlength="16"
                required=""
                placeholder="логин"/>
        <div class="invalid-feedback">
            логин
        </div>
    </div>
    <div class="form-group">
        <label for="password_input">пароль</label>
        <input
                name="password"
                id="password_input"
                type="password" class="form-control"
                pattern="^[a-zA-Z0-9_]{5,16}$"
                maxlength="16"
                required=""
                placeholder="пароль"/>
        <div class="invalid-feedback">
            пароль
        </div>
    </div>
    <div class="form-group">
        <input type="submit" name="submit" class="btn btn-outline-dark"
               value="вход">
    </div>
    <c:if test="${is_sign_in_data_invalid}">
        <div class="alert alert-warning">
            неверный логин или пароль,<br/> попытайтесь еще раз
        </div>
    </c:if>
</form>
<jsp:include page="../templates/header.jsp"/>
</body>
</html>