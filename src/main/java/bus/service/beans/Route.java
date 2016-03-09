package bus.service.beans;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private long id;
    private int routeNumber;
    private List<RouteStop> stops/* = new ArrayList<RouteStop>()*/;

    public List<RouteStop> getStops() {
        return stops;
    }

    public void setStops(List<RouteStop> stops) {
        this.stops = stops;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }
}
