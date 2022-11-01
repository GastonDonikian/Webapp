<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title><spring:message code="form.establecerContrato"/></title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
</head>
<body style="background-color: ghostwhite">
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>

<div class="container-fluid" id="grad1">
    <div class="row justify-content-center mt-0">
        <div class="col-11 col-sm-9 col-md-7 col-lg-6 text-center p-0 mt-3 mb-2">
            <div class="card px-0 pt-4 pb-0 mt-3 mb-3">
                <h2><strong><spring:message code="form.nuevoContrato"/></strong></h2>
                <p><spring:message code="form.completaFormulario"/></p>
                <div class="row" style="margin-left: 5%;">
                    <div class="col-md-12 mx-0">
                        <c:url value="/nuevoContrato/materia/${subjectId}" var="postContractUrl"/>
                        <form:form modelAttribute="contractForm" action="${postContractUrl}" method="post">
                            <div class="form-group row">
                                <c:set var="loggedUserName">
                                    <c:out value="${loggedUser.name}"/>
                                </c:set>
                                <input class="form-control col-sm-10" id="inputEmail" readonly="true" type="text" value="${loggedUserName}"/>
                            </div>

                            <div class="form-group row">
                                <input class="form-control col-sm-10" id="inputSubject" readonly type="text" value="${subject.get().name}"/>
                            </div>
                            <div class="form-group row">
                                <spring:message code="form.mensajeMateria" var="mensaje"/>
                                <form:textarea class="form-control col-sm-10" id="inputMessage"
                                               placeholder="${mensaje}" type="text"
                                               path="contractDescription"/>
                            </div>
                            <div style="text-align: left;">
                                <form:errors path="contractDescription" cssStyle="color: red;font-weight: bold;" element="p"/>
                            </div>
                            <div class="form-group row">
                                <div class="col-auto">
                                    <form:checkbox path="local" value="Local"/>
                                    <form:label path="local"><spring:message code="materia.local"/></form:label>
                                </div>
                                <div class="col-auto">
                                    <form:checkbox path="remote" value="Remoto"/>
                                    <form:label path="remote"><spring:message code="materia.remoto"/></form:label>
                                </div>
                            </div>
                            <div class="form-group row">
                                <spring:message code="form.precioPorHora" var="precio"/>
                                <c:set var="escapedPrice">
                                    <c:out value="${precio}"/>
                                </c:set>
                                <form:input class="form-control col-sm-10" placeholder="${escapedPrice}" id="inputSubject" type="text"
                                            path="price"/>
                            </div>
                            <div style="text-align: left;">
                                <form:errors path="price" cssStyle="color: red;font-weight: bold;" element="p"/>
                            </div>

                            <div style="text-align: right;margin-right: 5%">
                                <spring:message code="form.enviar" var="enviar"/>
                                <input type="submit" class="text-white btn" style="background-color: #349AC2;" value="${enviar}"/>
                            </div>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>