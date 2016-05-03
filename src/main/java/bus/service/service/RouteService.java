package bus.service.service;

import bus.service.beans.Route;
import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.dao.RouteDao;
import bus.service.dao.RouteStopDao;
import bus.service.dao.StopTimeDao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

public class RouteService {

    private RouteDao routeDao = new RouteDao();

    public void deleteRouteById(long routeId) {
        try {
            routeDao.deleteRouteById(routeId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Route createRouteByRouteNumber(int routeNumber) {
        RouteDao routeDao = new RouteDao();
        Route route = new Route();
        route.setRouteNumber(routeNumber);
        try {
            routeDao.createRoute(route);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return route;
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
            if (route != null) {
                List<RouteStop> routeStops = routeStopDao.getRouteStopsByRoute(route);

                for (RouteStop routeStop : routeStops) {
                    List<StopTime> stopTimes = stopTimeDao.getStopTimesByRouteStop(routeStop);
                    routeStop.setStopTimes(stopTimes);
                }

                route.setStops(routeStops);
            }

        } catch (SQLException e) {
            //todo log
        }
        return route;
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

    public List<Route> getNotLinkedRoutesByUserId(long id) {
        List<Route> routes = null;
        try {
            routes = routeDao.getAllRoutes();
            List<Route> linkedRoutesByUserId = routeDao.getLinkedRoutesByUserId(id);
            Iterator<Route> iterator = routes.iterator();
            while (iterator.hasNext()) {
                Route curRoute = iterator.next();
                for (Route linkedRoute : linkedRoutesByUserId) {
                    if (curRoute.getRouteNumber() == linkedRoute.getRouteNumber()) {
                        iterator.remove();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }

    public List<Route> getLinkedRoutesByUserId(long id) {
        List<Route> routes = null;
        try {
            routes = routeDao.getLinkedRoutesByUserId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return routes;
    }

    public void addRouteUserSub(long routeId, long userId) {
        try {
            routeDao.addRouteUserSub(routeId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteRouteUserSub(long routeId, long userId) {
        try {
            routeDao.deleteRouteUserSub(routeId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
