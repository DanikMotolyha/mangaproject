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
<jsp:include page="../templates/header.jsp"/>
<section class="vh-100 gradient-custom">
    <div class="container h-80">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <h3 class="mb-4 pb-2 pb-md-0 mb-md-5">SignUp page</h3>
                        <form id="signUp" action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="sign_up"/>
                            <div class="mt-4 pt-2">
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
                            <div class="mt-4 pt-2">
                            <label for="email">email</label>
                            <input
                                    name="email"
                                    id="email"
                                    class="form-control"
                                    required=""
                                    pattern="^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$"
                                    placeholder="email"/>
                            </div>
                            <div class="mt-4 pt-2">
                            <input type="submit" name="submit" class="btn btn-outline-dark"
                                   value="signUp">
                            </div>
                            <div class="mt-4 pt-2">
                            <c:if test="${duplicate_email}">
                                <div class="alert alert-warning text-center p-4">
                                    такая почта уже зарегестрирована,<br/> попытайтесь еще раз
                                </div>
                            </c:if>
                            <c:if test="${duplicate_login}">
                                <div class="alert alert-warning text-center p-4">
                                    такой логин уже зарегестрирован,<br/> попытайтесь еще раз
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
</html>