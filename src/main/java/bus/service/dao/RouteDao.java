package bus.service.dao;

import bus.service.db.ColumnNames;
import bus.service.db.Queries;
import bus.service.beans.Route;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {
        QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createRoute(Route route) throws SQLException {
        queryRunner.update(Queries.INSERT_ROUTE, route.getRouteNumber());
    }

    public List<Route> getAllRoutes() throws SQLException {
        return queryRunner.query(Queries.SELECT_ALL_ROUTES, new ResultSetHandler<List<Route>>() {
            public List<Route> handle(ResultSet rs) throws SQLException {
                List<Route> routes = new ArrayList<Route>();
                while (rs.next()) {
                    Route route = new Route();
                    route.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    route.setRouteNumber(rs.getInt(ColumnNames.ROUTE_NUMBER_COLUMN));
                    //todo
                    //route.getStops().addAll(rs.getObject(ColumnNames.??? ,ArrayList.class));
                    routes.add(route);
                }
                return routes;
            }
        });
    }

    public Route getRouteById(long id) throws SQLException {
        return queryRunner.query(Queries.SELECT_ROUTE_BY_ID, new ResultSetHandler<Route>() {
            public Route handle(ResultSet rs) throws SQLException {
                Route route = null;
                while (rs.next()) {
                    route = new Route();
                    route.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    route.setRouteNumber(rs.getInt(ColumnNames.ROUTE_NUMBER_COLUMN));
                    //todo
                    //route.getStops().addAll(rs.getObject(ColumnNames.??? ,ArrayList.class));
                }
                return route;
            }
        }, id);
    }

    public Route getRouteByRouteNumber(int routeNumber) throws SQLException {
        Route route = new Route();
        route.setRouteNumber(routeNumber);
        return route;
    }

    public void deleteRouteById(long id) throws SQLException {
        queryRunner.update(Queries.DELETE_ROUTE_BY_ID, id);
    }

    public void deleteRoute(Route route) throws SQLException {
        deleteRouteById(route.getId());
    }
}
