package bus.service.service;


import bus.service.dao.RouteDaoMock;
import bus.service.dao.RouteStopDaoMock;
import bus.service.dao.StopTimeDaoMock;
import org.junit.Before;
import org.junit.Test;

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
