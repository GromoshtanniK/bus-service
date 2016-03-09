package bus.service.db;

public abstract class Queries {
    public static final String INSERT_ROUTE = "INSERT INTO bus_service.route (route_number, forward) VALUES (?, ?)";
    public static final String SELECT_ALL_ROUTES = "SELECT * FROM bus_service.route";
    public static final String SELECT_ROUTE_BY_ID = "SELECT * FROM bus_service.route WHERE id = ?";
    public static final String DELETE_ROUTE_BY_ID = "DELETE FROM bus_service.route WHERE id = ?";

    public static final String INSERT_ROUTE_STOP = "INSERT INTO bus_service.bus_stop (altitude, latitude) VALUES (?, ?)";
    public static final String SELECT_ALL_ROUTE_STOPS = "SELECT * FROM bus_service.bus_stop";
    public static final String SELECT_ROUTE_STOP_BY_ID = "SELECT * FROM bus_service.bus_stop WHERE id = ?";
    public static final String DELETE_ROUTE_STOP_BY_ID = "DELETE FROM bus_service.bus_stop WHERE id = ?";
    public static final String SELECT_ROUTE_STOPS_BY_ROUTE = "SELECT * FROM bus_service.bus_stop WHERE route_id = ?";
    public static final String DELETE_ROUTE_STOPS_BY_ROUTE_ID = "DELETE FROM bus_service.bus_stop WHERE route_id = ?";
}