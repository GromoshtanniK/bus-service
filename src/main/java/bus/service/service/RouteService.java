package bus.service.service;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.dao.RouteDao;
import bus.service.dao.RouteStopDao;
import bus.service.dao.StopTimeDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteService {

    public List<Route> getAllRoutes() {
        RouteDao routeDao = new RouteDao();
        List<Route> routes = new ArrayList<Route>();

        try {
            routes.addAll(routeDao.getAllRoutes());
        } catch (SQLException e) {
            //todo log
        }
        return routes;
    }

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

    public void saveNewRoute(Route route) {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();
        StopTimeDao stopTimeDao = new StopTimeDao();
        try {
            for (RouteStop routeStop : route.getStops()) {
                routeStop.setRouteId(route.getId());
                routeStopDao.createRouteStop(routeStop);
                for (StopTime stopTime : routeStop.getStopTimes()) {
                    stopTime.setRouteStopId(routeStop.getId());
                    stopTimeDao.createStopTime(stopTime);
                }
            }
            routeDao.createRoute(route);
        } catch (SQLException e) {
            //todo log
        }
    }

    public void updateRoute(Route route) {
        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();
        StopTimeDao stopTimeDao = new StopTimeDao();

        try {
            routeDao.updateRoute(route);
            for (RouteStop routeStop : route.getStops()) {
                routeStopDao.updateRouteStop(routeStop);
                for (StopTime stopTime : routeStop.getStopTimes()) {
                    stopTimeDao.updateStopTime(stopTime);
                }
            }
        } catch (SQLException e) {
            //todo log
        }
    }
}
