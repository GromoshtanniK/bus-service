<%@ page import="bus.service.web.constants.Path" %>
<%@ page import="bus.service.web.constants.RequestAttributes" %>
<%@ page import="java.util.List" %>
<%@ page import="bus.service.beans.Route" %>
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
    <script src="js/addDeleteRouteScript.js"></script>
    <meta charset="UTF-8">
    <title>Routes</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="<%=Path.HEADER_JSP%>"/>
    <div class="content">
        <div class="form-group">
            <label for="route_number" class="route-save-label">Введите номер маршрута:</label>
            <input type="text" class="form-control route-save-field" id="route_number">
            <button type="button" class="btn btn-default route-save-button" id="add_route_button" aria-expanded="false">
                Сохранить
            </button>
        </div>

        <%
            List<Route> routes = (List<Route>) request.getAttribute(RequestAttributes.ROUTES);
            if (routes != null) {
        %>
        <div class="routes">

            <%
                for (Route route : routes) {
            %>
            <div class="route">
                <div class="route-number"><%=route.getRouteNumber()%></div>
                <button class="delete-route-btn" data-route="<%=route.getId()%>"><span class="glyphicon glyphicon-minus minus"></span></button>
            </div>
            <%
                }
            %>
        </div>
        <%
            }
        %>
    </div>
</div>
</body>
</html>