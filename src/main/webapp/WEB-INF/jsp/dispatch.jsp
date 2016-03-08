<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.beans.Route" %>
<%@ page import="bus.service.web.constants.RequestAttributes" %>
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
        <div class="btn-group">
            <button id="choose_route" type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="choose-route">Выберите номер маршрута</span> <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="?route=101">101</a></li>
                <li><a href="?route=102">102</a></li>
                <li><a href="?route=103">103</a></li>
                <li><a href="?route=105">105</a></li>
            </ul>
        </div>

        <button id="save_route" type="button" class="btn btn-default" aria-expanded="false">
            Сохранить
        </button>

        <%
            boolean isRouteAttached = false;
            Route route = (Route) request.getAttribute(RequestAttributes.ROUTE);
            if (route != null) {
                isRouteAttached = true;
            }
        %>

        <%
            if (isRouteAttached) {
        %>
        <div class="route">
            <div class="chosen-route">
                Выбран маршрут №<%=route.getRouteNumber()%>
            </div>
            <div>
                <div id="map"></div>
                <div class="stop-times">
                </div>
            </div>
        </div>

        <script>
            initMap([59.977693264318, 30.324142490948248], <%=route.getRouteNumber()%>);
        </script>
        <%
            }
        %>
    </div>
</div>
</body>
</html>