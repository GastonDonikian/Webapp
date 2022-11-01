<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/indexStyle.css"/>">
    <title><spring:message code="general.navbar" /></title>

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>
<nav class="navbar navbar-dark bg-dark navbar-expand-sm">
    <div class="container">
        <div>
            <a draggable="false" class="navbar-brand" href="<c:url value="/"/>">
                <img draggable="false" src="<c:url value="/resources/images/connect.png"/>" style="max-width: 160px;"/>
            </a>
        </div>
        <div id="idNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item"><a href="<c:url value="/contracts"/>" class="nav-link"><spring:message code="general.contratos" /></a></li>
                <sec:authorize access="isAuthenticated()">
                <li class="nav-item"><a href="<c:url value="/myLessons"/>" class="nav-link"><spring:message code="general.misClases" /></a></li>
                    <c:if test="${loggedUser.isProfessor == true}">
                        <li class="nav-item"><a href="<c:url value="/myStudents"/>" class="nav-link"><spring:message code="dropdown.alumnos" /></a></li>
                    </c:if>
                </sec:authorize>


            </ul>

        </div>
        <div>
            <ul class="navbar-nav ml-auto">
                <%--                <sec:authorize access="isAuthenticated()">--%>
                <%--                    <!-- if user is authenticated-->--%>
                <%--                    <li class="nav-item"><a href="<c:url value="/logout"/>" class="nav-link"><spring:message code="general.cerrarSesion" /></a></li>--%>
                <%--                </sec:authorize>--%>
                <sec:authorize access="!isAuthenticated()">
                    <!-- if user is not authenticated-->

                    <li class="nav-item"><a href="<c:url value="/register"/>" class="nav-link"><spring:message code="general.register" /></a></li>
                    <li class="nav-item"><a href="<c:url value="/login"/>" class="nav-link"><spring:message code="general.iniciarSesion" /></a></li>
                </sec:authorize>
                <li>
                    <sec:authorize access="isAuthenticated()">
                        <c:if test="${loggedUser.isProfessor == true}">
                            <div style="margin: 5px" class="btn-group show-on-hover">
                                <button   type="button" style="opacity: 1;background: none;margin: 0px;padding: 0px;white-space: nowrap;width: auto;overflow: visible;background: none;" class="text-white btn btn-default dropdown" data-toggle="dropdown">
                                    <img draggable="false" src="<c:url value="/user/image/${loggedUser.userId} "/>" class="img-fluid"
                                         style="object-fit: cover;border-radius: 50%;width: 35px;height: 35px;object-fit: cover;"/>
                                </button>
                                <ul style="margin-top: -2px;" class="dropdown-menu" role="menu">
                                    <li><a href="<c:url value="/professorProfile"/>" class="dropdown-item"><spring:message code="dropdown.subjects" /></a></li>
                                    <li><a href="<c:url value="/profesor/${loggedUser.userId}"/>" class="dropdown-item"><spring:message code="dropdown.perfil" /></a></li>
                                    <hr/>
                                    <li><a href="<c:url value="/logout"/>" class="dropdown-item"><spring:message code="general.cerrarSesion" /></a></li>
                                </ul>
                            </div>
                        </c:if>
                        <c:if test="${loggedUser.isProfessor == false}">
                            <div class="btn-group show-on-hover">
                                <button  type="button" style="opacity: 1;background: none;margin: 0px;padding: 0px;white-space: nowrap;width: auto;overflow: visible;background: none;" class="text-white btn btn-default dropdown" data-toggle="dropdown">
                                    <img draggable="false" src="<c:url value="/user/image/${loggedUser.userId} "/>" class="img-fluid"
                                         style="border-radius: 50%;width: 35px;height: 35px;object-fit: cover;"/>
                                </button>
                                <ul class="dropdown-menu" role="menu">
                                    <li><a href="<c:url value="/student/${loggedUser.userId}"/>" class="dropdown-item"><spring:message code="dropdown.perfil" /></a></li>
                                    <hr/>
                                    <li><a href="<c:url value="/logout"/>" class="dropdown-item"><spring:message code="general.cerrarSesion" /></a></li>
                                </ul>
                            </div>
                        </c:if>
                    </sec:authorize>
                </li>
            </ul>
        </div>

    </div>
</nav>
</body>
</html>