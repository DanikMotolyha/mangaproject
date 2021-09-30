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
    Login ${user.login}
</div>
<div>
    email ${user.email}
</div>
<jsp:include page="../templates/header.jsp"/>
</body>
</html>
