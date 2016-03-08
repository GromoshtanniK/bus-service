package bus.service.web.servlets;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.service.RouteService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = Path.DISPATCH_SERVLET)
public class DispatchServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Route route = getRoute(req);
        if (route != null) {
            req.setAttribute(RequestAttributes.ROUTE, route);
        }
        req.getRequestDispatcher(Path.DISPATCH_JSP).forward(req, resp);
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
        Route route = parseSaveRouteRequest(req.getReader().readLine());
        RouteService routeService = new RouteService();
        routeService.saveNewRoute(route);
    }

    private Route parseSaveRouteRequest(String json) {
        JSONObject saveRoute = new JSONObject(json);

        Route route = new Route();
        route.setRouteNumber(saveRoute.getInt("routeNumber"));

        List<RouteStop> routeStops = new ArrayList<RouteStop>();

        JSONArray placeMarks = saveRoute.getJSONArray("placeMarks");
        for (int i = 0; i < placeMarks.length(); i++) {
            JSONObject placeMark = placeMarks.getJSONObject(i);

            RouteStop routeStop = new RouteStop();
            routeStop.setStopName(placeMark.getString("name"));
            routeStop.setAltitude(placeMark.getJSONArray("cords").getDouble(0));
            routeStop.setLatitude(placeMark.getJSONArray("cords").getDouble(1));

            List<StopTime> stopTimes = new ArrayList<StopTime>();

            JSONArray times = placeMark.getJSONArray("times");
            for (int j = 0; j < times.length(); j++) {
                JSONObject time = times.getJSONObject(j);

                StopTime stopTime = new StopTime();
                stopTime.setHours(time.getInt("hours"));
                stopTime.setMinutes(time.getInt("minutes"));

                stopTimes.add(stopTime);
            }

            routeStop.setStopTimes(stopTimes);
            routeStops.add(routeStop);
        }

        route.setStops(routeStops);
        return route;
    }
}
