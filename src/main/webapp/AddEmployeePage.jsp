<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="bootstrap/css/customBootstrap.css"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="calendar/dist/simple-calendar.css">
    <%--<script src="jquery/jquery-3.5.0.js" type="text/javascript"></script>--%>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="calendar/dist/jquery.simple-calendar.js" type="text/javascript"></script>


    <meta charset="UTF-8">
</head>
<body>

<div class="main">
    <div class="jumbotron" style="height: 5%">
        <h1>Add Employee</h1>
    </div>

</div>

    <div class="container">

        <div class="form-group row ">
            <div class="col-xl-7">
                <a href="homePage.jsp" class="btn btn-success btn-xs">Home</a>
            </div>
        </div>

        <form action="/add-employee" method="post">

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" placeholder="" class="form-control">
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="surname">Surname</label>
                    <input type="text" id="surname" name="surname" placeholder="" class="form-control">
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Position</label>
                    <select name="positionId" class="form-control">
                        <option value="0" selected>Select position</option>
                        <c:forEach var="position" items="${positions}">
                            <option value="${position.stringID}">${position.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Department</label>
                    <select name="departmentId" class="form-control">
                        <option value="0" selected>Select department</option>
                        <c:forEach var="department" items="${departments}">
                            <option value="${department.stringID}">${department.name}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>


            <div class="form-group row">
                <div class="col-xl-5">
                    <label type="text">Manager</label>
                    <select name="managerId" class="form-control">
                        <option value="0" selected>Select manager</option>
                        <c:forEach var="manager" items="${managers}">
                            <option value="${manager.stringID}">${manager.name}${" "}${manager.surname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <%--<div class="form-group row">--%>
            <%--<div class="col-xl-5">--%>
            <%--<label>Manager</label>--%>
            <%--<input name="manager" type="text" class="form-control"/>--%>
            <%--</div>--%>
            <%--</div>--%>

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="employmentDate">Employment Date</label>
                    <input type="text" id="employmentDate" name="employmentDate" class="form-control">
                </div>
            </div>

            <div class="form-group row ">
                <div class="col-xl-7">
                    <input type="submit" value="Save" class="btn btn-success btn-xs"/>
                </div>
            </div>

        </form>
    </div>

</div>

</body>
</html>






