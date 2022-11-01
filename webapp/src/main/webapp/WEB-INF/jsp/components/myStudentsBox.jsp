<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/indexStyle.css"/>">

    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>

</head>
<body>

<section id="tabs" class="project-tab">
    <div class="container" style="background-color: white; margin-top: 2%;">
        <div class="row">
            <div class="col-md-12">
                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                        <a class="nav-item nav-link active" id="nav-confirm-tab" data-toggle="tab" href="#nav-confirm" role="tab" aria-controls="nav-confirm" aria-selected="true"><spring:message code="materia.porConfirmar"/></a>
                        <a class="nav-item nav-link" id="nav-progress-tab" data-toggle="tab" href="#nav-progress" role="tab" aria-controls="nav-progress" aria-selected="false"><spring:message code="materia.enProceso"/></a>
                        <a class="nav-item nav-link" id="nav-finished-tab" data-toggle="tab" href="#nav-finished" role="tab" aria-controls="nav-finished" aria-selected="false"><spring:message code="materia.terminada"/></a>
                        <a class="nav-item nav-link" id="nav-cancelled-tab" data-toggle="tab" href="#nav-cancelled" role="tab" aria-controls="nav-cancelled" aria-selected="false"><spring:message code="materia.cancelada"/></a>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-confirm" role="tabpanel" aria-labelledby="nav-confirm-tab">
                        <div class="row" style="margin: 2%">
                            <c:forEach var="contract" items="${contracts}">
                                <!--professor-->
                                <div class="col-lg-4">
                                    <div class="our-team-main">
                                        <div class="team-front">
                                            <img draggable="false" src="<c:url value="/user/image/${contract.professor.userId}"/>" class="avatar" style="border-radius: 50%;width: 117px;height: 117px;object-fit: cover;"/>
                                            <h3><c:out value="${contract.professor.name} ${contract.professor.surname}"/></h3>
                                            <c:set var="escapedProfessorStudies">
                                                <c:out value="${contract.professor.studies}"/>
                                            </c:set>
                                            <p><spring:message code="materia.estudios" arguments="${escapedProfessorStudies}" /></p>
                                        </div>

                                        <div class="team-back" style="display: flex;flex-direction: column;justify-content: space-between;">
                                            <input type="submit"
                                                   class="btn" style="align-self: center;width: -webkit-fill-available;" value="<spring:message code="clase.Informacion"/>">
                                            <div>
                                                <input type="submit"
                                                       class="btn btn-primary" style="align-self: center;width: -webkit-fill-available; background-color: #009AC2;" value="<spring:message code="clase.Aceptar"/>">
                                                <input type="submit"
                                                       class="btn btn-danger" style="align-self: center;width: -webkit-fill-available;" value="<spring:message code="clase.Rechazar"/>">
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="nav-progress" role="tabpanel" aria-labelledby="nav-progress-tab">
                        <div class="row" style="margin: 2%">
                            <c:forEach var="contract" items="${contracts}">
                                <!--professor-->
                                <div class="col-lg-4">
                                    <div class="our-team-main">
                                        <div class="team-front">
                                            <img draggable="false" src="<c:url value="/user/image/${contract.professor.userId}"/>" class="avatar" style="border-radius: 50%;width: 117px;height: 117px;object-fit: cover;"/>
                                            <h3><c:out value="${contract.professor.name} ${contract.professor.surname}"/></h3>
                                            <c:set var="escapedProfessorStudies">
                                                <c:out value="${contract.professor.studies}"/>
                                            </c:set>
                                            <p><spring:message code="materia.estudios" arguments="${escapedProfessorStudies}" /></p>
                                        </div>

                                        <div class="team-back">

                                        </div>

                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="nav-finished" role="tabpanel" aria-labelledby="nav-finished-tab">
                        <div class="row" style="margin: 2%">
                            <c:forEach var="contract" items="${contracts}">
                                <!--professor-->
                                <div class="col-lg-4">
                                    <div class="our-team-main">
                                        <div class="team-front">
                                            <img draggable="false" src="<c:url value="/user/image/${contract.professor.userId}"/>" class="avatar" style="border-radius: 50%;width: 117px;height: 117px;object-fit: cover;"/>
                                            <h3><c:out value="${contract.professor.name} ${contract.professor.surname}"/></h3>
                                            <c:set var="escapedProfessorStudies">
                                                <c:out value="${contract.professor.studies}"/>
                                            </c:set>
                                            <p><spring:message code="materia.estudios" arguments="${escapedProfessorStudies}" /></p>
                                        </div>

                                        <div class="team-back">

                                        </div>

                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="nav-cancelled" role="tabpanel" aria-labelledby="nav-cancelled-tab">
                        <div class="row" style="margin: 2%">
                            <c:forEach var="contract" items="${contracts}">
                                <!--professor-->
                                <div class="col-lg-4">
                                    <div class="our-team-main">
                                        <div class="team-front">
                                            <img draggable="false" src="<c:url value="/user/image/${contract.professor.userId}"/>" class="avatar" style="border-radius: 50%;width: 117px;height: 117px;object-fit: cover;"/>
                                            <h3><c:out value="${contract.professor.name} ${contract.professor.surname}"/></h3>
                                            <c:set var="escapedProfessorStudies">
                                                <c:out value="${contract.professor.studies}"/>
                                            </c:set>
                                            <p><spring:message code="materia.estudios" arguments="${escapedProfessorStudies}" /></p>
                                        </div>

                                        <div class="team-back">

                                        </div>

                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>
</body>