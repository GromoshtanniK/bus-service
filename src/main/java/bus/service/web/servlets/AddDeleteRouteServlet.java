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
import java.util.List;

@WebServlet(urlPatterns = Path.ADD_DELETE_SERVLET)
public class AddDeleteRouteServlet extends HttpServlet {

    RouteService routeService = new RouteService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Route> routes = routeService.getAllRoutes();
        req.setAttribute(RequestAttributes.ROUTES, routes);
        req.getRequestDispatcher(Path.ADD_DELETE_JSP).forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String addRouteNumber = req.getParameter("add");
        if (addRouteNumber != null) {
            add(addRouteNumber);
        }

        String deleteRouteId = req.getParameter("delete");
        if (deleteRouteId != null) {
            delete(deleteRouteId);
        }

    }

    private void delete(String routeId) {
        routeService.deleteRouteById(Long.valueOf(routeId));
    }


    private void add(String routeNumber) {
        routeService.createRouteByRouteNumber(Integer.valueOf(routeNumber));
    }

}
