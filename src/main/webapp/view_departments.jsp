<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Departments</title>
    <link rel="stylesheet" href="css/ViewDepartmentsPageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div class="main">

    <a href="index.jsp" class="main__add">Home</a>

    <table class="main__table">
        <tr>
            <th>Name</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="departments" items="${departments}">
            <tr>
                <td>${departments.name}</td>

                <td>
                    <a href="/edit-department?id=${departments.id}">Edit</a>
                </td>

                <td>
                    <a href="/delete-department?id=${departments.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <form action="departments" method="post" enctype="multipart/form-data">
        <input type="file" id="file" class="main__add"  name="file"/>
        <input value="Import from XML" class="main__add" type="submit" />
    </form>

    <a href="add-department" class="main__add">Add department</a>
    <a href="departments" class="main__add">Export to XML</a>
</div>
</body>
</html>
