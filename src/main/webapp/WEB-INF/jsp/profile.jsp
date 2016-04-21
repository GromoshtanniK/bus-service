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
    <script src="js/profileScript.js"></script>
    <script src="http://api-maps.yandex.ru/2.1/?lang=ru_RU" type="text/javascript"></script>
    <meta charset="UTF-8">
    <title>Routes</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="/WEB-INF/jsp/header.jsp"/>

    <div class="content">
        <div class="btn-group">
            <div class="watching-dropdown-menu-label">
                Выберите номер маршрута для добавления в список отслеживаемых:
            </div>
            <button id="choose_route" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="choose-route">Номер маршрута</span> <span class="caret"></span>
            </button>
            <ul class="dropdown-menu watching-dropdown-menu">
                <li><span class="watching-dropdown-li" data-route="ROUTE_ID_1">123</span></li>
                <!--TODO тут заменить ROUTE_ID и 123 (глянь как это сделано в index), в этот список выводятся роуты еще не добаленные, нужно сортировать по тем, которые не были добавленны пользователем-->
            </ul>
        </div>
        <div class="routes">
            <div class="route">
                <div class="route-number">321123</div>
                <!--TODO тут заменить ROUTE_ID-->
                <button class="delete-route-btn" data-route="ROUTE_ID_2"><span class="glyphicon glyphicon-minus minus"></span></button>
            </div>
        </div>
    </div>

</div>
</body>
</html>