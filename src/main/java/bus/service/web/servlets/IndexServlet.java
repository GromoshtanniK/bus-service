package bus.service.web.servlets;

import bus.service.beans.Route;
import bus.service.service.RouteService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Path.INDEX_SERVLET)
public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Route route = getRoute(req);
        if (route != null) {
            req.setAttribute(RequestAttributes.ROUTE, route);
        }

        RouteService routeService = new RouteService();
        req.setAttribute(RequestAttributes.ROUTES, routeService.getAllRoutes());

        req.getRequestDispatcher(Path.INDEX_JSP).forward(req, resp);
    }

    private Route getRoute(HttpServletRequest request) {
        RouteService routeService = new RouteService();
        String routeNumberParameter = request.getParameter(RequestAttributes.ROUTE_NUMBER);
        if (routeNumberParameter != null) {
            int routeNumber = Integer.valueOf(routeNumberParameter);
            return routeService.getRouteByRouteNumber(routeNumber);
        }
        return null;
    }
}
