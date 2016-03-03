package bus.service.dao;

import bus.service.SQL;
import bus.service.beans.Route;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {

    private static final String ID_COLUMN = "id";
    private static final String BUS_NUMBER_COLUMN = "route_number";
    private static final String FORWARD_COLUMN = "forward";

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createRoute(Route route) throws SQLException {
        queryRunner.update(SQL.INSERT_ROUTE, route.getBusNumber(), route.isForward());
    }

    public List<Route> getAllRoutes() throws SQLException {
        return queryRunner.query(SQL.SELECT_ALL_ROUTES, new ResultSetHandler<List<Route>>() {
            public List<Route> handle(ResultSet rs) throws SQLException {
                List<Route> routes = new ArrayList<Route>();
                while (rs.next()) {
                    Route route = new Route();
                    route.setId(rs.getLong(ID_COLUMN));
                    route.setBusNumber(rs.getInt(BUS_NUMBER_COLUMN));
                    route.setForward(rs.getBoolean(FORWARD_COLUMN));
                    routes.add(route);
                }
                return routes;
            }
        });
    }

    public Route getRouteById(long id) throws SQLException {
        return queryRunner.query(SQL.SELECT_ROUTE_BY_ID, new ResultSetHandler<Route>() {
            public Route handle(ResultSet rs) throws SQLException {
                Route route = null;
                while (rs.next()) {
                    route = new Route();
                    route.setId(rs.getLong(ID_COLUMN));
                    route.setBusNumber(rs.getInt(BUS_NUMBER_COLUMN));
                    route.setForward(rs.getBoolean(FORWARD_COLUMN));
                }
                return route;
            }
        }, id);
    }

    public void deleteRouteById(long id) throws SQLException {
        queryRunner.update(SQL.DELETE_ROUTE_BY_ID, id);
    }

    public void deleteRoute(Route route) throws SQLException {
        deleteRouteById(route.getId());
    }
}
