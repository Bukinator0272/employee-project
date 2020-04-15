<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="css/AddEmployeePageStyle.css" type="text/css"/>
    <meta charset="UTF-8">
</head>
<body>
<div class="main">
    <h1 class="text"> Enter Employee</h1>
    <form class="form" action="add-employee" method="post">
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

        <input class="submit_btn" type="submit" value="Submit">
    </form>
</div>
</body>
</html>
