<%--@elvariable id="manager" type="java.lang.String"--%>
<%--@elvariable id="employee" type="model.entity.Employee"--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="css/EditEmployeePageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div class="main">
    <table class="table">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Department</th>
            <th>Position</th>
            <th>EmploymentDate</th>
            <th>Manager</th>
        </tr>
        <tr>
            <td>${employee.name}</td>
            <td>${employee.surname}</td>
            <td>${employee.department}</td>
            <td>${employee.position}</td>
            <td>${employee.employmentDate}</td>
            <td>${manager}</td>
        </tr>
    </table>
    <form class="form" action="edit-employee" method="post">
        <label for="name">Name</label>
        <input type="text" id="name" name="name" placeholder="Name">

        <label for="surname">Surname</label>
        <input type="text" id="surname" name="surname" placeholder="Surname">

        <label for="department">Department</label>
        <input type="text" id="department" name="department" placeholder="Department">

        <label for="position">Position</label>
        <input type="text" id="position" name="position" placeholder="Position">

        <label for="manager">Manager</label>
        <input type="text" id="manager" name="manager" placeholder="Manager">

        <input type="hidden" id="id" name="id" value="${employee.id}">

        <input class="submit_btn" type="submit" value="Submit">
    </form>

</div>
</body>
</html>
