<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Materias</title>

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

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/materiaStyle.css"/>">
    <style>
        label{
            margin-left: 0.2rem;
        }
    </style>
</head>
<body style=" margin-bottom: 5%;">
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>

<div class="shadow-sm" style="background-color: white;">
    <div class="pl-5 ml-3 mr-3 pt-4 pr-5 pb-3">
        <c:url value="/materias" var="postFilterFormUrl"/>
        <form:form modelAttribute="FilterForm" action="${postFilterFormUrl}" method="get">
            <spring:message code="materia.buscar" var="Buscar"/>
            <div class="input-group">
                <form:input class="form-control" id="search" placeholder="${Buscar}" type="text" path="search"/>
                <input type="submit" class="btn text-white input-group-append" value="${Buscar}" style="background-color: #349AC2;"/>
            </div>
        </form:form>
    </div>
</div>

<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
    <div class="sticky-top col-4">

        <div class="card shadow-sm mt-5 ml-5 mr-2 mb-5">
            <article class="card-group-item">
                <header class="card-header">
                    <h6 class="title"><spring:message code="materia.categoria" /></h6>
                </header>
                <div class="filter-content">
                    <c:url value="/materias" var="postFilterFormUrl"/>
                    <form:form modelAttribute="FilterForm" action="${postFilterFormUrl}" method="get">

                    <div class="card-body">
                        <c:forEach items="${categories}" var="category">
                            <div class="custom-control custom-checkbox">
                                <spring:message code="${category.toString()}" var="wordCategory"/>
                                <form:checkbox path="categories" value="${category}" label="${wordCategory}"/>
                            </div>
                        </c:forEach>
                    </div> <!-- card-body.// -->
                </div>
            </article> <!-- card-group-item.// -->
            <article class="card-group-item">
                <header class="card-header">
                    <h6 class="title"><spring:message code="materia.nivel" /></h6>
                </header>
                <div class="filter-content">
                    <div class="card-body">
                        <c:forEach items="${levels}" var="level">
                            <div class="custom-control custom-checkbox">
                                <spring:message code="${level.toString()}" var="word"/>
                                <form:checkbox path="levels"
                                               value="${level}" label="${word}"/>
                            </div>
                        </c:forEach>

                    </div> <!-- card-body.// -->
                </div>
            </article> <!-- card-group-item.// -->
            <div class="card-footer" style="text-align: right;">
                <spring:message code="materia.filtrar" var="Filtrar"/>
                <input type="submit" style="background-color: #349AC2;border-color: #349AC2;" class="btn btn-primary" value="${Filtrar}"/>
            </div>
            </form:form>
        </div>

    </div>
    <!--cursos o materias-->
    <div class="col-8">
        <div class="card shadow-sm mt-5 mr-5 mb-5">
            <h5 class="card-header">
                <spring:message code="professor.materias" arguments="${materias.size()}" />
            </h5>
            <div class="card-body">
                <c:if test="${materias.size() == 0}">
                    <%@include file="components/nothingToShow.jsp"%>
                </c:if>
                <c:forEach var="materia" items="${materias}">
                    <div class="row border-bottom pb-3 mb-3">
                        <div class="col-10">
                            <div class="text-muted"><spring:message code="${materia.category.toString()}"/>/<spring:message code="${materia.level.toString()}"/></div>
                            <div class=""><c:out value="${materia.name}"/></div>
                        </div>
                        <div>
<%--                            <c:if test="${materia.id == allContracts.}">--%>
<%--                                <button  href="<c:url  value="/nuevoContrato/materia/${subject.get().id}" />"--%>
<%--                                             class="btn text-white disabled" readonly data-toggle="tooltip" style="background-color: #8ECAE1" data-placement="bottom" title="Ya enseñas esta materia"><spring:message code="materia.ensenarMateria" /></button>--%>
<%--                            </c:if>--%>
<%--                            <c:if test="${}">--%>
                            <a href="<c:url value="/nuevoContrato/materia/${materia.id}" />"
                                   class="btn btn-outline-info"><spring:message code="materia.ensenarMateria" /></a>
<%--                            </c:if>--%>
                        </div>
                    </div>
                </c:forEach>
                <c:if test="${pages > 1}">
                <nav>
                    <ul class="pagination" style="justify-content: center;">
                        <c:choose>
                            <c:when test="${page == 1}">
                                <li class="page-item disabled">
                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="<c:url value="/materias?page=${page-1}"/>">
                                        <spring:message code="pag.anterior" /></a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="i" begin="1" end="${pages}">
                            <c:choose>
                                <c:when test="${i == page}">
                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="<c:url value="/materias?page=${i}"/>"><c:out value = "${i}"/></a></li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>

                        <c:choose>
                            <c:when test="${page == pages}">
                                <li class="page-item disabled">
                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">
                                    <a class="page-link" href="<c:url value="/materias?page=${page+1}"/>">
                                        <spring:message code="pag.siguiente" /></a>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
                </c:if>
            </div>
        </div>

    </div>


</div>

</div>

</body>
</html>
