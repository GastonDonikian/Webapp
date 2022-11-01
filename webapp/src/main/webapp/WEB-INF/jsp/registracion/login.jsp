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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
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
        <div class="card shadow-sm" style="margin: 2%;">
            <div class="card-header text-center">
                <h3><spring:message code="user.signin" /></h3>
                <%--                <div class="d-flex justify-content-end social_icon">--%>
                <%--                    <span><i class="fab fa-facebook-square"></i></span>--%>
                <%--                    <span><i class="fab fa-google-plus-square"></i></span>--%>
                <%--                    <span><i class="fab fa-twitter-square"></i></span>--%>
                <%--                </div>--%>
            </div>
            <div class="card-body">
                <%--                    VERSION SIN FORMS--%>
                    <c:if test="${param.error != null}">
                        <div style="color: red;" id="error">
                                                    <spring:message code="form.badCredentials">
                                                    </spring:message>
                        </div>
                    </c:if>
                <c:url value="/login" var="getLogin"/>
                <form action="${getLogin}" method="post">
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-user"></i></span>
                        </div>
                        <spring:message code="form.email" var="email"/>
                        <input class="form-control" name="email" placeholder="${email}" type="text"/>
                    </div>
                    <div class="input-group form-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-key"></i></span>
                        </div>
                        <spring:message code="form.contrasena" var="contrasena"/>
                        <input class="form-control" name="password" placeholder="${contrasena}"
                                   type="password"/>
                    </div>
                    <div class="row align-items-center remember" >
                        <input name="rememberme" type="checkbox"><spring:message code="form.recuerdame" />
                    </div>
                    <div class="form-group">
                        <spring:message code="form.entrar" var="entrar"/>
                        <input type="submit" value="${entrar}" class="btn float-right login_btn">
                    </div>
                </form>

                <%--                   VERSION CON FORMS --%>
                <%--                <c:url value="/signIn" var="getSignIn"/>--%>
                <%--                <form:form modelAttribute="SignInForm" action="${getSignIn}" method="post">--%>

                <%--                    --%>

                <%--                    <div class="input-group form-group">--%>
                <%--                        <div class="input-group-prepend">--%>
                <%--                            <span class="input-group-text"><i class="fas fa-user"></i></span>--%>
                <%--                        </div>--%>
                <%--                        <form:errors path="email" cssStyle="color: red;" element="p"/>--%>
                <%--                        <form:input class="form-control" id="inputEmail" placeholder="Email" type="text" path="email"/>--%>
                <%--                    </div>--%>
                <%--                    <div class="input-group form-group">--%>
                <%--                        <div class="input-group-prepend">--%>
                <%--                            <span class="input-group-text"><i class="fas fa-key"></i></span>--%>
                <%--                        </div>--%>
                <%--                        <form:errors path="contrasena" cssStyle="color: red;" element="p"/>--%>
                <%--                        <form:input class="form-control" id="inputContrasena" placeholder="Contrasena" type="password" path="contrasena"/>--%>
                <%--                    </div>--%>
                <%--                    <div class="row align-items-center remember">--%>
                <%--                        <input type="checkbox">Recuerdame--%>
                <%--                    </div>--%>
                <%--                    <div class="form-group">--%>
                <%--                        <input type="submit" value="Entrar" class="btn float-right login_btn">--%>
                <%--                    </div>--%>
                <%--                </form:form>--%>
            </div>
            <div class="card-footer">
                <div class="d-flex justify-content-center links">
                    <spring:message code="form.noCuenta" /><a style="color: #349AC2" href="<c:url value="/register"/>"><spring:message code="form.registrarse" /></a>
                </div>
                <%--                <div class="d-flex justify-content-center">--%>
                <%--                    <a href="#">Te olvidaste la contrasena?</a>--%>
                <%--                </div>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
