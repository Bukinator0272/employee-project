<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="css/ViewEmployeesPageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<div class="main">
    <table class="main__table">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Department</th>
            <th>Position</th>
            <th>EmploymentDate</th>
            <th>Manager</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.department}</td>
                <td>${employee.position}</td>
                <td>${employee.employmentDate}</td>
                <td>${employee.manager}</td>
            </tr>
        </c:forEach>
    </table>
    <a href="add-employee" class="main__add">View Employees</a>
</div>
</body>
</html>
