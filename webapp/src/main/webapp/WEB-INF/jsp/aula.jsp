<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html lang="en">
<head>
    <link rel="shortcut icon" type="image/x-icon" href="<c:url value="/resources/images/icon.png"/>">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
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

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/styles/aulaStyle.css"/>">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/b99c7cd212.js" crossorigin="anonymous"></script>
</head>
<body>
<!--NAV BAR-->
<%@include file="components/navbar.jsp" %>
<!--NAV BAR-->
<%@include file="components/homeBg.jsp" %>

<div class="row align-items-start" style="margin-left: 0;margin-right: 0;">
    <div class="col-4">
        <!--biografia-->
        <div class="card shadow-sm mt-5 ml-5 mr-2">
            <div class="shadow-sm card-body">
                <h4 class="card-title"><c:out value="${subject.name}"/></h4>
                <a href="<c:url value="/profesor/${professor.userId}"/>" style="color: black; text-decoration: none;">
                    <h6 class="card-text">
                        <c:set var="professorMessage">
                        <spring:message code="professor.profesor" arguments="${professor.name}"/>
                        </c:set>
                        <c:out value="${professorMessage}"/>
                    </h6>
                </a>
                <p class="card-text"><c:out value="${professor.email}"/></p>
                <a href="<c:url value="/student/${student.userId}"/>" style="color: black; text-decoration: none;">
                    <h6 class="card-text">
                        <c:set var="studentMessage">
                            <spring:message code="student.alumno" arguments="${student.name}"/>
                        </c:set>
                        <c:out value="${studentMessage}"/>
                    </h6>
                </a>
                <p class="card-text"><c:out value="${student.email}"/></p>
                <c:if test="${clase.schedule == null || clase.schedule.length() <= 0}">
                    <p><spring:message code="rooms.emptyschedule" /></p>
                </c:if>
                <c:if test="${clase.schedule != null && clase.schedule.length() > 0}">
                    <c:set var="escapedS">
                        <c:out value="${clase.schedule}"/>
                    </c:set>
                    <p><spring:message code="rooms.schedule" arguments="${escapedS}"/></p>
                </c:if>
                <c:if test="${clase.meetingLink == null || clase.schedule.length() <= 0}">
                    <p><spring:message code="rooms.emptylink" /></p>
                </c:if>
                <c:if test="${clase.meetingLink != null && clase.schedule.length() > 0}">
                    <c:set var="escapedM">
                        <c:out value="${clase.meetingLink}"/>
                    </c:set>
                    <a href="https://${escapedM}" style="text-decoration: none;"><p><spring:message code="rooms.link" arguments="${escapedM}"/></p></a>
                </c:if>
                <c:url value="/class/${lessonId}/update"
                       var="newClassroomForm"/>
                <c:if test="${loggedUser.userId == professor.userId}">
                    <form:form modelAttribute="classroomForm"
                               action="${newClassroomForm}" method="post"
                               class="input-group">
                        <div style="background-color: ghostwhite;width: 100%;padding: 5%;">
                            <spring:message code="form.schedule" var="schedule"/>
                            <c:set var="escapedSchedule">
                                <c:out value="${clase.schedule}"/>
                            </c:set>
                            <form:input type="text" class="form-control"
                                        placeholder="${schedule}"
                                        value="${escapedSchedule}"
                                        path="schedule"/>
                            <div style="text-align: center;">
                                <form:errors path="schedule"
                                             cssStyle="color: red;font-weight: bold;"
                                             element="p"/>
                            </div>
                            <spring:message code="form.meetingLink" var="meetingLink"/>
                            <c:set var="escapedMeetingLink">
                                <c:out value="${clase.meetingLink}"/>
                            </c:set>
                            <form:input type="text" class="form-control"
                                        placeholder="${meetingLink}"
                                        value="${escapedMeetingLink}"
                                        path="meetingLink"/>
                            <div style="text-align: center;">
                                <form:errors path="meetingLink"
                                             cssStyle="color: red;font-weight: bold;"
                                             element="p"/>
                            </div>
                            <spring:message code="form.editar" var="Enviar"/>
                            <input type="submit" class="btn btn-primary"
                                   style="background-color: #349AC2;width: 100%"
                               value="${Enviar}"/>
                        </div>
                    </form:form>
                </c:if>
            </div>
        </div>
    </div>
    <!--cursos o materias-->
    <div class="col-8">
        <div class="card shadow-sm mt-5 mr-5 mb-5">
            <%--TABS--%>
            <section id="tabs" class="project-tab">
                <div class="container rounded" style="background-color: white; margin-top: 2%;">
                    <div class="row">
                        <div class="col-md-12">
                            <h5 class="card-header"
                                style="background-color: white; text-transform: uppercase;text-align: center;">
                                <spring:message code="aula.miClase"/>
                            </h5>
                            <nav>
                                <div class="nav nav-tabs nav-fill" id="nav-tab" role="tablist">
                                    <a draggable="false" class="nav-item nav-link active" id="nav-confirm-tab"
                                       data-toggle="tab" href="#nav-confirm"
                                       role="tab" aria-controls="nav-confirm" aria-selected="true"><spring:message
                                            code="aula.chat"/></a>
                                    <a draggable="false" class="nav-item nav-link" id="nav-progress-tab"
                                       data-toggle="tab" href="#nav-progress"
                                       role="tab" aria-controls="nav-progress" aria-selected="false"><spring:message
                                            code="aula.archivos"/></a>
                                </div>
                            </nav>
                            <div class="tab-content" id="nav-tabContent">
                                <div class="tab-pane fade" id="nav-progress" role="tabpanel"
                                     aria-labelledby="nav-progress-tab">
                                    <div style="margin: 2%">
                                        <table class="table">
                                            <c:if test="${files.size() >= 1}">
                                                <thead>
                                                <tr>
                                                    <th><spring:message code="aula.name"/></th>
                                                    <th style="text-align-last: right;"><spring:message
                                                            code="aula.action"/></th>
                                                </tr>
                                                </thead>
                                            </c:if>
                                            <tbody>
                                            <c:if test="${files.size() == 0}">
                                                <%@include file="components/nothingToShow.jsp" %>
                                            </c:if>
                                            <c:forEach var="file" items="${files}">
                                                <tr>
                                                    <td><c:out value="${file.name}"/></td>
                                                    <td style="display: flex;justify-content: flex-end;">

                                                        <a class="btn text-white"
                                                           style="background-color: #349AC2;width: 100px;"
                                                           href="<c:url value="/class/${lessonId}/file/${file.fileId}"/>">Download</a>
                                                        <c:if test="${loggedUser.userId == professor.userId}">
                                                            <form method="post"
                                                                  action="<c:url value="/class/${lessonId}/file/${file.fileId}/delete"/>">
                                                                <input type="submit"
                                                                       class="btn text-white"
                                                                       style="background-color: #dc3545;width: 100px;margin-left: 20px;"
                                                                       value="<spring:message code="aula.borrar"/>">
                                                            </form>
                                                        </c:if>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                            </tbody>
                                        </table>
                                        <!--paginacion-->
                                        <div class="row align-items-center" style="justify-content: center;">
                                            <c:if test="${pages >1}">
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
                                                                    <a class="page-link" href="<c:url value="/class/${lessonId}?page=${page-1}"/>">
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
                                                                    <li class="page-item"><a class="page-link" href="<c:url value="/class/${lessonId}?page=${i}"/>"><c:out value = "${i}"/></a></li>
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
                                                                    <a class="page-link" href="<c:url value="/class/${lessonId}?page=${page+1}"/>">
                                                                        <spring:message code="pag.siguiente" /></a>
                                                                </li>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </ul>
                                                </nav>
                                            </c:if>
                                        </div>


                                        <c:if test="${loggedUser.userId == professor.userId}">
                                            <hr class="black"/>
                                        <div class="card-footer"
                                             style="background-color: ghostwhite;border: white;border-radius: 2%;">
                                            <div style="display: flex;flex-direction: column;">
                                                <c:url value="/class/${lessonId}/file" var="postFileForm"/>

                                                <form:form enctype="multipart/form-data" modelAttribute="uploadFileForm"
                                                           action="${postFileForm}"
                                                           method="post">
                                                    <div class="form-group row" style="margin-right: 0;margin-left: 0;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i
                                                                    class="fas fa-user"></i></span>
                                                        </div>
                                                        <spring:message code="form.nombre" var="formNombre"/>
                                                        <form:input class="form-control col-sm-10" id="inputNombre"
                                                                    placeholder="${formNombre}" type="text"
                                                                    path="name"/>
                                                    </div>
                                                    <div style="text-align: center;">
                                                        <form:errors path="name"
                                                                     cssStyle="color: red;font-weight: bold;"
                                                                     element="p"/>
                                                    </div>
                                                    <div class="form-group row" style="margin-right: 0;margin-left: 0;">
                                                        <div class="input-group-prepend">
                                                            <span class="input-group-text"><i
                                                                    class="fas fa-folder"></i></span>
                                                        </div>
                                                        <spring:message code="form.subirArchivo" var="formSubir"/>
                                                        <form:input path="file" style="padding: 0.3%;" type="file"
                                                                    data-msg-placeholder="${formSubir}"
                                                                    class="form-control col-sm-10"/>
                                                    </div>
                                                    <div style="text-align: center;">
                                                        <form:errors path="file"
                                                                     cssStyle="color: red;font-weight: bold;"
                                                                     element="p"/>
                                                    </div>
                                                    <div class="form-group row"
                                                         style="margin-right: 0;margin-left: 0;justify-content: flex-end;">
                                                        <input type="submit"
                                                               class="btn text-white" style="background-color: #349AC2;"
                                                               value="<spring:message code="aula.subir"/>">
                                                    </div>
                                                </form:form>
                                            </div>
                                        </div>
                                        </c:if>
                                    </div>
                                </div>
                                <div class="tab-pane fade show active" id="nav-confirm" role="tabpanel"
                                     aria-labelledby="nav-confirm-tab">
                                    <div style="margin: 2%">

                                        <%--CHAT--%>
                                        <main class="content">
                                            <div class="container p-0">

                                                <div class="card">
                                                    <div class="row g-0">
                                                        <div class="col-12">
                                                            <div class="py-2 px-4 border-bottom d-none d-lg-block">
                                                                <div class="d-flex align-items-center py-1">
                                                                    <div class="position-relative">
                                                                        <c:if test="${professor.userId != loggedUser.userId }">
                                                                            <img src="<c:url value="/user/image/${professor.userId}"/>"
                                                                                 class="rounded-circle mr-1"
                                                                                 alt="Sharon Lessman" width="40"
                                                                                 height="40">
                                                                        </c:if>
                                                                        <c:if test="${student.userId != loggedUser.userId }">
                                                                            <img src="<c:url value="/user/image/${student.userId}"/>"
                                                                                 class="rounded-circle mr-1"
                                                                                 alt="Sharon Lessman" width="40"
                                                                                 height="40">
                                                                        </c:if>
                                                                    </div>
                                                                    <div class="flex-grow-1 pl-3">
                                                                        <c:if test="${professor.userId != loggedUser.userId }">
                                                                            <a href="<c:url value="/profesor/${professor.userId}"/>"
                                                                               style="color: black; text-decoration: none;"><strong><c:out value="${professor.name} ${professor.surname}"/></strong></a>
                                                                        </c:if>
                                                                        <c:if test="${student.userId != loggedUser.userId }">
                                                                            <a href="<c:url value="/student/${student.userId}"/>"
                                                                               style="color: black; text-decoration: none;"><strong><c:out value="${student.name} ${student.surname}"/></strong></a>
                                                                        </c:if>
                                                                    </div>
                                                                </div>
                                                            </div>

                                                                <div class="position-relative">
                                                                    <div class="chat-messages p-4" style="display: flex;flex-direction: column;max-height: 400px;min-height: 400px;overflow-y: scroll;">
                                                                        <c:if test="${chat.messageList.size() <= 0}">
                                                                            <div class="chat-message pb-4">
                                                                                <div class="bg-light rounded py-2 px-3" style="min-width: 150px;margin-top: 25%;text-align-last: center;">
                                                                                    <spring:message code="rooms.nomessage" />
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                        <c:forEach items="${chat.messageList}" var="message">
                                                                            <c:if test="${message.author.userId == loggedUser.userId}">
                                                                                <div class="chat-message-right pb-4" style="display: flex;flex-shrink: 0;flex-direction: row-reverse;margin-left: auto;">
                                                                                    <div>
                                                                                        <img src="<c:url value="/user/image/${loggedUser.userId}"/>" class="rounded-circle mr-1" alt="Chris Wood" width="40" height="40">
                                                                                        <div class="text-muted small text-nowrap mt-2" style="font-size: xx-small;">${message.parseLocalDateTime}</div>
                                                                                    </div>
                                                                                    <div class="flex-shrink-1 bg-light rounded py-2 px-3 mr-3" style="min-width: 150px;">
                                                                                            <div style="flex-flow: row-reverse;display: flex;justify-content: space-between;">
                                                                                                <form action="<c:url value="/class/${lessonId}/message/${message.messageId}/delete"/>" method="post">
                                                                                                    <div class="dolmen">
                                                                                                        <button type="submit" class="btn" value="" style="font-size: xx-small;border-radius: 44%;"><i class="fas fa-trash" style="color: red"></i></button>
                                                                                                    </div>
                                                                                                </form>
                                                                                                <div class="font-weight-bold mb-1">
                                                                                                    <spring:message code="rooms.tu" />
                                                                                                </div>
                                                                                            </div>
                                                                                            <c:out value="${message.message}"/>
                                                                                    </div>
                                                                                </div>
                                                                            </c:if>
                                                                            <c:if test="${message.author.userId != loggedUser.userId}">
                                                                                <div class="chat-message-left pb-4" style="display: flex;flex-shrink: 0;margin-right: auto;">
                                                                                    <div>
                                                                                        <img src="<c:url value="/user/image/${message.author.userId}"/>" class="rounded-circle mr-1" alt="Sharon Lessman" width="40" height="40">
                                                                                        <div class="text-muted small text-nowrap mt-2" style="font-size: xx-small;">${message.parseLocalDateTime}</div>
                                                                                    </div>
                                                                                    <div class="flex-shrink-1 bg-light rounded py-2 px-3 ml-3">
                                                                                        <div class="font-weight-bold mb-1"><c:out value="${message.author.name} ${message.author.surname}"/></div>
                                                                                        <c:out value="${message.message}"/>
                                                                                </div>
                                                                            </div>
                                                                        </c:if>
                                                                    </c:forEach>
                                                                </div>
                                                            </div>
                                                                <div class="flex-grow-0 py-3 px-4 border-top">
                                                                    <c:url value="/class/${lessonId}/message"
                                                                           var="postNewMessage"/>
                                                                    <form:form modelAttribute="messageForm"
                                                                               action="${postNewMessage}" method="post">
                                                                        <div class="input-group">
                                                                            <spring:message code="message.placeholder" var="messagePlaceholder"/>
                                                                            <form:input type="text" class="form-control"
                                                                                        placeholder="${messagePlaceholder}"
                                                                                        path="message"/>
                                                                            <spring:message code="form.enviar" var="Enviar"/>
                                                                            <input type="submit" class="btn btn-primary"
                                                                                   style="background-color: #349AC2;"
                                                                                   value="${Enviar}"/>
                                                                        </div>
                                                                    </form:form>
                                                                </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </main>
                                        <%--CHAT--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
            <%--            TABS--%>
        </div>

    </div>

</div>

</body>
</html>

