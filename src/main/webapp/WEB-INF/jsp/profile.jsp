<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.beans.Route" %>
<%@ page import="java.util.List" %>
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
                <%
                    List<Route> routes = (List<Route>) request.getAttribute(RequestAttributes.LINKED_ROUTES);
                    for (Route route : routes) {
                %>
                    <li><span class="watching-dropdown-li" data-route="<%=route.getId()%>"><%=route.getRouteNumber()%></span></li>
                <%
                    }
                %>
            </ul>
        </div>
        <div class="routes">
            <%
                List<Route> linkedRoutes = (List<Route>) request.getAttribute(RequestAttributes.LINKED_ROUTES);
                for (Route route : linkedRoutes) {
            %>
            <div class="route">
                <div class="route-number"><%=route.getRouteNumber()%></div>
                <button class="delete-route-btn" data-route="<%=route.getId()%>"><span class="glyphicon glyphicon-minus minus"></span></button>
            </div>
            <%
                }
            %>
        </div>
    </div>

</div>
</body>
</html>