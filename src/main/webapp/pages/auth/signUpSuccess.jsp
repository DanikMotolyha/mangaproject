<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 29.09.2021
  Time: 20:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
    регистрация завершена, ваш пароль был отправлен вам на почту
    <form action="<c:url value="/Controller"/>" method="post">
        <input type="hidden" name="command" value="go_to_main_page"/>
        <div class="form-group">
            <input type="submit" name="submit" class="btn btn-outline-dark"
                   value="continue">
        </div>
    </form>
</body>
</html>
