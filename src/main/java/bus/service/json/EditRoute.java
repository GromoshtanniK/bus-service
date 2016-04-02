package bus.service.json;

import bus.service.beans.RouteStop;

import java.util.ArrayList;
import java.util.List;

public class EditRoute {

    private int routeNumber;

    private List<RouteStop> deleted = new ArrayList<RouteStop>();
    private List<RouteStop> added = new ArrayList<RouteStop>();
    private List<ChangedStop> changed = new ArrayList<ChangedStop>();


    public int getRouteNumber() {
        return routeNumber;
    }

    public void setRouteNumber(int routeNumber) {
        this.routeNumber = routeNumber;
    }

    public List<RouteStop> getDeletedStopRoutes() {
        return deleted;
    }

    public void setDeleted(List<RouteStop> deleted) {
        this.deleted = deleted;
    }

    public List<RouteStop> getAddedRouteStops() {
        return added;
    }

    public void setAdded(List<RouteStop> added) {
        this.added = added;
    }

    public List<ChangedStop> getChanged() {
        return changed;
    }

    public void setChanged(List<ChangedStop> changed) {
        this.changed = changed;
    }
}
