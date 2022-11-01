<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">
    <!-- Bootstrap core JS-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.bundle.min.js"></script>
    <!-- Core theme JS-->

    <title>Connect</title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/homeStyle.css"/>">
</head>
<body class="bg-light">
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>
<!-- Header-->
<header>
    <div class="container mt-3 mb-5">
        <div class="border-style rounded-3 bg-light text-center" style="border-radius: 6px;">
            <div class="container ">
                <div class="border-style pt-3 rounded-3 bg-light text-center">
                        <div class="m-4 m-lg-5">
                            <div class="feature bg-gradient text-white rounded mb-4 mt-n4" style="background-color: #8ECAE1"><i
                                    class="bi bi-search"></i></div>
                            <h2 class="display-5 fw-bold"><spring:message code="home.encontraProfesores"/></h2>
                            <h5><spring:message code="home.empezaBuscando"/></h5>
                        </div>
                    <form action="<c:url value="/contracts"/>" method="get">
                        <div class="input-group pb-2">
                        <input class="form-control" type="text" name="search" placeholder="<spring:message code="materia.buscar"/>">
                        <input type="submit"
                               class="btn text-white input-group-append" style="align-self: center; background-color: #349AC2;" value="<spring:message code="materia.buscar"/>">
                        </div>
                    </form>
                    <form action="<c:url value="/contracts"/>" method="get">
                       <div class="pb-4">
                        <c:forEach var="category" items="${categories}">
                            <spring:message code="${category}" var="option"/>
                            <button type="submit" name="categories"
                                   class="btn colorLogo text-white rounded ml-1 mr-1" style="align-self: center; " value="${category}">
                                ${option}
                            </button>
                        </c:forEach>
                       </div>
                    </form>
                </div>
            </div>


        </div>
    </div>
</header>
<!-- Page Content-->
<section>
    <div class="container px-lg-5">
        <!-- Page Features-->
        <div class="row gx-lg-5">
            <sec:authorize access="!isAuthenticated()">
                <div class="col-lg-6 col-xxl-4 mb-5">
                    <div class="card bg-light border-0 h-100">
                        <a href="${pageContext.request.contextPath}/register" style="text-decoration: none;color: inherit;color: black;">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                                <div class="feature bg-gradient text-white rounded mb-4 mt-n4" style="background-color: #8ECAE1;"><i
                                        class="bi bi-collection"></i></div>
                                <h2 class="fs-4 fw-bold"><spring:message code="home.queresSerProfesor"/></h2>
                                <p class="mb-0"><spring:message code="home.queresSerDescripcion"/></p>
                            </div>
                        </a>
                    </div>
                </div>
            </sec:authorize>
                <div class="col-lg-6 col-xxl-4 mb-5">
                    <div class="card bg-light border-0 h-100">
                        <a href="mailto:staff@connect.com" style="text-decoration: none;color: inherit;color: black;">
                            <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                                <div class="feature bg-gradient text-white rounded mb-4 mt-n4" style="background-color: #8ECAE1;"><i
                                        class="bi bi-envelope"></i></div>
                                <h2 class="fs-4 fw-bold"><spring:message code="home.queresMail"/></h2>
                                <p class="mb-0"><spring:message code="home.queresMailDescripcion"/></p>
                            </div>
                        </a>
                    </div>
                </div>
            <sec:authorize access="isAuthenticated()">
            <div class="col-lg-6 col-xxl-4 mb-5">
                <div class="card bg-light border-0 h-100">
                    <a href="${pageContext.request.contextPath}/nuevaMateria" style="text-decoration: none;color: inherit;color: black;">
                        <div class="card-body text-center p-4 p-lg-5 pt-0 pt-lg-0">
                            <div class="feature bg-gradient text-white rounded mb-4 mt-n4" style="background-color: #8ECAE1;"><i
                                    class="bi bi-book"></i></div>
                            <h2 class="fs-4 fw-bold"><spring:message code="home.faltaMateria"/></h2>
                            <span class="mb-0"><spring:message code="home.faltaMateriaDescripcion"/></span>
                        </div>
                    </a>
                </div>
            </div>
            </sec:authorize>
        </div>
    </div>
</section>

<!--FOOTER -->
<%--<%@include file="components/footer.jsp" %>--%>
<!--FOOTER-->

</body>
</html>

