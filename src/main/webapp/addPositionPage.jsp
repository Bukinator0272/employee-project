<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add Position</title>
    <link rel="stylesheet" href="bootstrap/css/customBootstrap.css"/>
    <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <meta charset="UTF-8">

</head>
<body>

<div class="main">
    <div class="jumbotron" style="height: 5%">
        <h1>Add Position</h1>
    </div>
    <div class="container">

        <div class="form-group row ">
            <div class="col-xl-7">
                <a href="homePage.jsp" class="btn btn-success btn-xs">Home</a>
            </div>
        </div>

        <form action="/add-position" method="post">

            <div class="form-group row">
                <div class="col-xl-5">
                    <label for="name">Name</label>
                    <input type="text" id="name" name="name" placeholder="" class="form-control">
                </div>
            </div>

            <div class="form-group row ">
                <div class="col-xl-7">
                    <input type="submit" value="Save" class="btn btn-success btn-xs"/>
                </div>
            </div>

        </form>
    </div>
</div>

</body>
</html>



