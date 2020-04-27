<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Positions</title>
    <link rel="stylesheet" href="css/ViewPositionsPageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>

<body>
<div class="main">

    <a href="homePage.jsp" class="main__add">Home</a>

    <table class="main__table">
        <tr>
            <th>Name</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:forEach var="positions" items="${positions}">
            <tr>
                <td>${positions.name}</td>

                <td>
                    <a href="edit-position?id=${positions.id}">Edit</a>
                </td>

                <td>
                    <a href="delete-position?id=${positions.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="add-position" class="main__add">Add position</a>
</div>
</body>
</html>
