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
<jsp:include page="../templates/header.jsp"/>
<body>

<section class="vh-100 gradient-custom">
    <div class="container h-80">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">Resend password form</h3>
                        <form action="<c:url value="/Controller"/>" method="post">
                            <div class="form-group">
                                <input type="hidden" name="command" value="reset_password"/>

                                <div class="col-md-6 mb-4">

                                    <div class="form-outline">
                                        <label for="email">email</label>
                                        <input
                                                name="email"
                                                id="email"
                                                class="form-control form-control-lg"
                                                required=""
                                                pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                                                placeholder="email"/>
                                    </div>

                                </div>
                            </div>

                            <div class="mt-4 pt-2">
                                <input type="submit" name="submit" class="btn btn-primary btn-lg"
                                       value="reset">
                            </div>
                            <div class="mt-4 pt-2">
                            <c:if test="${sessionScope.user_does_not_exist}">
                                <div class="alert alert-warning text-center p-4">
                                    пользователя с такой почтой нет,<br/> попытайтесь еще раз
                                </div>
                            </c:if>
                            <c:if test="${sessionScope.denied_reset_password}">
                                <div class="alert alert-warning text-center p-4">
                                    вы уже меняли пароль,<br/> попытайтесь через 3 дня
                                </div>
                            </c:if>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../templates/footer.jsp"/>
</body>
</html>
