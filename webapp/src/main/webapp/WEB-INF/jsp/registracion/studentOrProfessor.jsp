<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <title><spring:message code="form.registracion"/></title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!--Made with love by Mutiullah Samim -->

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"/>

    <link href="http://www.w3.org/html/logo/downloads/HTML5_Badge_512.png" rel="icon"/>

    <!--Bootsrap 4 CDN-->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">

    <!--Fontawesome CDN-->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
    <!--Custom styles-->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/studentOrProfessorStyle.css"/>">
</head>
<body>
<!--NAV BAR-->
<%@include file="../components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="../components/homeBg.jsp" %>

<!--NAV BAR-->
<div class="cards-container">
    <a href="${pageContext.request.contextPath}/registerProfessor">
        <div class="card">
            <div class="box">
                <div class="img">
                    <img draggable="false" src="<c:url value="/resources/images/book.png"/>">
                </div>
                <h2><span><spring:message code="form.profesor"/></span></h2>
                <p> <spring:message code="form.descripcionProfesor"/></p>
            </div>
        </div>
    </a>
    <a href="${pageContext.request.contextPath}/registerStudent">
        <div class="card">
            <div class="box">
                <div class="img">
                    <img draggable="false" src="<c:url value="/resources/images/book.png"/>">
                </div>
                <h2><span><spring:message code="form.alumno"/></span></h2>
                <p> <spring:message code="form.descripcionAlumno"/></p>
            </div>
        </div>
    </a>
</div>
</body>
</html>
