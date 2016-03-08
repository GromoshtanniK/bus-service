package bus.service.service;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.dao.RouteDao;
import bus.service.dao.RouteStopDao;

import java.sql.SQLException;
import java.util.List;

public class RouteService {

    public Route getRouteByRouteNumber(int routeNumber) {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();

        Route route = null;

        try {
            route = routeDao.getRouteByRouteNumber(routeNumber);
            List<RouteStop> routeStops = routeStopDao.getRouteStopsByRoute(route);
            route.setStops(routeStops);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
    }

    public void saveNewRoute(Route route) {

    }
}
