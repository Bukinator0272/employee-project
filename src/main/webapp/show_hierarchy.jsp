<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employees</title>
    <%--<link rel="stylesheet" href="css/ViewEmployeesPageStyle.css" type="text/css"/>--%>
    <link rel="stylesheet" href="bootstrap/css/customBootstrap.css"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
</head>

<body>
<div class="main">

    <div class="jumbotron" style="height: 5%">
        <h1>Show Employee Hierarchy</h1>
    </div>

    <div class="container">

        <div class="form-group row ">
            <div class="col-xl-7">
                <a href="index.jsp" class="btn btn-success btn-xs">Home</a>
            </div>
        </div>

        <c:set var="last">1</c:set>
        <ul>
            <c:forEach var="hierarchy" items="${hierarchy}">

            <c:if test="${hierarchy.level == last}">
            <li><a href="show-employee?id=${hierarchy.employee.id}">${hierarchy.employee.name}${" "}${hierarchy.employee.surname}</a></li>
            </c:if>
            <c:if test="${hierarchy.level > last}">
            <ul>
                <li><a href="show-employee?id=${hierarchy.employee.id}">${hierarchy.employee.name}${" "}${hierarchy.employee.surname}</a></li>
                    <c:set var="last">${hierarchy.level}</c:set>
                </c:if>
                <c:if test="${hierarchy.level < last}">
                <c:forEach begin="1" end="${last-hierarchy.level}" step="1" varStatus="loop">
                    </ul>
                </c:forEach>
                <li><a href="show-employee?id=${hierarchy.employee.id}">${hierarchy.employee.name}${" "}${hierarchy.employee.surname}</a></li>
                    <c:set var="last">${hierarchy.level}</c:set>
                </c:if>
                </c:forEach>
    </div>
</div>
</body>
</html>


<%--<html lang="en">--%>
<%--<head>--%>
<%--<title>Bootstrap Example</title>--%>
<%--<meta charset="utf-8">--%>
<%--<meta name="viewport" content="width=device-width, initial-scale=1">--%>
<%--<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">--%>
<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>--%>
<%--<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>--%>
<%--</head>--%>
<%--<body>--%>

<%--<div class="container">--%>

<%--<div class="media">--%>
<%--<div class="media-left">--%>
<%--<div class="media-body">--%>
<%--<h4 class="media-heading"></h4>--%>
<%--</div>--%>


