package bus.service;

public abstract class SQL {
    public static final String INSERT_ROUTE = "INSERT INTO bus_service.route (route_number, forward) VALUES (?, ?)";
    public static final String SELECT_ALL_ROUTES = "SELECT * FROM bus_service.route";
    public static final String SELECT_ROUTE_BY_ID = "SELECT * FROM bus_service.route WHERE id = ?";
    public static final String DELETE_ROUTE_BY_ID = "DELETE FROM bus_service.route WHERE id = ?";

    public static final String INSERT_BUS_STOP = "INSERT INTO bus_service.bus_stop (altitude, latitude) VALUES (?, ?)";
    public static final String SELECT_ALL_BUS_STOPS = "SELECT * FROM bus_service.bus_stop";
    public static final String SELECT_BUS_STOP_BY_ID = "SELECT * FROM bus_service.bus_stop WHERE id = ?";
    public static final String DELETE_BUS_STOP_BY_ID = "DELETE FROM bus_service.bus_stop WHERE id = ?";
}
