<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>


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
</head>
<body>
<div class="shadow-sm" style="background-color: white">
    <div class="row align-items-start" style="margin-right: 0;margin-left: 0;">
        <div class="col-1 ml-5">
            <img draggable="false" style="border-radius: 50%;width: 100px;height: 100px;object-fit: cover;margin: 10%;"
                 src="<c:url value="/user/image/${prof.userId}"/>"/>
        </div>
        <div class="col align-self-end" style="margin: 1%">
            <div class="row" style="margin-left: 0">
            <a href="${pageContext.request.contextPath}/profesor/${prof.userId}"
               style="color: black;">
                <h3 class="mb-0"><c:out value="${prof.name} ${prof.surname}"/></h3>
            </a>
                <h3 class="mb-0">: <c:out value="${mainContract.subject.name}"/></h3>
            </div>

            <p class="mb-0"><c:out value="${prof.email}"/></p>
            <div>
                <c:forEach begin="1" end="5" var="i">
                    <c:choose>
                        <c:when test="${prof.rating != null && 1>=prof.rating && i<=prof.rating}">
                            <i class="fas fa-star" style="color: gold"></i>
                        </c:when>
                        <c:when test="${prof.rating != null && 3>=prof.rating && i<=prof.rating}">
                            <i class="fas fa-star" style="color: gold"></i>
                        </c:when>
                        <c:when test="${prof.rating != null && 5>=prof.rating && i<=prof.rating}">
                            <i class="fas fa-star" style="color: gold"></i>
                        </c:when>
                        <c:otherwise>
                            <i class="fas fa-star" style="color: darkgrey"></i>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <span>(<c:out value="${allReviews}"/>)
                </span>
            </div>
        </div>
    </div>
</div>


</body>
</html>
