<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 26.09.2021
  Time: 18:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<body>
    error <br/>
    <ul>
        <li>Request from ${pageContext.errorData.requestURI} </li>
        <li>Servlet name: ${pageContext.errorData.servletName}</li>
        <li>Status code: ${pageContext.errorData.statusCode}</li>
        <li>Exception: ${pageContext.exception}</li>
        <li>Message from exception: ${pageContext.exception.message}</li
        <li>details: ${exception}</li
    </ul>
</body>
</html>
