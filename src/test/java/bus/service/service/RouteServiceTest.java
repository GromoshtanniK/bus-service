package java.bus.service.service;


import bus.service.service.RouteService;
import org.junit.Before;
import org.junit.Test;

import java.bus.service.dao.RouteDaoMock;
import java.bus.service.dao.RouteStopDaoMock;
import java.bus.service.dao.StopTimeDaoMock;

public class RouteServiceTest {

    private RouteDaoMock routeDao;
    private RouteStopDaoMock routeStopDao;
    private StopTimeDaoMock stopTimeDao;

    private RouteService routeService;

    @Before
    public void prepareTestObjects() {
        routeDao = new RouteDaoMock();
        routeStopDao = new RouteStopDaoMock();
        stopTimeDao = new StopTimeDaoMock();

        routeService = new RouteService();
    }

    @Test
    public void testGetRouteByRouteNumber() {
        //todo
    }

    @Test
    public void testSaveNewRoute() {
        //todo
    }

    @Test
    public void testUpdateRoute() {
        //todo
    }
}
