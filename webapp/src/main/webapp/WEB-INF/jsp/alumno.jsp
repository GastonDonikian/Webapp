<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="form.contactar"/></title>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
</head>
<body>
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>


<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
<div class="col-4">
    <!--biografia-->
    <div class="card shadow-sm mt-5 ml-5 mr-2">
        <div class="shadow-sm card-body d-flex flex-row">
                    <img draggable="false" style="border-radius: 50%;width: 100px;height: 100px;object-fit: cover;"
                         class="img-fluid p-0"
                         src="<c:url value="/user/image/${user.userId}"/>">

            <div class="ml-3 align-self-center">
                    <h3 class="mb-1"><c:out value="${user.name} ${user.surname}"/></h3>
                    <c:if test="${isCurrentUser}">
                        <a href="<c:url value="/studentProfile"/>" class="btn text-white btn-info"><spring:message
                                code="professor.editarPerfil"/></a>
                    </c:if>
            </div>
        </div>
    </div>
</div>

<div class="col-8">
    <div class="card shadow-sm mt-5 mr-5 mb-5">
        <h5 class="card-header">
            <spring:message code="alumno.infoContacto"/>
        </h5>
        <div class="card-body">
            <div class="text-muted"><spring:message code="form.email"/></div>
            <div class="border-bottom pb-3 mb-3"><c:out value="${user.email}"/></div>

            <div class="text-muted"><spring:message code="form.telefono"/></div>
            <div class="border-bottom pb-3 mb-3"><c:out value="${user.phoneNumber}"/></div>
        </div>

    </div>

</div>
</div>

</body>
</html>

