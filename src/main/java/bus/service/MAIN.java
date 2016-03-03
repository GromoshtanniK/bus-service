package bus.service;

import bus.service.beans.BusStop;
import bus.service.beans.Route;
import bus.service.dao.BusStopDao;
import bus.service.dao.RouteDao;

import java.sql.SQLException;
import java.util.List;

public class MAIN {
    public static void main(String[] args) throws SQLException {

        BusStop busStop = new BusStop();
        busStop.setAltitude(1452.25);
        busStop.setLatitude(4665.433535);

        BusStopDao busStopDao = new BusStopDao();

        //busStopDao.createBusStop(busStop);

        List<BusStop> busStops = busStopDao.getAllBusStops();


        System.out.println();

    }
}
