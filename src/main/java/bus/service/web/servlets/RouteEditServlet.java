package bus.service.web.servlets;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.json.*;
import bus.service.service.RouteService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = Path.ROUTE_EDIT_SERVLET)
public class RouteEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Route route = getRoute(req);

        if (route != null) {
            req.setAttribute(RequestAttributes.ROUTE, route);
        }

        RouteService routeService = new RouteService();
        req.setAttribute(RequestAttributes.ROUTES, routeService.getAllRoutes());

        req.getRequestDispatcher(Path.EDIT_JSP).forward(req, resp);
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

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonParser parser = new JsonParser(req.getReader());
        SavingRoute savingRoute = parser.parseSaveRouteRequest();
        System.out.println();
//        Route route = parseSaveRouteRequest(req.getReader().readLine());
//        RouteService routeService = new RouteService();
//        routeService.deleteAndCreateNewRoute(route);
    }

}
