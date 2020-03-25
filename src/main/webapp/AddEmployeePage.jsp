<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Employee</title>
</head>
<body>
<form action="add-employee" method="post">
    Name: <label>
    <input name="name" type="text"/>
</label>
    <br><br>
    Surname: <label>
    <input name="surname" type="text"/>
</label>
    Department: <label>
    <input name="department" type="text"/>
</label>
    Position: <label>
    <input name="position" type="text"/>
</label>
    Project name: <label>
    <input name="projectName" type="text"/>
</label>
    Cabinet number: <label>
    <input name="cabinetNumber" type="text"/>
</label>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
