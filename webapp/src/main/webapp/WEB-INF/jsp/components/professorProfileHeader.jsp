<%--
  Created by IntelliJ IDEA.
  User: gaston
  Date: 3/10/21
  Time: 19:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ignore</title>
</head>
<body>

<div class="shadow-sm" style="background-color: white">
    <div class="row align-items-start">
        <div class="col-1 ml-5">
            <img draggable="false" style="border-radius: 50%;width: 100px;height: 100px;object-fit: cover;margin: 10%;"
                 class="img-fluid"
                 src="<c:url value="/user/image/${loggedUser.userId}"/>">
        </div>

        <div class="col align-self-end" style="margin: 1%">
            <h3 class="mb-0"><c:out value="${loggedUser.name} ${loggedUser.surname}"/></h3>
            <%--            <p class="mb-0"><c:out value="${loggedUser.studies}" /></p>--%>
            <p class="mb-0"><c:out value="${loggedUser.email}"/></p>
            <a href="<c:url value="/editarProfessor"/>" class="btn btn-info"><spring:message
                    code="professor.editarPerfil"/></a>
        </div>
    </div>
</div>
</body>
</html>
