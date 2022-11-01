<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <title><spring:message code="general.verify"/></title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!--Made with love by Mutiullah Samim -->

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/verifyStyle.css"/>">
    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

</head>
<body style="background-color: ghostwhite">
<!--NAV BAR-->
<%@include file="../components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="../components/homeBg.jsp" %>

<div class="container-fluid py-5">
    <div class="container">
        <c:if test="${loggedUser != null }">
            <c:if test="${loggedUser.enabled == true}">
                <h3 class="text-center"><spring:message code="form.mensajeVerificado"/></h3>
            </c:if>
            <c:if test="${loggedUser.enabled == false}">
                <h3 class="text-center"><spring:message code="form.mensajeNoVerificado"/></h3>
            </c:if>

            <div class="row my-5 justify-content-center">
                <div class="col-lg-offset-3 col-lg-6 col-md-offset-2 col-md-8 col-sm-offset-1 col-sm-10">
                    <div class="email-signature5">
                        <div class="signature-icon">
                            <img draggable="false" style="width: 100px;height: 100px;border-radius: 100px;margin: 10%"
                                 class="img-fluid"
                                 src="<c:url value="/resources/images/profilePhoto.jpeg"/>">
                        </div>
                        <ul class="signature-content">
                            <li><h3 class="title"><c:out value="${loggedUser.name} ${loggedUser.surname}"/></h3></li>
                            <li>Email: <c:out value="${loggedUser.email}"/></li>
                            <li>Mobile: <c:out value="${loggedUser.phoneNumber}"/></li>
                        </ul>
                        <div>
                            <c:if test="${loggedUser.enabled == true}">
                                <a href="${pageContext.request.contextPath}/" style="background-color: #009AC2;"
                                   class="text-white btn float-right login_btn"><spring:message
                                        code="form.comenzar"/> </a>
                            </c:if>
                            <c:if test="${loggedUser.enabled == false}">
                                <form action="${pageContext.request.contextPath}/verify/send" method="post">
                                    <input type="submit"
                                           style="background-color: #009AC2;"
                                           class="text-white btn float-right login_btn" value="<spring:message
                                        code="form.reenviarConfirmacion"/>">
                                </form>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${loggedUser == null}">
            <h3 class="text-center"><spring:message code="form.mensajeVerificado"/></h3>
        </c:if>
    </div>
</div>

</body>
</html>
