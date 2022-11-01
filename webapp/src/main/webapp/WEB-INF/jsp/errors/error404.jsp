<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/error404Style.css"/>">
    <title><spring:message code="general.error" /></title>
</head>
<body>
    <div class="error-content">
        <div class="container">
            <div class="row">
                <div class="col-md-12 ">
                    <div class="error-text">
                        <h1 class="error"><spring:message code="error.404" /></h1>
                        <div class="im-sheep">
                            <div class="top">
                                <div class="body"></div>
                                <div class="head">
                                    <div class="im-eye one"></div>
                                    <div class="im-eye two"></div>
                                    <div class="im-ear one"></div>
                                    <div class="im-ear two"></div>
                                </div>
                            </div>
                            <div class="im-legs">
                                <div class="im-leg"></div>
                                <div class="im-leg"></div>
                                <div class="im-leg"></div>
                                <div class="im-leg"></div>
                            </div>
                        </div>
                        <h4><spring:message code="error.upsProblema" /></h4>
                        <p><spring:message code="error.disculpeRemovidoMensaje" /></p>
                        <a href="<c:url value="/"/>" class="btn btn-primary btn-round"><spring:message code="general.volver" /></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
