<%@ page import="bus.service.web.constants.Path" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" type="text/css" href="css/reset.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/bootstrap-theme.min.css">
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <script src="js/jQuery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/scripts.js"></script>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <meta charset="UTF-8">
    <title>Routes</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="<%=Path.HEADER_JSP%>"/>
    <div class="content">
    </div>
</div>
</body>
</html>