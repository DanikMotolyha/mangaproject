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

<footer class="footer">
    <div class="container">
        <div class="col-md-5">
            <h3 style="color: #d97777" class="text-muted">MangaReading</h3>
        </div>
    </div>
    <c:if test="${sessionScope.user == null}">
        <form action="<c:url value="/Controller"/>" method="post">
            <input type="hidden" name="command" value="GO_TO_SIGN_IN_PAGE"/>
            <div class="form-group">
                <input type="submit" name="submit" class="btn btn-outline-dark"
                       value="SignIn">
            </div>
        </form>
        <form action="<c:url value="/Controller"/>" method="post">
            <input type="hidden" name="command" value="GO_TO_SIGN_UP_PAGE"/>
            <div class="form-group">
                <input type="submit" name="submit" class="btn btn-outline-dark"
                       value="SignUp">
            </div>
        </form>
    </c:if>
    <c:if test="${sessionScope.user != null}">
        <form action="<c:url value="/Controller"/>" method="post">
            <input type="hidden" name="command" value="GO_TO_PROFILE_PAGE"/>
            <div class="form-group">
                <input type="submit" name="submit" class="btn btn-outline-dark"
                       value="profile">
            </div>
        </form>
        <form action="<c:url value="/Controller"/>" method="post">
            <input type="hidden" name="command" value="LOG_OUT"/>
            <div class="form-group">
                <input type="submit" name="submit" class="btn btn-outline-dark"
                       value="logout">
            </div>
        </form>
    </c:if>

</footer>