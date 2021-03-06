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

<jsp:include page="../templates/header.jsp"/>
<section class="vh-100 gradient-custom">
    <div class="container h-80">
        <div class="row justify-content-center align-items-center h-100">
            <div class="col-12 col-lg-9 col-xl-7">
                <div class="card shadow-2-strong card-registration" style="border-radius: 15px;">
                    <div class="card-body p-4 p-md-5">
                        <div class="mt-4 pt-2">
                            регистрация завершена, ваш пароль был отправлен вам на почту.<br/>
                            пароль вы можете поменять в личном кабинете
                        </div>
                        <form action="<c:url value="/Controller"/>" method="post">
                            <input type="hidden" name="command" value="go_to_home_page"/>
                            <div class="mt-4 pt-2">
                                <input type="submit" name="submit" class="btn btn-outline-dark"
                                       value="continue">
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
