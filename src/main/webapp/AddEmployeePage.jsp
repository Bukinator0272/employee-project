<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="css/AddEmployeePageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div>
    <form action="add-employee" method="post">
        Name: <label>
        <input name="name" type="text"/>
    </label>
        <br><br>
        Surname: <label>
        <input name="surname" type="text"/>
    </label>
        <br><br>
        Department: <label>
        <input name="department" type="text"/>
    </label>
        <br><br>
        Position: <label>
        <input name="position" type="text"/>
    </label>
        <br><br>
        Manager id: <label>
        <input name="manager" type="text"/>
    </label>
        <br><br>
        <input type="submit" value="Submit"/>
    </form>
</div>
</body>
</html>
