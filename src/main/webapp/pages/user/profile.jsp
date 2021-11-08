<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 29.09.2021
  Time: 21:28
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<jsp:include page="../templates/footer.jsp"/>
<div>
    <div style="width: 20px; height: 20px">
        <img src="../../static/image/user/${user.avatarSrc}"/>
    </div>
    <div>
        Login ${user.login}
    </div>
    <div>
        email ${user.email}
    </div>
    <div>
        description ${user.description}
    </div>
    <div>
        resend Password ${user.resendPasswordDate}
    </div>
</div>
<form action="<c:url value="/Controller"/>" method="post">
    <input type="hidden" name="command" value="go_to_edit_profile_page"/>
    <div class="form-group">
        <input type="submit" name="submit" class="btn btn-outline-dark"
               value="edit">
    </div>
</form>
<form action="<c:url value="/Controller"/>" method="post">
    <input type="hidden" name="command" value="go_to_user_list_profile_page"/>
    <div class="form-group">
        <input type="submit" name="submit" class="btn btn-outline-dark"
               value="list">
    </div>
</form>
<jsp:include page="../templates/header.jsp"/>
</body>
</html>
