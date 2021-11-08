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
<jsp:include page="../templates/header.jsp"/>
<section class="vh-100 gradient-custom">
    <div class="container h-80">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">SignIn form</h3>
                        <form action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="sign_in"/>

                            <div class="col-md-6 mb-4">

                                <div class="form-outline">
                                    <label class="form-label" for="login_input">Login</label>
                                    <input
                                            name="login"
                                            id="login_input"
                                            type="text"
                                            pattern="^[a-zA-Z0-9_]{5,16}$"
                                            maxlength="16"
                                            required=""
                                            class="form-control form-control-lg"/>
                                </div>

                            </div>

                            <div class="col-md-6 mb-4">

                                <div class="form-outline">
                                    <label class="form-label" for="password_input">Password</label>
                                    <input
                                            name="password"
                                            id="password_input"
                                            type="password"
                                            pattern="^[a-zA-Z0-9_]{5,16}$"
                                            maxlength="16"
                                            required=""
                                            class="form-control form-control-lg"/>
                                </div>

                            </div>

                            <div class="mt-4 pt-2">
                                <input class="btn btn-primary btn-lg" type="submit" value="submit"/>
                            </div>
                        </form>
                        <form action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="go_to_reset_password_page"/>
                            <input type="submit" name="submit" class="btn btn-primary btn-lg"
                                   value="reset password">
                        </form>

                        <c:if test="${sessionScope.is_sign_in_data_invalid}">
                            <div class="alert alert-warning text-center">
                                неверный логин или пароль,<br/> попытайтесь еще раз
                            </div>
                        </c:if>

                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
<jsp:include page="../templates/footer.jsp"/>
