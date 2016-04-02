package bus.service.web.servlets;

import bus.service.beans.Route;
import bus.service.json.*;
import bus.service.service.EditRouteService;
import bus.service.service.RouteService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Path.ROUTE_EDIT_SERVLET)
public class RouteEditServlet extends HttpServlet {

    RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Route route = getRoute(req);

        if (route != null) {
            req.setAttribute(RequestAttributes.ROUTE, route);
        }

        req.setAttribute(RequestAttributes.ROUTES, routeService.getAllRoutes());
        req.getRequestDispatcher(Path.EDIT_JSP).forward(req, resp);
    }

    private Route getRoute(HttpServletRequest request) {
        String routeNumberParameter = request.getParameter(RequestAttributes.ROUTE_NUMBER);
        if (routeNumberParameter != null) {
            int routeNumber = Integer.valueOf(routeNumberParameter);
            return routeService.getRouteByRouteNumber(routeNumber);
        }
        return null;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonParser parser = new JsonParser(req.getReader());
        EditRoute editRoute = parser.parseEditRouteRequest();
        EditRouteService routeService = new EditRouteService();
        routeService.applyEditRouteChanges(editRoute);
    }

}
