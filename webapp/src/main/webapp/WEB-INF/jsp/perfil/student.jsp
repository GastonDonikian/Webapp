<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="form.perfil"/></title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/profileStyle.css"/>">
</head>
<body style="background-color: ghostwhite">

<!--NAV BAR-->
<%@include file="../components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="../components/homeBg.jsp" %>


<div>
    <c:url value="/studentProfile" var="postFormProfile"/>
    <div class="shadow-sm rounded" style="margin: 3% 20%; background-color: white;">
        <div style="padding: 5% 2% 1% 5% ; "><form:form enctype="multipart/form-data" modelAttribute="profileStudentForm" action="${postFormProfile}"
                   method="post">

            <div class="form-group row">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                </div>
                <spring:message code="form.nombre" var="formNombre"/>
                <c:set var="loggedUserName">
                    <c:out value="${loggedUser.name}"/>
                </c:set>
                <form:input value="${loggedUserName}" class="form-control col-sm-10" id="inputNombre"
                            placeholder="${formNombre}" type="text" path="name"/>
            </div>
            <div style="text-align: center;">
                <form:errors path="name" cssStyle="color: red;font-weight: bold;" element="p"/>
            </div>
            <div class="form-group row">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-user"></i></span>
                </div>
                <spring:message code="form.apellido" var="formApellido"/>
                <c:set var="loggedUserSurname">
                    <c:out value="${loggedUser.surname}"/>
                </c:set>
                <form:input value="${loggedUserSurname}" class="form-control col-sm-10" id="inputApellido"
                            placeholder="${formApellido}" type="text" path="surname"/>
            </div>
            <div style="text-align: center;">
                <form:errors path="surname" cssStyle="color: red;font-weight: bold;" element="p"/>
            </div>
            <div class="form-group row">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="far fa-envelope"></i></span>
                </div>
                <spring:message  code="form.email" var="formEmail"/>
                <c:set var="loggedUserEmail">
                    <c:out value="${loggedUser.email}"/>
                </c:set>
                <form:input readonly="true" value="${loggedUserEmail}" class="form-control col-sm-10" id="inputEmail"
                            placeholder="${formEmail}" type="text" path="email"/>
            </div>
            <div style="text-align: center;">
                <form:errors path="email" cssStyle="color: red;font-weight: bold;" element="p"/>
            </div>
            <div class="form-group row">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-phone"></i></span>
                </div>
                <spring:message code="form.telefono" var="formTelefono"/>
                <c:set var="loggedUserPhoneNumber">
                    <c:out value="${loggedUser.phoneNumber}"/>
                </c:set>
                <form:input value="${loggedUserPhoneNumber}" class="form-control col-sm-10" id="inputTelefono"
                            placeholder="${formTelefono}" type="text" path="phone"/>
            </div>
            <div style="text-align: center;">
                <form:errors path="phone" cssStyle="color: red;font-weight: bold;" element="p"/>
            </div>
            <div class="form-group row">
                <div class="input-group-prepend">
                    <span class="input-group-text"><i class="fas fa-image"></i></span>
                </div>
                <spring:message code="form.subirArchivo" var="formSubir"/>
                <form:input path="profileImage" style="padding: 0.3%;" type="file" data-msg-placeholder="${formSubir}" class="form-control col-sm-10"/>
            </div>
            <div style="text-align: left;">
                <form:errors path="profileImage" cssStyle="color: red;font-weight: bold;" element="p"/>
            </div>
            <div style="text-align:right">
                <spring:message code="form.guardarCambios" var="formGuardar"/>
                <input type="submit" class="btn text-white" style="background-color: #349AC2" value="${formGuardar}"/>
            </div>
        </form:form>
        </div>
    </div>

</body>
</html>
