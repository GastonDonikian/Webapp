<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/nothingStyle.css"/>">

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<div class="container">
    <div class="nothing-wrapper">
        <img draggable="false" class="man-icon" src="<c:url value="/resources/images/nothing-here.png"/>">
        <h3 class="title"><spring:message code="materia.nada"/></h3>
        <p class="info"><spring:message code="materia.esperar"/></p>
    </div>
</div>
</body>