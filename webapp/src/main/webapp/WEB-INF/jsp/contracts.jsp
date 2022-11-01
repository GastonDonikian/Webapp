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

    <title><spring:message code="general.materias"/></title>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/materiaStyle.css"/>">

    <style>
        .page-btn{
            margin-inline: 1em;
        }
        label{
            margin-left: 0.2rem;
        }
    </style>

</head>
<body style="background-color: ghostwhite">
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>

<div class="shadow-sm" style="background-color: white;">
    <div class="pl-5 ml-3 mr-3 pt-4 pr-5 pb-3">
        <c:url value="/contracts" var="postFilterFormUrl"/>
        <form:form modelAttribute="filterContractForm" action="${postFilterFormUrl}" method="get" id="filterContract">
        <spring:message code="materia.buscar" var="Buscar"/>
        <div class="input-group">
            <form:input class="form-control" id="search" placeholder="${Buscar}" type="text" path="search"/>
            <button onclick="paginate(0)" class="btn text-white input-group-append"
                    style="background-color: #349AC2;">${Buscar}</button>
        </div>
    </div>
</div>


<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
    <div class="sticky-top col-4" style="margin-bottom: 2%;">

        <div class="card shadow-sm mt-5 ml-5 mr-2">

            <article class="card-group-item">
                <div class="filter-content">

                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><spring:message code="materia.categoria"/></h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${categories}" var="category">
                                    <div class="custom-control custom-checkbox">
                                        <spring:message code="${category.toString()}" var="catt"/>
                                        <form:checkbox path="categories" value="${category}" label="${catt}"/>
                                    </div>
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                    <article class="card-group-item">
                        <header class="card-header">
                            <h6 class="title"><spring:message code="materia.nivel"/></h6>
                        </header>
                        <div class="filter-content">
                            <div class="card-body">
                                <c:forEach items="${levels}" var="level">
                                    <div class="custom-control custom-checkbox">
                                        <spring:message code="${level.toString()}" var="lvll"/>
                                        <form:checkbox path="levels"
                                                       value="${level}" label="${lvll}"/>
                                    </div>
                                </c:forEach>
                            </div> <!-- card-body.// -->
                        </div>
                    </article> <!-- card-group-item.// -->
                    <header class="card-header">
                        <h6 class="title"><spring:message code="materia.profLocalidad"/></h6>
                    </header>
                    <div class="card-body">
                        <div class="form-group row" style="margin: 0;">
                            <spring:message code="form.seleccionarOrden" var="seleccionar"/>
                            <c:forEach items="${locations}" var="location">
                                <div class="custom-control custom-checkbox">
                                    <form:checkbox path="localidades"
                                                   value="${location}" label="${location.toString()}"/>
                                </div>
                            </c:forEach>
                                <%--                            <form:select path="localidades" class="form-control col-sm-10">--%>
                                <%--                                <form:errors path="localidades" cssStyle="color: red" element="p"/>--%>
                                <%--                                <c:forEach items="${locations}" var="location">--%>
                                <%--                                    <form:option value="${location}">--%>
                                <%--                                        <form:checkbox path="localidades" value="${location}"/>--%>
                                <%--                                        <form:label path="localidades">--%>
                                <%--                                            <spring:message code="${location}" var="opcion"/>--%>
                                <%--                                            <c:out value="${opcion}"/>--%>
                                <%--                                        </form:label>--%>
                                <%--                                    </form:option>--%>
                                <%--                                </c:forEach>--%>
                                <%--                            </form:select>--%>
                        </div>

                    </div> <!-- card-body.// -->
                </div>
            </article> <!-- card-group-item.// -->
            <article class="card-group-item">
                <header class="card-header">
                    <h6 class="title"><spring:message code="materia.ordenarPor"/></h6>
                </header>
                <div class="filter-content">
                    <div class="card-body">
                        <div class="form-group row" style="margin: 0;">
                            <spring:message code="form.seleccionarUbicacion" var="seleccionar"/>
                            <form:select path="orderBy" class="form-control col-sm-10"
                                         aria-label="Ordenar por">
                                <form:option value="" label="${seleccionar}"/>
                                <form:errors path="orderBy" cssStyle="color: red" element="p"/>
                                <spring:message code="${orderBy[0]}" var="option1"/>
                                <form:option value="${orderBy[0]}">
                                    <c:out value="${option1}"/>
                                </form:option>
                                <spring:message code="${orderBy[1]}" var="option2"/>
                                <form:option value="${orderBy[1]}">
                                    <c:out value="${option2}"/>
                                </form:option>
                            </form:select>
                        </div>
                    </div> <!-- card-body.// -->
                </div>
            </article> <!-- card-group-item.// -->
            <article class="card-group-item">
                <header class="card-header">
                    <h6 class="title"><spring:message code="materia.presencialORemoto"/></h6>
                </header>
                <div class="">

                    <div class="card-body">
                        <div>
                            <spring:message code="materia.local" var="local"/>
                            <form:checkbox path="local" value="Local" label="${local}"/>
                        </div>
                        <div>
                            <spring:message code="materia.remoto" var="remoto"/>
                            <form:checkbox path="remote" value="Remoto" label="${remoto}"/>
                        </div>
                    </div> <!-- card-body.// -->
                </div>
            </article>
            <div class="card-footer" style="text-align: right;">
                <spring:message code="materia.filtrar" var="materiaFiltrar"/>
                <button onclick="paginate(0)"  class="btn btn-primary" style="background-color: #349AC2;border-color:#349AC2;"
                        >${materiaFiltrar}</button>
            </div>

        </div>

    </div>
    <!--profesores -->
    <div class="col-8 box-of-professors">
        <div class="row mt-5">
            <c:if test="${contracts.size() == 0}">
                <%@include file="components/nothingToShow.jsp" %>
            </c:if>
            <c:forEach var="contract" items="${contracts}">
                <!--professor-->
                <div class="col-lg-4">
                    <a href="${pageContext.request.contextPath}/profesor/${contract.professor.userId}/${contract.subject.id}"
                       style="text-decoration: none;color: inherit;color: black;">
                        <div class="our-team-main">
                            <div class="team-front">
                                <img draggable="false" src="<c:url value="/user/image/${contract.professor.userId}"/>"
                                     class="avatar"
                                     style="border-radius: 50%;width: 117px;height: 117px;object-fit: cover;"/>
                                <h3><c:out value="${contract.professor.name} ${contract.professor.surname}"/></h3>
                                <h6><c:out value="${contract.subject.name}"/></h6>
                                <p><c:forEach begin="1" end="5" var="i">
                                    <c:choose>
                                        <c:when test="${contract.rating != null && 1>=contract.rating && i<=contract.rating}">
                                            <i class="fas fa-star" style="color: gold"></i>
                                        </c:when>
                                        <c:when test="${contract.rating != null && 3>=contract.rating && i<=contract.rating}">
                                            <i class="fas fa-star" style="color: gold"></i>
                                        </c:when>
                                        <c:when test="${contract.rating != null && 5>=contract.rating && i<=contract.rating}">
                                            <i class="fas fa-star" style="color: gold"></i>
                                        </c:when>
                                        <c:otherwise>
                                            <i class="fas fa-star" style="color: darkgrey"></i>
                                        </c:otherwise>
                                    </c:choose></c:forEach></p>
                                <p style="font-size: small;">
                                    <c:set var="contractPrice">
                                        <%--                                        <spring:message code="materia.price" arguments="${contract.price}"/>--%>
                                        <spring:message code="materia.price" arguments="${contract.price}"/>
                                    </c:set>
                                    <c:out value="${contractPrice}"/>
                                </p>
                            </div>

                            <div class="team-back">
                                <div>
                                    <c:set var="escapedD">
                                        <c:out value="${contract.description}"/>
                                    </c:set>
                                    <span style="font-weight: bold;"><spring:message code="form.descripcion" arguments="${escapedD}"/></span>
                                </div>
                                <div>
                                    <c:set var="escapedStudies">
                                        <c:out value="${contract.professor.studies}"/>
                                    </c:set>
                                    <span style="font-weight: bold; "><spring:message code="materia.estudiosEn" arguments="${escapedStudies}"/></span>
                                </div>
                                <div>
                                    <span style="font-weight: bold; "><spring:message
                                            code="materia.profesorLocalidad" arguments="${contract.professor.location.toString()}"/> </span>
                                </div>
                                <span style="font-weight: bold; "><spring:message
                                        code="materia.presencialORemoto"/> </span>
                                <ul><c:if test="${contract.remote == true && contract.local == false}">

                                    <li><span><spring:message code="materia.remoto"/> </span></li>

                                </c:if>
                                    <c:if test="${contract.local == true && contract.remote == false}">

                                        <li><span><spring:message code="materia.local"/> </span></li>

                                    </c:if>
                                    <c:if test="${contract.local == true && contract.remote == true}">
                                        <li><span><spring:message code="materia.ambas"/> </span></li>

                                    </c:if>
                                </ul>

                            </div>

                        </div>
                    </a>
                </div>
                <!--professor-->
            </c:forEach>
        </div>
        <!--paginacion-->
        <div class="row align-items-center" style="justify-content: center;">
            <c:if test="${pages >1}">
                <form:input type="hidden" class="form-control" id="page" path="page"/>
                <nav>
                    <ul class="pagination" style="justify-content: center;">
                        <c:choose>
                            <c:when test="${page == 1}">
                                <li class="page-item disabled">
                                    <span class="page-link btn input-group-append "><spring:message code="pag.anterior"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="page-item">

                                    <button onclick="paginate(${page - 1})" class="page-link page-link btn input-group-append"
                                            style="background-color: white;color: black"><spring:message
                                            code="pag.anterior"/></button>
                                </li>

                            </c:otherwise>
                        </c:choose>

                        <c:forEach var="i" begin="1" end="${pages}">
                            <c:choose>
                                <c:when test="${i == page}">
                                    <li class="page-item active">
                                        <a class="page-link btn input-group-append" href="#"
                                           style="background-color: #349AC2;border-color: #349AC2;"><c:out
                                                value="${i}"/></a>
                                    </li>
                                </c:when>
                                <c:otherwise>

                                    <li class="page-item">
                                            <%--                                        <form:input cssStyle="display:none " class="form-control" path="page" value="${i}"/>--%>
                                        <button onclick="paginate(${i})" class="btn input-group-append "
                                                value="${i}"
                                                style="background-color: white;color: black"><c:out value="${i}"/></button>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>

                        <c:choose>
                            <c:when test="${page == pages}">
                                <li class="page-item disabled">
                                    <span class="page-link"><spring:message code="pag.siguiente"/></span>
                                </li>
                            </c:when>
                            <c:otherwise>

                                <li class="page-item">
                                        <%--                                    <form:input cssStyle="display:none " class="form-control" id="page" path="page" value="${page + 1}"/>--%>
                                    <button onclick="paginate(${page + 1})" class="page-link"
                                            style="background-color: white;color: black"><spring:message
                                            code="pag.siguiente"/></button>
                                </li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </nav>
            </c:if>
            </form:form>
        </div>
    </div>
</div>

</div>


</body>

<script>
    function paginate(value) {
        let form = document.getElementById('page');
        form.value = value;
        document.getElementById("filterContract").submit();
    }
</script>
</html>
