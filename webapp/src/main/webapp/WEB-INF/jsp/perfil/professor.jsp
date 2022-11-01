<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Establecer Contacto</title>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/profileStyle.css"/>">
</head>
<body style="background-color: ghostwhite">

<!--NAV BAR-->
<%@include file="../components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="../components/homeBg.jsp" %>

<c:url value="/professorProfile" var="postFormProfile"/>

<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
    <div class="col-6" style="margin:0 auto" >
        <div class="card shadow-sm ml-5 mt-5 mr-5 mb-5">
            <div class="card-header" style="display: flex;justify-content: space-between;">
                <h5 style="margin-bottom: 0px;align-self: center;">
                    <spring:message code="professor.materias" arguments="${activeContracts.size().toString()}"/>
                </h5>
                <a href="<c:url value="/materias" />"
                    class="btn btn-outline-info"><spring:message code="materia.agregarMateria" /></a>
            </div>

            <div class="card-body">
                <c:forEach var="activeContract" items="${activeContracts}">
                    <div class="row border-bottom pb-3 mb-3" style="justify-content: space-between;">
                        <div class="col-6">
<%--                            <a href="${pageContext.request.contextPath}/materia/${activeContract.subject.id}">--%>
                            <div class="text-muted">  <spring:message code="${activeContract.subject.category.toString()}"/>/<spring:message code="${activeContract.subject.level.toString()}"/></div>
                            <div><c:out value="${activeContract.subject.name}"/></div>
                            <div class="text-muted">
                                <c:set var="profDescription">
                                    <spring:message code="professor.descripcion" arguments="${activeContract.description}"/>
                                </c:set>
                                <c:out value="${profDescription}" />
                            </div>
<%--                            </a>--%>
                        </div>
                        <div class="col-auto">
                        <form method="post" action="<c:url value="/professorProfileStatus/${activeContract.id}/PAUSED"/>">
                            <input type="submit"
                                   class="btn btn-info" style="align-self: center;width: 100%;"
                            value="<spring:message code="professor.pausar" />">

                        </form>
                        <form action="<c:url value="/professorProfile/${activeContract.subject.id}"/>" method="post">
                            <input type="submit"
                                   class="btn btn-danger" style="align-self: center;width: 100%;" value="<spring:message code="professor.baja"/>">
                        </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>

    <div class="col-6" style="margin:0 auto">
        <div class="card shadow-sm mt-5 mr-5 mb-5">
            <h5 class="card-header">
                <spring:message code="professor.materiasPausa" arguments="${pausedContracts.size().toString()}"/>
            </h5>
            <div class="card-body">
                <c:forEach var="pausedContract" items="${pausedContracts}">
                    <div class="row border-bottom pb-3 mb-3" style="justify-content: space-between;">
                        <div class="col-6">
<%--                            <a href="${pageContext.request.contextPath}/materia/${pausedContract.subject.id}">--%>
                            <div class="text-muted"> <spring:message code="${pausedContract.subject.category.toString()}"/>/<spring:message code="${pausedContract.subject.level.toString()}"/></div>
                            <div><c:out value="${pausedContract.subject.name}"/></div>
                            <div class="text-muted">
                                <c:set var="profDescription2">
                                    <spring:message code="professor.descripcion" arguments="${pausedContract.description}"/>
                                </c:set>
                                <c:out value="${profDescription2}" />
<%--                            </a>--%>
                            </div>
                        </div>
                        <div class="col-auto">
                        <form method="post" action="<c:url value="/professorProfileStatus/${pausedContract.id}/ACTIVE"/>">
                              <input type="submit"
                           class="btn btn-info" style="align-self: center;width: 100%;" value="<spring:message code="professor.renaudar" />">
                        </form>
                        <form action="<c:url value="/professorProfile/${pausedContract.subject.id}"/>" method="post">
                            <input type="submit" class="btn btn-danger" style="align-self: center;width: 100%;" value="<spring:message code="professor.baja"/>">
                        </form>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
</div>
</body>
</html>
