<%@ page import="bus.service.beans.Route" %>
<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.web.constants.RequestAttributes" %>
<%@ page import="java.util.List" %>
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
                <%
                    List<Route> routes = (List<Route>) request.getAttribute(RequestAttributes.ROUTES);
                    if (routes != null) {
                %>
                <%
                    for (Route route : routes) {
                %>
                <li><a href="?route=<%=route.getRouteNumber()%>"><%=route.getRouteNumber()%></a></li>
                <%
                    }
                %>
                <%
                    }
                %>
            </ul>
        </div>

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
                    <div class="stop-title">Остановка: название остановки</div>
                    <div class="stop-time">11:15</div>
                    <div class="stop-time">12:20</div>
                    <div class="stop-time">13:47</div>
                    <div class="stop-time">14:28</div>
                    <div class="stop-time">15:19</div>
                    <div class="stop-time">16:30</div>
                </div>
            </div>
        </div>

        <%
            }
        %>
    </div>
</div>

<script>
    ymaps.ready(function () {

        myMap = new ymaps.Map('map', { center: [59.977693264318, 30.324142490948248], zoom: 12, controls: ['zoomControl', 'fullscreenControl']});

        var pm0f = new ymaps.Placemark([59.97509064931156, 30.317426239574974],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/avtstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm0f);


        var pm1f = new ymaps.Placemark([59.97123564709448, 30.314764430137735],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/avtstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm1f);


        var pm2f = new ymaps.Placemark([59.981457013836426, 30.328498398388454],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/avtstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm2f);

        var pm3f = new ymaps.Placemark([59.98303856785052, 30.332048804932086],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/avtstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm3f);



        var pm0b = new ymaps.Placemark([59.97077213267899, 30.31407075173795],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/trmstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm0b);

        var pm1b = new ymaps.Placemark([59.97486210409955, 30.316761051739267],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/trmstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm1b);


        var pm2b = new ymaps.Placemark([59.98144894911788, 30.32779565962654],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/trmstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm2b);

        var pm3b = new ymaps.Placemark([59.98366398439077, 30.332768475140163],
                null,
                {
                    iconLayout: 'default#image',
                    iconImageHref: "./images/trmstn.png",
                    iconImageSize: [10, 10],
                    iconImageOffset: [-3, -3],
                    iconShape: {
                        type: 'Circle',
                        coordinates: [0, 0],
                        radius: 10
                    }
                });
        myMap.geoObjects.add(pm3b);

    });

</script>

</body>
</html>