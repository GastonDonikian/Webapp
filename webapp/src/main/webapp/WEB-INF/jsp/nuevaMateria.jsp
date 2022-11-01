<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Establecer Contacto</title>
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
                <h2><strong><spring:message code="materia.nuevaMateria" /></strong></h2>
                <p><spring:message code="materia.completarCampos" /></p>
                <div style="margin: 2%;">
                    <div class="col-md-12 mx-0">
                        <c:url value="/nuevaMateria" var="postNewSubjectUrl"/>
                        <form:form modelAttribute="newSubjectForm" action="${postNewSubjectUrl}" method="post">
                            <div class="form-group row">
                                <spring:message code="form.email" var="Email"/>
                                <form:errors path="email" cssStyle="color: red;font-weight: bold;" element="p"/>
                                <c:set var="loggedUserEmail">
                                    <c:out value="${loggedUser.email}"/>
                                </c:set>

                                <form:input readonly="true" class="form-control col-sm-10" id="inputEmail" value="${loggedUserEmail}" type="text" path="email"/>


                            </div>

                            <div class="form-group row">
                                <spring:message code="form.materia" var="Materia"/>
                                <form:errors path="subject" cssStyle="color: red;font-weight: bold;" element="p"/>
                                <form:input class="form-control col-sm-10" id="inputSubject" placeholder="${Materia} *" type="text" path="subject"/>
                            </div>

                            <div class="form-group row">
                                <spring:message code="form.seleccionarNivel" var="SelectN"/>
                                <form:select path="level" class="form-control col-sm-10" aria-label="Seleccionar Nivel">
                                    <form:errors path="level" cssStyle="color: red;font-weight: bold;" element="p"/>
                                    <form:option value="" label="${SelectN}"/>
                                    <c:forEach items="${levels}" var="level">
                                        <form:option value="${level}">
                                            <spring:message code="${level}" var="option2"/>
                                            <c:out value="${option2}"/>
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group row">
                                <spring:message code="form.seleccionarCategoria" var="SelectC"/>
                                <form:select path="category" class="form-control col-sm-10">
                                    <form:errors path="category" cssStyle="color: red;font-weight: bold;" element="p"/>
                                    <form:option value="" label="${SelectC}"/>
                                    <c:forEach items="${categories}" var="category">
                                        <form:option value="${category}">
                                            <spring:message code="${category}" var="option"/>
                                            <c:out value="${option}"/>
                                        </form:option>
                                    </c:forEach>
                                </form:select>
                            </div>

                            <div class="form-group row">
                                <spring:message code="form.mensaje" var="Mensaje"/>
                                <form:errors path="message" cssStyle="color: red;font-weight: bold;" element="p"/>
                                <form:textarea class="form-control col-sm-10" id="inputMessage" placeholder="${Mensaje} *" type="text" path="message"/>
                            </div>

                            <div style="text-align: right">
                                <spring:message code="form.enviar" var="Enviar"/>
                                <input type="submit" class="text-white btn" style="background-color: #349AC2;" value="${Enviar}"/>
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
