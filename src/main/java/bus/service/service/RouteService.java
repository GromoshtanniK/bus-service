package bus.service.service;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.dao.RouteDao;
import bus.service.dao.RouteStopDao;

import java.sql.SQLException;
import java.util.List;

public class RouteService {
    public Route getRouteByNumber(int routeNumber) throws SQLException {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();

        Route route = routeDao.getRouteByRouteNumber(routeNumber);
        List<RouteStop> routeStops = routeStopDao.getRouteStopsByRoute(route);
        route.setStops(routeStops);

        return route;
    }
}
