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

    public void deleteRouteBuRouteNumber(int routeNumber) {
        RouteDao routeDao = new RouteDao();
        try {
            routeDao.deleteRouteByRouteNumber(routeNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createRouteByRouteNumber(int routeNumber) {
        RouteDao routeDao = new RouteDao();
        Route route = new Route();
        route.setRouteNumber(routeNumber);
        try {
            routeDao.createRoute(route);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Route> getAllRoutes() {
        RouteDao routeDao = new RouteDao();
        try {
            return routeDao.getAllRoutes();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Route getRouteByRouteNumber(int routeNumber) {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();
        StopTimeDao stopTimeDao = new StopTimeDao();

        Route route = null;

        try {
            route = routeDao.getRouteByRouteNumber(routeNumber);
            List<RouteStop> routeStops = routeStopDao.getRouteStopsByRoute(route);

            for (RouteStop routeStop : routeStops) {
                List<StopTime> stopTimes = stopTimeDao.getStopTimesByRouteStop(routeStop);
                routeStop.setStopTimes(stopTimes);
            }

            route.setStops(routeStops);
        } catch (SQLException e) {
            //todo log
        }
        return route;
    }

    public void deleteAndCreateNewRoute(Route route) {

        RouteDao routeDao = new RouteDao();
        RouteStopDao routeStopDao = new RouteStopDao();
        StopTimeDao stopTimeDao = new StopTimeDao();

        try {
            routeStopDao.deleteRouteStopsByRoute(routeDao.getRouteByRouteNumber(route.getRouteNumber()));
            long routeId = routeDao.findRouteIdByNumber(route.getRouteNumber());
            route.setId(routeId);
            for (RouteStop routeStop : route.getStops()) {
                routeStop.setRouteId(route.getId());
                routeStopDao.createRouteStop(routeStop);

                for (StopTime stopTime : routeStop.getStopTimes()) {
                    stopTime.setRouteStopId(routeStop.getId());
                    stopTimeDao.createStopTime(stopTime);
                }

            }
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
