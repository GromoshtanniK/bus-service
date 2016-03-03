package bus.service.dao;

import bus.service.SQL;
import bus.service.beans.RouteStop;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteStopDao {

    private static final String ID_COLUMN = "id";
    private static final String ALTITUDE_COLUMN = "altitude";
    private static final String LATITUDE_COLUMN = "latitude";

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createRouteStop(RouteStop routeStop) throws SQLException {
        queryRunner.update(SQL.INSERT_ROUTE_STOP, routeStop.getAltitude(), routeStop.getLatitude());
    }

    public List<RouteStop> getAllRouteStops() throws SQLException {
        return queryRunner.query(SQL.SELECT_ALL_ROUTE_STOPS, new ResultSetHandler<List<RouteStop>>() {
            public List<RouteStop> handle(ResultSet rs) throws SQLException {
                List<RouteStop> routeStops = new ArrayList<RouteStop>();
                while (rs.next()) {
                    RouteStop routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(LATITUDE_COLUMN));
                    routeStops.add(routeStop);
                }
                return routeStops;
            }
        });
    }

    public RouteStop getRouteStopById(long id) throws SQLException {
        return queryRunner.query(SQL.SELECT_ROUTE_STOP_BY_ID, new ResultSetHandler<RouteStop>() {
            public RouteStop handle(ResultSet rs) throws SQLException {
                RouteStop routeStop = null;
                while (rs.next()) {
                    routeStop = new RouteStop();
                    routeStop.setId(rs.getLong(ID_COLUMN));
                    routeStop.setAltitude(rs.getDouble(ALTITUDE_COLUMN));
                    routeStop.setLatitude(rs.getDouble(LATITUDE_COLUMN));
                }
                return routeStop;
            }
        }, id);
    }

    public void deleteRouteStopById(long id) throws SQLException {
        queryRunner.update(SQL.DELETE_ROUTE_STOP_BY_ID, id);
    }

    public void deleteRouteStop(RouteStop routeStop) throws SQLException {
        deleteRouteStopById(routeStop.getId());
    }

}
