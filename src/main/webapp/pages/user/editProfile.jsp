<%--
  Created by IntelliJ IDEA.
  User: Dunik
  Date: 01.10.2021
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<jsp:include page="../templates/footer.jsp"/>

<!- form for update user description and avatar -!>
<form action="<c:url value="/ImageUploader"/>" method="post" enctype="multipart/form-data">
    <input type="hidden" name="id" value="${user.id}"/>
    <div class="form-group">
        <label class="col-md-2 control-label" for="avatar">avatar</label>
        <div class="col-md-8">
            <img style="width: 20px; height: 20px"
                 src="../../static/image/user/${user.avatarSrc}">
            <input id="avatar"
                   type="file"
                   name="avatar"
                   accept=".image/vnd.sealedmedia.softseal.jpg, .image/vnd.sealedmedia.softseal.png"/>
        </div>
    </div>
    <div class="form-group">
        <input type="submit" name="submit" class="btn btn-outline-dark"
               value="save">
    </div>
</form>
<!- Security -!>
<jsp:include page="../templates/header.jsp"/>
</body>
</html>
