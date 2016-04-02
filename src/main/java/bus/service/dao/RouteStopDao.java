package bus.service.dao;

import bus.service.beans.Route;
import bus.service.db.ColumnNames;
import bus.service.db.Queries;
import bus.service.beans.RouteStop;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteStopDao {


    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void addRouteStop(RouteStop routeStop) throws SQLException {
        long routeStopId = queryRunner.insert(Queries.INSERT_ROUTE_STOP, new ScalarHandler<Long>(), routeStop.getAltitude(), routeStop.getLatitude(), routeStop.getStopName(),
                routeStop.isBackWay(), routeStop.getRouteId());
        routeStop.setId(routeStopId);
    }

    public List<RouteStop> getRouteStopsByRoute(Route route) throws SQLException {
        return getRouteStopsByRouteId(route.getId());
    }

    public void deleteRouteStopById(long id) throws SQLException {
        queryRunner.update(Queries.DELETE_ROUTE_STOP_BY_ID, id);
    }

    public void deleteRouteStop(RouteStop routeStop) throws SQLException {
        deleteRouteStopById(routeStop.getId());
    }

    public List<RouteStop> getRouteStopsByRouteId(long id) throws SQLException {
        return queryRunner.query(Queries.SELECT_ROUTE_STOPS_BY_ROUTE, new ResultSetHandler<List<RouteStop>>() {
            public List<RouteStop> handle(ResultSet rs) throws SQLException {
                List<RouteStop> routeStops = new ArrayList<RouteStop>();
                while (rs.next()) {
                    RouteStop routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ColumnNames.ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(ColumnNames.LATITUDE_COLUMN));
                    routeStop.setStopName(rs.getString(ColumnNames.STOP_NAME_COLUMN));
                    routeStop.setIsBackWay(rs.getBoolean(ColumnNames.IS_BACK_WAY_COLUMN));
                    routeStop.setRouteId(rs.getLong(ColumnNames.ROUTE_ID_COLUMN));
                    routeStops.add(routeStop);
                }
                return routeStops;
            }
        }, id);
    }

    public void updateRouteStop(RouteStop routeStop) throws SQLException {
        queryRunner.update(Queries.UPDATE_ROUTE_STOP, routeStop.getAltitude(), routeStop.getLatitude(),
                routeStop.getStopName(), routeStop.isBackWay(), routeStop.getId());
    }
}
