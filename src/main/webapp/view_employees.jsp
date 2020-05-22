<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
    <link rel="stylesheet" href="css/ViewEmployeesPageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>

<body>
<div class="main">

    <a href="index.jsp" class="main__add">Home</a>
    <a href="/show-hierarchy" class="main__add">Show hierarchy</a>

    <table class="main__table">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Position</th>
            <th>Department</th>
            <th>EmploymentDate</th>
            <th>Manager</th>
            <th>Edit</th>
            <th>Delete</th>
            <th>Show</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.position.name}</td>
                <td>${employee.department.name}</td>
                <td>${employee.employmentDate}</td>
                <td>${employee.manager.name}${" "}${employee.manager.surname}</td>

                <td>
                    <a href="edit-employee?id=${employee.id}">Edit</a>
                </td>

                <td>
                    <a href="delete-employee?id=${employee.id}">Delete</a>
                </td>

                <td>
                    <a href="show-employee?id=${employee.id}">Show</a>
                </td>

            </tr>
        </c:forEach>
    </table>

    <form action="employees" method="post" enctype="multipart/form-data">
        <input type="file" id="file" class="main__add"  name="file"/>
        <input value="Import from XML" class="main__add" type="submit" />
    </form>

    <a href="add-employee" class="main__add">Add employee</a>
    <a href="employees" class="main__add">Export to XML</a>
</div>
</body>
</html>
