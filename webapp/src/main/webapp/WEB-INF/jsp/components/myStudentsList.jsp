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


    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">

</head>
<body>

<section id="tabs" class="project-tab">
    <div class="container rounded" style="background-color: white; margin-top: 2%;">
        <div class="row">
            <div class="col-md-12">
                <h5 class="card-header" style="background-color: white; text-transform: uppercase;text-align: center;">
                    <spring:message code="dropdown.alumnos" />
                </h5>
                <nav>
                    <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                        <a draggable="false" class="nav-item nav-link active" id="nav-confirm-tab" data-toggle="tab" href="#nav-confirm"
                           role="tab" aria-controls="nav-confirm" aria-selected="true"><spring:message
                                code="clase.solicitadas" arguments="${totalNew}"/></a>
                        <a draggable="false" class="nav-item nav-link" id="nav-progress-tab" data-toggle="tab" href="#nav-progress"
                           role="tab" aria-controls="nav-progress" aria-selected="false"><spring:message
                                code="clase.proceso" arguments="${totalProgress}"/></a>
                        <a draggable="false" class="nav-item nav-link" id="nav-finished-tab" data-toggle="tab" href="#nav-finished"
                           role="tab" aria-controls="nav-finished" aria-selected="false"><spring:message
                                code="clase.terminadas" arguments="${totalFinished}"/></a>
                        <a draggable="false" class="nav-item nav-link" id="nav-cancelled-tab" data-toggle="tab" href="#nav-cancelled"
                           role="tab" aria-controls="nav-cancelled" aria-selected="false"><spring:message
                                code="clase.canceladas" arguments="${totalCancelled}"/></a>
                    </div>
                </nav>
                <div class="tab-content" id="nav-tabContent">
                    <div class="tab-pane fade show active" id="nav-confirm" role="tabpanel"
                         aria-labelledby="nav-confirm-tab">
                        <div class="row" style="margin: 2%">
                            <c:if test="${newLessons.size() == 0}">
                                <%@include file="nothingToShow.jsp"%>
                            </c:if>
                            <c:forEach var="lesson" items="${newLessons}">
                                <div class="card col-12" style="margin-bottom: 30px;padding: 0;">
                                    <div class="card-header">
                                            ${lesson.subject.name}
                                        <span class="text-muted"> (${lesson.subject.level}/${lesson.subject.category})</span>
                                    </div>
                                    <div class="card-body" style="display: flex; justify-content: space-between;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <img draggable="false" src="<c:url value="/user/image/${lesson.student.userId}"/>"
                                                     class="avatar"
                                                     style="border-radius: 50%;width: 70px;height: 70px;object-fit: cover;"/>
                                            </div>
                                            <div style="align-self: center;">
                                                <a href="${pageContext.request.contextPath}/student/${lesson.student.userId}" style="color: black;">
                                                <h5 class="card-title" style="margin-bottom: 0 !important;"><c:out value="${lesson.student.name} ${lesson.student.surname} - $${lesson.price}"/></h5>
                                                </a>
                                                <h7 class="card-title"><c:out value="${lesson.student.email}"/></h7>
                                            </div>
                                        </div>
                                                <div class="row" style="align-content: center">
                                                    <form class="col-auto" action="<c:url value="/myStudents/${lesson.lessonId}/0"/>"
                                                          method="post">
                                                        <input type="submit"
                                                               class="btn btn-outline-danger"
                                                               style="align-self: center;"
                                                               value="<spring:message code="myStudents.rechazarAlumno"/>">
                                                    </form>
                                                    <form action="<c:url value="/myStudents/${lesson.lessonId}/IN_PROCESS"/>"
                                                          method="post">
                                                        <input type="submit"
                                                               class="btn btn-outline-success mr-2" style="align-self: center;"
                                                               value="<spring:message code="myStudents.aceptarAlumno"/>">
                                                    </form>
                                                </div>

                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <!--paginacion-->
                        <div class="row align-items-center" style="justify-content: center;">
                            <c:if test="${nPages > 1}">
                                <nav>
                                    <ul class="pagination" style="justify-content: center;">
                                        <c:choose>
                                            <c:when test="${nPage == 1}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?nPage=${nPage-1}"/>">
                                                        <spring:message code="pag.anterior" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="i" begin="1" end="${nPages}">
                                            <c:choose>
                                                <c:when test="${i == nPage}">
                                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/myStudents?nPage=${i}"/>"><c:out value = "${i}"/></a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${nPage == nPages}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?nPage=${nPage+1}"/>">
                                                        <spring:message code="pag.siguiente" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>


                    </div>
                    <div class="tab-pane fade" id="nav-progress" role="tabpanel" aria-labelledby="nav-progress-tab">
                        <div class="row" style="margin: 2%">
                            <c:if test="${inProgressLessons.size() == 0}">
                                <%@include file="nothingToShow.jsp"%>
                            </c:if>
                            <c:forEach var="lesson" items="${inProgressLessons}">
                                <!--professor-->
                                <div class="card col-12" style="margin-bottom: 30px;padding: 0;">
                                    <div class="card-header">
                                            ${lesson.subject.name}
                                        <span class="text-muted"> (${lesson.subject.level}/${lesson.subject.category})</span>
                                    </div>
                                    <div class="card-body" style="display: flex; justify-content: space-between;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <img draggable="false" src="<c:url value="/user/image/${lesson.student.userId}"/>"
                                                     class="avatar"
                                                     style="border-radius: 50%;width: 70px;height: 70px;object-fit: cover;"/>
                                            </div>
                                            <div style="align-self: center;">
                                                <a href="${pageContext.request.contextPath}/student/${lesson.student.userId}" style="color: black;">
                                                <h5 class="card-title" style="margin-bottom: 0 !important;"><c:out value="${lesson.student.name} ${lesson.student.surname} - $${lesson.price}"/></h5>
                                                </a>
                                                <h7 class="card-title"><c:out value="${lesson.student.email}"/></h7>
                                                <br>
                                                <h7><spring:message code="rooms.archivos"
                                                                    arguments="${lesson.fileList.size()}"/>
                                                </h7>
                                                <c:if test="${lesson.schedule == null || lesson.schedule.length() <= 0}">
                                                    <br>
                                                    <h7><spring:message code="rooms.emptyschedule"/></h7>
                                                </c:if>
                                                <c:if test="${lesson.schedule != null && lesson.schedule.length() > 0}">
                                                    <br>
                                                    <c:set var="escapedS">
                                                        <c:out value="${lesson.schedule}"/>
                                                    </c:set>
                                                    <h7><spring:message code="rooms.schedule" arguments="${escapedS}"/></h7>
                                                </c:if>
                                                <c:if test="${lesson.meetingLink == null || lesson.schedule.length() <= 0}">
                                                    <br>
                                                    <h7><spring:message
                                                            code="rooms.emptylink"/></h7>
                                                </c:if>
                                                <c:if test="${lesson.meetingLink != null && lesson.schedule.length() > 0}">
                                                    <br>
                                                    <c:set var="escapedM">
                                                        <c:out value="${lesson.meetingLink}"/>
                                                    </c:set>
                                                    <h7><spring:message code="rooms.link" arguments="${escapedM}"/></h7>
                                                </c:if>
                                            </div>
                                        </div>
                                                    <div class="row" style="align-content: center">
                                                        <form class="mr-2"
                                                              action="<c:url value="/myStudents/${lesson.lessonId}/CANCELLED"/>"
                                                              method="post">
                                                            <input type="submit"
                                                                   class="btn btn-outline-danger"
                                                                   style="align-self: center;"
                                                                   value="<spring:message code="myStudents.cancelarClase"/>">
                                                        </form>
                                                        <form action="<c:url value="/myStudents/${lesson.lessonId}/FINISHED"/>"
                                                              method="post">
                                                            <input type="submit"
                                                                   class="btn btn-outline-primary dark mr-2"
                                                                   style="align-self: center;"
                                                                   value="<spring:message code="myStudents.terminarClase"/>">
                                                        </form>
                                                        <a href="${pageContext.request.contextPath}/class/${lesson.lessonId}">
                                                            <button class="btn btn-outline-success mr-2">
                                                                <spring:message code="clase.irAClase"/>
                                                            </button>
                                                        </a>

                                        </div>
                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>

                        <!--paginacion-->
                        <div class="row align-items-center" style="justify-content: center;">
                            <c:if test="${pPages > 1}">
                                <nav>
                                    <ul class="pagination" style="justify-content: center;">
                                        <c:choose>
                                            <c:when test="${pPage == 1}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?pPage=${pPage-1}"/>">
                                                        <spring:message code="pag.anterior" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="i" begin="1" end="${pPages}">
                                            <c:choose>
                                                <c:when test="${i == pPage}">
                                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/myStudents?pPage=${i}"/>"><c:out value = "${i}"/></a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${pPage == pPages}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?pPage=${pPage+1}"/>">
                                                        <spring:message code="pag.siguiente" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>

                    </div>
                    <div class="tab-pane fade" id="nav-finished" role="tabpanel" aria-labelledby="nav-finished-tab">
                        <div class="row" style="margin: 2%">
                            <c:if test="${finishedLessons.size() == 0}">
                                <%@include file="nothingToShow.jsp"%>
                            </c:if>
                            <c:forEach var="lesson" items="${finishedLessons}">
                                <!--professor-->
                                <div class="card col-12" style="margin-bottom: 30px;padding: 0;">
                                    <div class="card-header">
                                            ${lesson.subject.name}
                                        <span class="text-muted"> (${lesson.subject.level}/${lesson.subject.category})</span>
                                    </div>
                                    <div class="card-body" style="display: flex;justify-content: space-between;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <img draggable="false" src="<c:url value="/user/image/${lesson.student.userId}"/>"
                                                     class="avatar"
                                                     style="border-radius: 50%;width: 70px;height: 70px;object-fit: cover;"/>
                                            </div>
                                            <div style="align-self: center;">
                                                <a href="${pageContext.request.contextPath}/student/${lesson.student.userId}" style="color: black;">
                                                <h5 class="card-title" style="margin-bottom: 0 !important;"><c:out value="${lesson.student.name} ${lesson.student.surname}"/></h5>
                                                </a>
                                                <h7 class="card-title "><c:out value="${lesson.student.email}"/></h7>
                                            </div>
                                        </div>
                                            <div class="row" >
                                                <div class="col-auto" style="align-self: center">
                                                   <div style="display: flex;justify-content: flex-end;">
                                                       <c:choose>
                                                           <c:when test="${lesson.review == null}">
                                                               <span class="font-italic"><spring:message code="review.no"/> </span>
                                                           </c:when>
                                                           <c:otherwise>
                                                               <span class="font-italic"><spring:message code="review.si"/></span>
                                                           </c:otherwise>
                                                       </c:choose>
                                                   </div>
                                                    <c:if test="${lesson.review != null}">
                                                        <div style="margin-left: 0;margin-right: 0;display: flex;justify-content: flex-end;">
                                                            <div class="">
                                                            <c:forEach begin="1" end="5" var="i">
                                                                <c:choose>
                                                                    <c:when test="${lesson.review != null && 1>=lesson.review.rating && i<=lesson.review.rating}">
                                                                        <i class="fas fa-star" style="color: gold"></i>
                                                                    </c:when>
                                                                    <c:when test="${lesson.review != null && 3>=lesson.review.rating && i<=lesson.review.rating}">
                                                                        <i class="fas fa-star" style="color: gold"></i>
                                                                    </c:when>
                                                                    <c:when test="${lesson.review != null && 5>=lesson.review.rating && i<=lesson.review.rating}">
                                                                        <i class="fas fa-star" style="color: gold"></i>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <i class="fas fa-star" style="color: darkgrey;"></i>
                                                                    </c:otherwise>
                                                                </c:choose></c:forEach>
                                                            </div>
                                                        </div>
                                                        <div class="ml-4" style="text-align-last: right;">
                                                            <span><c:out value="${lesson.review.message}"/></span>
                                                        </div>
                                                        <div style="position: absolute;font-size: xx-small;right: 6px;bottom: -15px;">${lesson.review.date}</div>
                                                    </c:if>
                                                </div>
                                            </div>
                                        </div>

                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>

                        <!--paginacion-->
                        <div class="row align-items-center" style="justify-content: center;">
                            <c:if test="${fPages > 1}">
                                <nav>
                                    <ul class="pagination" style="justify-content: center;">
                                        <c:choose>
                                            <c:when test="${fPage == 1}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?fPage=${fPage-1}"/>">
                                                        <spring:message code="pag.anterior" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="i" begin="1" end="${fPages}">
                                            <c:choose>
                                                <c:when test="${i == fPage}">
                                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/myStudents?fPage=${i}"/>"><c:out value = "${i}"/></a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${fPage == fPages}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?fPage=${fPage+1}"/>">
                                                        <spring:message code="pag.siguiente" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </ul>
                                </nav>
                            </c:if>
                        </div>

                    </div>
                    <div class="tab-pane fade" id="nav-cancelled" role="tabpanel" aria-labelledby="nav-cancelled-tab">
                        <div class="row" style="margin: 2%">
                            <c:if test="${cancelledLessons.size() == 0}">
                                <%@include file="nothingToShow.jsp"%>
                            </c:if>
                            <c:forEach var="lesson" items="${cancelledLessons}">
                                <!--professor-->
                                <div class="card col-12" style="margin-bottom: 30px;padding: 0;">
                                    <div class="card-header">
                                            ${lesson.subject.name}
                                        <span class="text-muted"> (${lesson.subject.level}/${lesson.subject.category})</span>
                                    </div>
                                    <div class="card-body" style="display: flex; justify-content: space-between;">
                                        <div class="row">
                                            <div class="col-auto">
                                                <img draggable="false" src="<c:url value="/user/image/${lesson.student.userId}"/>"
                                                     class="avatar"
                                                     style="border-radius: 50%;width: 70px;height: 70px;object-fit: cover;"/>
                                            </div>
                                            <div style="align-self: center;">
                                                <a href="${pageContext.request.contextPath}/student/${lesson.student.userId}" style="color: black;">
                                                <h5 class="card-title" style="margin-bottom: 0 !important;"><c:out value="${lesson.student.name} ${lesson.student.surname}"/></h5>
                                                </a>
                                                <h7 class="card-title"><c:out value="${lesson.student.email}"/></h7>
                                            </div>
                                        </div>

                                                    <div class="row" style="align-content: center">
                                                        <form class="col-auto"
                                                              action="<c:url value="/myStudents/${lesson.lessonId}/0"/>"
                                                              method="post">
                                                            <input type="submit"
                                                                   class="btn btn-outline-danger"
                                                                   style="align-self: center;"
                                                                   value="<spring:message code="myStudents.borrarCancelado"/>">
                                                        </form>
                                                        <form action="<c:url value="/myStudents/${lesson.lessonId}/IN_PROCESS"/>"
                                                              method="post">
                                                            <input type="submit"
                                                                   class="btn btn-outline-success mr-2"
                                                                   style="align-self: center;"
                                                                   value="<spring:message code="myStudents.nuevaClase"/>">
                                                        </form>
                                        </div>
                                    </div>
                                </div>
                                <!--professor-->
                            </c:forEach>
                        </div>
                        <!--paginacion-->
                        <div class="row align-items-center" style="justify-content: center;">
                            <c:if test="${cPages > 1}">
                                <nav>
                                    <ul class="pagination" style="justify-content: center;">
                                        <c:choose>
                                            <c:when test="${cPage == 1}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.anterior" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?cPage=${cPage-1}"/>">
                                                        <spring:message code="pag.anterior" /></a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>

                                        <c:forEach var="i" begin="1" end="${cPages}">
                                            <c:choose>
                                                <c:when test="${i == cPage}">
                                                    <li class="page-item active"><a class="page-link" href="#" style="background-color: #349AC2;border-color: #349AC2;"><c:out value = "${i}"/></a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <li class="page-item"><a class="page-link" href="<c:url value="/myStudents?cPage=${i}"/>"><c:out value = "${i}"/></a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </c:forEach>

                                        <c:choose>
                                            <c:when test="${cPage == cPages}">
                                                <li class="page-item disabled">
                                                    <span class="page-link"><spring:message code="pag.siguiente" /></span>
                                                </li>
                                            </c:when>
                                            <c:otherwise>
                                                <li class="page-item">
                                                    <a class="page-link" href="<c:url value="/myStudents?cPage=${cPage+1}"/>">
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
    </div>
</section>
</body>