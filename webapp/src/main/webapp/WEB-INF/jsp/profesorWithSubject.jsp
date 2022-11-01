<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title><spring:message code="form.contactar"/></title>


    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/pwsStyle.css"/>">
</head>
<body>
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>
<!--FOTO +NOMBRE-->
<%@include file="components/profesorSubjectHeader.jsp" %>
<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
    <div class="col-4">
        <!--biografia-->
        <div class="card shadow-sm mt-5 ml-5 mr-2">
            <div class="shadow-sm card-body">
                <c:set var="escapedN">
                    <c:out value="${prof.name}"/>
                </c:set>
                <h5 class="card-title"><spring:message code="professor.acerca" arguments="${escapedN}"/></h5>
                <p class="card-text"><c:out value="${prof.description}"/></p>
            </div>
        </div>
        <div class="card shadow-sm mt-5 ml-5 mr-2 mb-5">
            <h5 class="card-header">
                <spring:message code="professor.materias" arguments="${totalContracts.toString()}"/>
            </h5>
            <div class="card-body">
                <c:forEach var="contract" items="${contracts}">
                    <c:choose>
                        <c:when test="${contract.subject.id == subjectId}">
                            <a href="${pageContext.request.contextPath}/profesor/${prof.userId}/${contract.subject.id}"
                               style="text-decoration: none;color: inherit;color: black;">
                                <div class="text-muted font-weight-bold"><spring:message
                                        code="${contract.subject.category.toString()}"/>/<spring:message
                                        code="${contract.subject.level.toString()}"/></div>
                                <div class="border-bottom pb-3 mb-3 font-weight-bold"><c:out
                                        value="${contract.subject.name}"/></div>


                            </a>
                        </c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath}/profesor/${prof.userId}/${contract.subject.id}"
                               style="text-decoration: none;color: inherit;color: black;">
                                <div class="text-muted"><spring:message
                                        code="${contract.subject.category.toString()}"/>/<spring:message
                                        code="${contract.subject.level.toString()}"/></div>
                                <div class="border-bottom pb-3 mb-3"><c:out value="${contract.subject.name}"/></div>
                            </a>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
            </div>
            <div class="row align-items-center" style="justify-content: center;">
                <c:if test="${pages > 1}">
                    <nav>
                        <ul class="pagination" style="justify-content: center;">
                            <c:choose>
                                <c:when test="${page == 1}">
                                    <li class="page-item disabled">
                                        <span class="page-link"><spring:message code="pag.anterior"/></span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item">
                                        <a class="page-link"
                                           href="<c:url value="/profesor/${prof.userId}/${subjectId}?page=${page-1}"/>">
                                            <spring:message code="pag.anterior"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>

                            <c:forEach var="i" begin="1" end="${pages}">
                                <c:choose>
                                    <c:when test="${i == page}">
                                        <li class="page-item active"><a class="page-link" href="#"
                                                                        style="background-color: #349AC2;border-color: #349AC2;"><c:out
                                                value="${i}"/></a></li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="page-item"><a class="page-link"
                                                                 href="<c:url value="/profesor/${prof.userId}/${subjectId}?page=${i}"/>"><c:out
                                                value="${i}"/></a></li>
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
                                        <a class="page-link"
                                           href="<c:url value="/profesor/${prof.userId}/${subjectId}?page=${page+1}"/>">
                                            <spring:message code="pag.siguiente"/></a>
                                    </li>
                                </c:otherwise>
                            </c:choose>
                        </ul>
                    </nav>
                </c:if>
            </div>
        </div>
    </div>
    <!--cursos o materias-->
    <div class="col-8">
        <div class="card shadow-sm mt-5 mr-5 mb-5">
            <h5 class="card-header">
                <nav>
                    <div class="nav nav-pills " style="" id="nav-tab" role="tablist">
                        <a draggable="false" class="nav-item nav-link active" id="nav-info-tab" data-toggle="tab"
                           href="#nav-info"
                           role="tab" aria-controls="nav-info" aria-selected="true">
                            <spring:message code="professor.informacion"/></a>
                        <a draggable="false" class="nav-item nav-link" id="nav-review-tab" data-toggle="tab"
                           href="#nav-review"
                           role="tab" aria-controls="nav-review" aria-selected="false">
                            <spring:message code="clases.reviews"/>
                            <span>(<c:out value="${totalReviews}"/>)</span></a>
                    </div>
                </nav>

            </h5>


            <div class="card-body">
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-info" role="tabpanel"
                         aria-labelledby="nav-confirm-tab">

                        <div class="text-muted"><spring:message code="form.materia"/></div>
                        <div class="border-bottom pb-3 mb-3"><c:out value="${mainContract.subject.name}"/></div>

                        <div class="text-muted"><spring:message code="form.estudios"/></div>
                        <div class="border-bottom pb-3 mb-3"><c:out value="${prof.studies}"/></div>

                        <div class="text-muted"><spring:message code="clases.descripcion"/></div>
                        <div class="border-bottom pb-3 mb-3"><c:out value="${mainContract.description}"/></div>

                        <div class="text-muted"><spring:message code="form.telefono"/></div>
                        <div class="border-bottom pb-3 mb-3"><c:out value="${prof.phoneNumber}"/></div>

                        <div class="text-muted"><spring:message code="materia.precio"/></div>
                        <div class="border-bottom pb-3 mb-3"><c:out value="${mainContract.price}"/></div>

                        <div class="text-muted"><spring:message code="materia.presencialORemoto"/></div>
                        <c:choose>
                            <c:when test="${mainContract.remote && mainContract.local}">
                                <div class="border-bottom pb-3 mb-3"><spring:message code="form.ambas"/></div>
                            </c:when>
                            <c:when test="${mainContract.remote && !mainContract.local}">
                                <div class="border-bottom pb-3 mb-3"><spring:message code="materia.remoto"/></div>
                            </c:when>
                            <c:when test="${!mainContract.remote && mainContract.local}">
                                <div class="border-bottom pb-3 mb-3"><spring:message code="materia.local"/></div>
                            </c:when>
                            <c:otherwise>
                                <div class="border-bottom pb-3 mb-3"><spring:message
                                        code="materia.noInformacion"/></div>
                            </c:otherwise>
                        </c:choose>
                    </div>
                    <div class="tab-pane fade" id="nav-review" role="tabpanel" aria-labelledby="nav-progress-tab">
                        <!--professor-->
                        <c:if test="${reviews.size() == 0}">
                            <%@include file="components/nothingToShow.jsp" %>
                        </c:if>
                        <c:forEach var="review" items="${reviews}">
                            <div class="card shadow-sm col-12 mb-2">
                                <div class="row" style="display: flex; justify-content: space-between;">
                                    <div class="col-auto" style="align-self: center;">
                                        <img draggable="false"
                                             src="<c:url value="/user/image/${review.author.userId}"/>"
                                             class="avatar"
                                             style="border-radius: 50%;width: 70px;height: 70px;object-fit: cover;"/>
                                    </div>
                                    <div class="col align-self-center">
                                        <div class="row">
                                            <a href="${pageContext.request.contextPath}/student/${review.author.userId}"
                                               style="color: black;">
                                                <h5 class="card-title mb-0"><c:out value="${review.author.name} ${review.author.surname}"/></h5>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="card-body" style="text-align-last: right;">
                                        <div class="mt-0">
                                            <c:forEach begin="1" end="5" var="i">
                                                <c:choose>
                                                    <c:when test="${review.rating != null && 1>=review.rating && i<=review.rating}">
                                                        <i class="fas fa-star" style="color: gold"></i>
                                                    </c:when>
                                                    <c:when test="${review.rating != null && 3>=review.rating && i<=review.rating}">
                                                        <i class="fas fa-star" style="color: gold"></i>
                                                    </c:when>
                                                    <c:when test="${review.rating != null && 5>=review.rating && i<=review.rating}">
                                                        <i class="fas fa-star" style="color: gold"></i>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <i class="fas fa-star" style="color: darkgrey"></i>
                                                    </c:otherwise>
                                                </c:choose></c:forEach>
                                        </div>
                                        <c:if test="${review.message == null || review.message.length() <= 0}">
                                            <span><spring:message code="review.nocomment"/></span>
                                        </c:if>
                                        <c:if test="${review.message != null && review.message.length() > 0}">
                                            <span><c:out value="${review.message}"/></span>
                                        </c:if>
                                    </div>
                                </div>
                                <div style="position: absolute;font-size: xx-small;right: 6px;bottom: 3px;">${review.date}</div>
                            </div>
                        </c:forEach>

                        <!--paginación-->
                        <div class="row align-items-center" style="justify-content: center;">
                            <c:if test="${rPages > 1}">
                                <nav>
                                    <ul class="pagination" style="justify-content: center;">
                                        <c:choose>
                                            <c:when test="${rPage == 1}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/profesor/${prof.userId}/${subjectId}?rPage=${page-1}"/>">
                                                        <spring:message code="pag.anterior" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="i" begin="1" end="${rPages}">
                                            <c:choose>
                                                <c:when test="${i == rPage}">
                                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/profesor/${prof.userId}/${subjectId}?rPage=${i}"/>"><c:out value = "${i}"/></a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${rPage == rPages}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/profesor/${prof.userId}/${subjectId}?rPage=${page+1}"/>">
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
            <c:if test="${!isCurrentUser}">
                <div class="card-footer" style="text-align-last: right;">
                    <sec:authorize access="!isAuthenticated()">
                        <a href="<c:url value="/login"/>" class="btn text-white"
                           style="background-color: #349AC2"><spring:message code="form.contactar"/></a>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <form action="<c:url value="/profesor/${mainContract.professor.userId}/${mainContract.subject.id}"/>"
                              method="post">
                            <input type="submit"
                                   class="btn text-white" style="background-color: #349AC2;"
                                   value="<spring:message code="form.contactar"/>">
                        </form>
                    </sec:authorize>
                </div>
            </c:if>
        </div>

    </div>

</div>

</body>
</html>
