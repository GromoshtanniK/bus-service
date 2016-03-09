package bus.service.dao;

import bus.service.beans.Route;
import bus.service.db.ColumnNames;
import bus.service.db.Queries;
import bus.service.beans.RouteStop;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteStopDao {


    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createRouteStop(RouteStop routeStop) throws SQLException {
        queryRunner.update(Queries.INSERT_ROUTE_STOP, routeStop.getAltitude(), routeStop.getLatitude());
    }

    public List<RouteStop> getAllRouteStops() throws SQLException {
        return queryRunner.query(Queries.SELECT_ALL_ROUTE_STOPS, new ResultSetHandler<List<RouteStop>>() {
            public List<RouteStop> handle(ResultSet rs) throws SQLException {
                List<RouteStop> routeStops = new ArrayList<RouteStop>();
                while (rs.next()) {
                    RouteStop routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ColumnNames.ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(ColumnNames.LATITUDE_COLUMN));
                    routeStop.setRouteId(rs.getLong(ColumnNames.ROUTE_ID_COLUMN));
                    routeStops.add(routeStop);
                }
                return routeStops;
            }
        });
    }

    public RouteStop getRouteStopById(long id) throws SQLException {
        return queryRunner.query(Queries.SELECT_ROUTE_STOP_BY_ID, new ResultSetHandler<RouteStop>() {
            public RouteStop handle(ResultSet rs) throws SQLException {
                RouteStop routeStop = null;
                while (rs.next()) {
                    routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ColumnNames.ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(ColumnNames.LATITUDE_COLUMN));
                    routeStop.setRouteId(rs.getLong(ColumnNames.ROUTE_ID_COLUMN));
                }
                return routeStop;
            }
        }, id);
    }

    public List<RouteStop> getRouteStopsByRoute(Route route) throws SQLException {
        return queryRunner.query(Queries.SELECT_ROUTE_STOPS_BY_ROUTE, new ResultSetHandler<List<RouteStop>>() {
            public List<RouteStop> handle(ResultSet rs) throws SQLException {
                List<RouteStop> routeStops = new ArrayList<RouteStop>();
                while (rs.next()) {
                    RouteStop routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ColumnNames.ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(ColumnNames.LATITUDE_COLUMN));
                    routeStop.setRouteId(rs.getLong(ColumnNames.ROUTE_ID_COLUMN));
                    routeStops.add(routeStop);
                }
                return routeStops;
            }
        });
    }

    public void deleteRouteStopById(long id) throws SQLException {
        queryRunner.update(Queries.DELETE_ROUTE_STOP_BY_ID, id);
    }

    public void deleteRouteStopsByRoute(Route route) throws SQLException{
        queryRunner.update(Queries.DELETE_ROUTE_STOPS_BY_ROUTE_ID, route.getId());
    }

    public void deleteRouteStop(RouteStop routeStop) throws SQLException {
        deleteRouteStopById(routeStop.getId());
    }

}
