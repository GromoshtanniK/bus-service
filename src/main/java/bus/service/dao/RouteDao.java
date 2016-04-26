package bus.service.dao;

import bus.service.db.ColumnNames;
import bus.service.db.Queries;
import bus.service.beans.Route;
import bus.service.db.DB;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RouteDao {

    QueryRunner queryRunner = new QueryRunner(DB.getDataSource());

    public void createRoute(final Route route) throws SQLException {
        long routeId = queryRunner.insert(Queries.INSERT_ROUTE, new ScalarHandler<Long>(), route.getRouteNumber());
        route.setId(routeId);
    }

    public List<Route> getAllRoutes() throws SQLException {
        return queryRunner.query(Queries.SELECT_ALL_ROUTES, new ResultSetHandler<List<Route>>() {
            public List<Route> handle(ResultSet rs) throws SQLException {
                List<Route> routes = new ArrayList<Route>();
                while (rs.next()) {
                    Route route = new Route();
                    route.setId(rs.getLong(ColumnNames.ID_COLUMN));
                    route.setRouteNumber(rs.getInt(ColumnNames.ROUTE_NUMBER_COLUMN));
                    routes.add(route);
                }
                return routes;
            }
        });
    }

    public Route getRouteByRouteNumber(int routeNumber) throws SQLException {
        return queryRunner.query(Queries.SELECT_ROUTE_BY_ROUTE_NUMBER, new ResultSetHandler<Route>() {
            public Route handle(ResultSet resultSet) throws SQLException {
                Route route = null;
                while (resultSet.next()) {
                    route = new Route();
                    route.setId(resultSet.getLong(ColumnNames.ID_COLUMN));
                    route.setRouteNumber(resultSet.getInt(ColumnNames.ROUTE_NUMBER_COLUMN));
                }
                return route;
            }
        }, routeNumber);
    }

    public void updateRoute(Route route) throws SQLException {
        queryRunner.update(Queries.UPDATE_ROUTE, route.getRouteNumber(), route.getId());
    }

    public void deleteRouteById(long routeId) throws SQLException {
        queryRunner.update(Queries.DELETE_ROUTE_BY_ID, routeId);
    }

    public List<Route> getLinkedRoutesByUserId(long userId) throws SQLException {
        return  queryRunner.query(Queries.SELECT_LINKED_ROUTES_BY_USER_ID, new ResultSetHandler<List<Route>>() {
            public List<Route> handle(ResultSet resultSet) throws SQLException {
                List<Route> routes = new ArrayList<Route>();
                while (resultSet.next()) {
                    Route route = new Route();
                    route.setId(resultSet.getLong(ColumnNames.ID_COLUMN));
                    route.setRouteNumber(resultSet.getInt(ColumnNames.ROUTE_NUMBER_COLUMN));
                    routes.add(route);
                }
                return routes;
            }
        }, userId);
    }
}
