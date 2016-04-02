package bus.service.service;

import bus.service.beans.RouteStop;
import bus.service.beans.StopTime;
import bus.service.dao.RouteStopDao;
import bus.service.dao.StopTimeDao;
import bus.service.json.EditRoute;

import java.sql.SQLException;
import java.util.List;

public class EditRouteService {

    RouteStopDao routeStopDao = new RouteStopDao();
    StopTimeDao stopTimeDao = new StopTimeDao();

    public void applyEditRouteChanges(EditRoute editRoute) {
        addRouteStops(editRoute.getAddedRouteStops());
        deleteRouteStops(editRoute.getDeletedStopRoutes());
    }

    private void deleteRouteStops(List<RouteStop> routeStops) {
        for (RouteStop routeStop : routeStops) {
            try {
                routeStopDao.deleteRouteStop(routeStop);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void addRouteStops(List<RouteStop> routeStops) {

        for (RouteStop routeStop : routeStops) {
            try {
                routeStopDao.addRouteStop(routeStop);

                for (StopTime stopTime : routeStop.getStopTimes()) {
                    stopTime.setRouteStopId(routeStop.getId());
                    stopTimeDao.addStopTime(stopTime);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
