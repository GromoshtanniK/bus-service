<%@ page import="bus.service.beans.Route" %>
<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.web.constants.RequestAttributes" %>
<%@ page import="java.util.List" %>
<%@ page import="bus.service.beans.RouteStop" %>
<%@ page import="bus.service.beans.StopTime" %>
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
    <script src="js/indexScript.js"></script>
    <meta charset="UTF-8">
    <title>BUS SERVICE</title>
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
                    <%--<div class="stop-title">Остановка: название остановки</div>--%>
                    <%--<div class="stop-time">11:15</div>--%>
                    <%--<div class="stop-time">12:20</div>--%>
                    <%--<div class="stop-time">13:47</div>--%>
                    <%--<div class="stop-time">14:28</div>--%>
                    <%--<div class="stop-time">15:19</div>--%>
                    <%--<div class="stop-time">16:30</div>--%>
                </div>
            </div>
        </div>
        <script>
            initMap([59.977693264318, 30.324142490948248],
                    [
                        <%
                        for (RouteStop routeStop : route.getStops()) {
                        %>
                        {
                            backWay: <%=routeStop.isBackWay()%>,
                            name: "<%=routeStop.getStopName()%>",
                            coordinates: [<%=routeStop.getAltitude()%>, <%=routeStop.getLatitude()%>],
                            times: [
                                <%
                                    for (StopTime stopTime : routeStop.getStopTimes()) {
                                    %>
                                {hours: <%=stopTime.getHours()%>, minutes: <%=stopTime.getMinutes()%>},
                                <%
                                }
                            %>
                            ]
                        },
                        <%
                        }
                        %>
                    ]);
        </script>
        <%
            }
        %>
    </div>
</div>
</body>
</html>