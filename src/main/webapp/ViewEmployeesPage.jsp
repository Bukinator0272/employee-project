<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employees</title>
</head>
<body>
<table>
    <c:forEach var="employee" items="${employees}">
        <tr>
            <td>${employee.id}</td>
            <td>${employee.employmentDate}</td>
            <td>${employee.name}</td>
            <td>${employee.surname}</td>
            <td>${employee.department}</td>
            <td>${employee.position}</td>
            <td>${employee.projectName}</td>
            <td>${employee.cabinetNumber}</td>
        </tr>
    </c:forEach>
</table>
<a href="add-employee">Add Employee</a>
</body>
</html>
