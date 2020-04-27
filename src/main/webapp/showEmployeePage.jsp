<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <title>Show Employee</title>
    <link rel="stylesheet" href="bootstrap/css/customBootstrap.css"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">
</head>
<body>
<div class="main">
    <div class="jumbotron" style="height: 5%">
        <h1>Show Employee</h1>
    </div>
    <div class="container">
        <form action="/show-employee" method="post">

            <div class="form-group row ">
                <div class="col-xl-7">
                    <a href="homePage.jsp" class="btn btn-success btn-xs">Home</a>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" value="${employee.name}" class="form-control">
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="surname">Surname</label>
                    <input type="text" id="surname" name="surname" value="${employee.surname}" class="form-control">
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Position</label>
                    <select name="positionId" class="form-control">
                        <c:forEach var="position" items="${positions}">
                            <c:if test="${employee.position.equals(position)}">
                                <option value="${position.stringID}">${position.name}</option>
                            </c:if>
                            <c:if test="${!employee.position.equals(position)}">
                                <option value="${position.stringID}">${position.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Department</label>
                    <select name="departmentId" class="form-control">
                        <c:forEach var="department" items="${departments}">
                            <c:if test="${employee.department.equals(department)}">
                                <option value="${department.stringID}">${department.name}</option>
                            </c:if>
                            <c:if test="${!employee.department.equals(department)}">
                                <option value="${department.stringID}">${department.name}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Manager</label>
                    <select name="managerId" class="form-control">
                        <c:forEach var="manager" items="${managers}">
                            <c:if test="${employee.manager.equals(manager)}">
                                <option value="${manager.stringID}">${manager.name}${" "}${manager.surname}</option>
                            </c:if>
                            <c:if test="${!employee.manager.equals(manager)}">
                                <option value="${manager.stringID}">${manager.name}${" "}${manager.surname}</option>
                            </c:if>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="employmentDate">Employment Date</label>
                    <input type="text" id="employmentDate" name="employmentDate"  value="${employee.employmentDate}"class="form-control">
                </div>
            </div>

            <div class="form-group row ">
                <div class="col-xl-7">
                    <a href="edit-employee?id=${employee.id}"><img src="update.jpg" width="30" height="30"></a>
                </div>
            </div>

        </form>

    </div>
</div>
</body>
</html>
