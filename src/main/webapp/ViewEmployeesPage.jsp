<%@ page import="model.entity.Employee" %>
<%@ page import="model.service.EmployeeService" %>
<%@ page import="java.util.List" %>
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
    <table class="table">
        <caption><h1>Employees</h1></caption>
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Department</th>
            <th>Position</th>
            <th>EmploymentDate</th>
            <th>Manager</th>
        </tr>
        <%
            EmployeeService employeeService = new EmployeeService();
            List<Employee> employeeList = (List<Employee>) request.getAttribute("employees");
            int i = 0;
        %>
        <%--@elvariable id="employees" type="java.util.List"--%>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.name}</td>
                <td>${employee.surname}</td>
                <td>${employee.department}</td>
                <td>${employee.position}</td>
                <td>${employee.employmentDate}</td>
                <td>
                    <%= employeeService.getManagersNameSurname(employeeList.get(i).getManager())%>
                </td>
                <td><a href="edit-employee?id=${employee.id}">Edit</a></td>
                <td><a href="delete-employee?id=${employee.id}">Delete</a></td>
                <% i++; %>
            </tr>
        </c:forEach>
    </table>
    <a href="add-employee" class="add">Add Employee</a>
</div>
</body>
</html>
