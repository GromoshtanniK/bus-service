package bus.service.service;

import bus.service.beans.Route;

public class RouteService {
    public Route getRouteByNumber(int routeNumber) {
        Route route = new Route();
        route.setRouteNumber(routeNumber);
        return route;
    }
}
