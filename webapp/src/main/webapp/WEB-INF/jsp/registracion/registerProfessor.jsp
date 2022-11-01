<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <title><spring:message code="general.iniciarSesion" /></title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!--Made with love by Mutiullah Samim -->

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>


    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/loginStyle.css"/>">
</head>
<body>
<!--NAV BAR-->
<%@include file="../components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="../components/homeBg.jsp" %>

<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card" style="margin: 2%;">
            <div class="text-center card-header">
                <h3><spring:message code="form.registracion" /></h3>
                <%--                <div class="d-flex justify-content-end social_icon">--%>
                <%--                    <span><i class="fab fa-facebook-square"></i></span>--%>
                <%--                    <span><i class="fab fa-google-plus-square"></i></span>--%>
                <%--                    <span><i class="fab fa-twitter-square"></i></span>--%>
                <%--                </div>--%>
            </div>
            <div class="card-body">
                <c:url value="/registerProfessor" var="getRegisterProfessor"/>
                <form:form modelAttribute="SignUpProfessorForm" action="${getRegisterProfessor}" method="post">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <spring:message code="form.nombre" var="formNombre"/>
                        <form:input class="form-control" id="inputNombre" placeholder="${formNombre} * " type="text" path="name"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="name" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <spring:message code="form.apellido" var="formApellido"/>
                        <form:input class="form-control" id="inputApellido" placeholder="${formApellido} * " type="text" path="surname"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="surname" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="far fa-envelope"></i></span>
                        </div>
                        <spring:message code="form.email" var="formEmail"/>
                        <form:input class="form-control" id="inputEmail" placeholder="${formEmail} * " type="text" path="email"/>
                    </div>
                    <div style="text-align: center;">
                        <c:if test="${userAlreadyExists == true}">
                            <p style="color: red;font-weight: bold;"><spring:message code="form.emailYaExiste"/>
                            </p>
                        </c:if>
                        <form:errors path="email" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <spring:message code="form.contrasena" var="formContrasena"/>
                        <form:input class="form-control" id="inputContrasena" placeholder="${formContrasena} * " type="password" path="password"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="password" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>

                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <spring:message code="form.repetirContrasena" var="formContrasena"/>
                        <form:input class="form-control" id="inputContrasena" placeholder="${formContrasena} * " type="password" path="repeatPassword"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-compass"></i></span>
                        </div>
                        <spring:message code="form.seleccionarUbicacion" var="SelectN"/>
                        <form:select path="location" class="form-control" aria-label="Seleccionar Nivel" style="padding-left: 2%;">
                            <form:option value="" label="${SelectN}"/>
                            <c:forEach items="${locations}" var="location">
                                <form:option value="${location}">
                                    <spring:message code="${location}" var="option"/>
                                    <c:out value="${option}"/>
                                </form:option>
                            </c:forEach>
                        </form:select>

                    </div>
                    <div style="text-align: center;">
                        <form:errors path="location" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-phone"></i></span>
                        </div>
                        <spring:message code="form.telefono" var="formTelefono"/>
                        <form:input class="form-control" id="inputTelefono" placeholder="${formTelefono} * " type="text" path="phone"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="phone" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <spring:message code="form.edad" var="formEdad"/>
                        <form:input class="form-control" id="inputEdad" placeholder="${formEdad}" type="text" path="age"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="age" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-clock"></i></span>
                        </div>
                        <spring:message code="form.schedule" var="formTime"/>
                        <form:input class="form-control" id="inputTime" placeholder="${formTime}" type="text" path="schedule"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="schedule" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-university"></i></span>
                        </div>
                        <spring:message code="form.estudiosDescripcion" var="formEstudios"/>
                        <form:input class="form-control" id="inputEstudios" placeholder="${formEstudios} * " type="text" path="studies"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="studies" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-thumbs-up"></i></span>
                        </div>
                        <spring:message code="clases.descripcion" var="formDescripcion"/>
                        <form:textarea class="form-control" id="inputDescripcion" placeholder="${formDescripcion}" type="text" path="description"/>
                    </div>
                    <div style="text-align: center;">
                        <form:errors path="description" cssStyle="color: red;font-weight: bold;" element="p"/>
                    </div>
                    <div class="form-group">
                        <spring:message code="form.crear" var="formCrear"/>
                        <input type="submit" value="${formCrear}" class="btn float-right login_btn">
                    </div>
                </form:form>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    <spring:message code="form.siCuenta" /><a href="<c:url value="/login"/>"><spring:message code="general.iniciarSesion" /></a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
