package bus.service.db;

public final class Queries {
    //route
    public static final String INSERT_ROUTE = "INSERT INTO bus_service.route (route_number) VALUES (?)";
    public static final String UPDATE_ROUTE = "UPDATE bus_service.route SET route_number = ? WHERE id = ?";
    public static final String SELECT_ALL_ROUTES = "SELECT * FROM bus_service.route";
    public static final String SELECT_ROUTE_BY_ROUTE_NUMBER = "SELECT * FROM bus_service.route WHERE route_number = ?";
    public static final String SELECT_ROUTE_BY_ID = "SELECT * FROM bus_service.route WHERE id = ?";
    public static final String DELETE_ROUTE_BY_ID = "DELETE FROM bus_service.route WHERE id = ?";
    public static final String DELETE_ROUTE_BY_ROUTE_NUMBER = "DELETE FROM bus_service.route WHERE route_number = ?";

    //route_stop
    public static final String INSERT_ROUTE_STOP = "INSERT INTO bus_service.route_stop " +
            "(altitude, latitude, stop_name, is_back_way, route_id) VALUES (?, ?, ?, ?, ?)";
    public static final String UPDATE_ROUTE_STOP = "UPDATE bus_service.route_stop SET altitude = ?, latitude = ?, " +
            "stop_name = ?, is_back_way = ? WHERE id = ?";
    public static final String SELECT_ALL_ROUTE_STOPS = "SELECT * FROM bus_service.route_stop";
    public static final String SELECT_ROUTE_STOP_BY_ID = "SELECT * FROM bus_service.route_stop WHERE id = ?";
    public static final String SELECT_ROUTE_STOPS_BY_ROUTE = "SELECT * FROM bus_service.route_stop WHERE route_id = ?";
    public static final String DELETE_ROUTE_STOP_BY_ID = "DELETE FROM bus_service.route_stop WHERE id = ?";
    public static final String DELETE_ROUTE_STOPS_BY_ROUTE_ID = "DELETE FROM bus_service.route_stop WHERE route_id = ?";

    //stop_time
    public static final String INSERT_STOP_TIME = "INSERT INTO bus_service.stop_time (hours, minutes, route_stop_id) VALUES (?, ?, ?)";
    public static final String UPDATE_STOP_TIME = "UPDATE bus_service.stop_time SET hours = ?, minutes = ? WHERE id = ?";
    public static final String SELECT_ALL_STOP_TIMES = "SELECT * FROM bus_service.stop_time";
    public static final String SELECT_STOP_TIMES_BY_ROUTE_STOP = "SELECT * FROM bus_service.stop_time WHERE route_stop_id = ?";
    public static final String SELECT_STOP_TIME_BY_ID = "SELECT * FROM bus_service.stop_time WHERE id = ?";
    public static final String DELETE_STOP_TIME_BY_ID = "DELETE FROM bus_service.stop_time WHERE id = ?";
    public static final String DELETE_STOP_TIMES_BY_ROUTE_STOP_ID = "DELETE FROM bus_service.stop_time WHERE route_stop_id = ?";
}