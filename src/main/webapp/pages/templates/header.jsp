<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 27.09.2021
  Time: 15:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="logo_image" prefix="logo_image" %>

<fmt:setLocale value="${sessionScope.currentLocale}" scope="session"/>
<fmt:setBundle basename="${sessionScope.currentBundle}"/>
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <link href="/styles/header.css" rel="stylesheet"/>

    <!-- Bootstrap core CSS -->
    <link href="/static/css/bootstrap.css" rel="stylesheet"/>
    <link href="/static/css/bootstrap.min.css" rel="stylesheet"/>

    <script src="/static/js/bootstrap.js"></script>
    <script src="/static/js/bootstrap.bundle.js"></script>
</head>
<div id="header-main" class="container p-0 shadow-lg mt-3">
    <div id="header-div" class="row justify-content-around m-0">
        <div class="col-md-3">
            <logo_image:image/>
        </div>
        <div class="col-md-3 my-auto">
            <form class="my-auto" action="<c:url value="/Controller"/>" method="post">
                <input type="hidden" name="command" value="change_locale"/>
                <input type="hidden" name="locale" value="${sessionScope.currentLocale}"/>
                <input type="hidden" name="current_uri" value="${pageContext.request.requestURI}">
                <input type="submit" class="btn btn-outline-dark"
                       value="${sessionScope.secondLocale}">
            </form>
        </div>
        <c:if test="${sessionScope.user == null}">
            <div class="col-md-3 my-auto">
                <div class="row justify-content-md-end my-auto">
                    <div class="col-md-auto my-auto">
                        <form class="m-0 my-auto" action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="GO_TO_SIGN_IN_PAGE"/>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-outline-dark"
                                       value="SignIn">
                            </div>
                        </form>
                    </div>
                    <div class="col-md-auto my-auto">
                        <form class="m-0 my-auto" action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="GO_TO_SIGN_UP_PAGE"/>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-outline-dark"
                                       value="SignUp">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${sessionScope.user != null}">
            <div class="col-md-3 my-auto">
                <div class="row justify-content-md-end my-auto">
                    <div class="col-md-auto my-auto">
                        <form class="m-0 my-auto" action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="GO_TO_PROFILE_PAGE"/>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-outline-dark"
                                       value="profile">
                            </div>
                        </form>

                    </div>
                    <div class="col-md-auto my-auto">
                        <form class="m-0 my-auto" action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="LOG_OUT"/>
                            <div class="form-group">
                                <input type="submit" name="submit" class="btn btn-outline-dark"
                                       value="logout">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </c:if>
    </div>
</div>
