package bus.service.service;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.dao.RouteDao;
import bus.service.dao.RouteStopDao;
import bus.service.dao.StopTimeDao;

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
            //todo log
        }
        return route;
    }

    public void saveNewRoute(Route route) throws SQLException {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();
        StopTimeDao stopTimeDao = new StopTimeDao();

        for (RouteStop routeStop : route.getStops()) {
            routeStop.setRouteId(route.getId());
            routeStopDao.createRouteStop(routeStop);
            for (StopTime stopTime : routeStop.getStopTimes()) {
                stopTime.setRouteStopId(routeStop.getId());
                stopTimeDao.createStopTime(stopTime);
            }
        }
        routeDao.createRoute(route);
    }

    public void updateRoute(Route route) {
        //todo
    }
}
