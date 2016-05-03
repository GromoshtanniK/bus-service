package bus.service;


import bus.service.beans.Route;
import bus.service.beans.User;
import bus.service.service.AuthenticationAndAuthorizationService;
import bus.service.service.RouteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class RouteServiceTest {

    private RouteService routeService;
    private AuthenticationAndAuthorizationService authorizationService;

    @Before
    public void init() {
        routeService = new RouteService();
        authorizationService = new AuthenticationAndAuthorizationService();
    }

    @Test
    public void testCreateAndDeleteRoute() {
        int routeNum = 1234;
        Route route = routeService.createRouteByRouteNumber(routeNum);
        Assert.assertNotNull(route.getId());

        route = routeService.getRouteByRouteNumber(routeNum);
        Assert.assertNotNull(route);

        routeService.deleteRouteById(route.getId());
        route = routeService.getRouteByRouteNumber(routeNum);
        Assert.assertNull(route);
    }

    @Test
    public void testRouteUserSub() {
        int routeNum = 1234;
        Route route = routeService.createRouteByRouteNumber(routeNum);
        User user = authorizationService.getUserByUsername("admin");
        Assert.assertNotNull(route);
        Assert.assertNotNull(user);

        List<Route> linkedRoutesByUserId = routeService.getLinkedRoutesByUserId(user.getId());
        Assert.assertTrue(linkedRoutesByUserId.size() == 0);
        routeService.addRouteUserSub(route.getId(), user.getId());
        linkedRoutesByUserId = routeService.getLinkedRoutesByUserId(user.getId());
        Assert.assertTrue(linkedRoutesByUserId.size() != 0);
        List<Route> notLinkedRoutesByUserId = routeService.getNotLinkedRoutesByUserId(user.getId());
        List<Route> allRoutes = routeService.getAllRoutes();
        Assert.assertTrue(allRoutes.size() != notLinkedRoutesByUserId.size());

        routeService.deleteRouteUserSub(route.getId(), user.getId());
        notLinkedRoutesByUserId = routeService.getNotLinkedRoutesByUserId(user.getId());
        Assert.assertTrue(allRoutes.size() == notLinkedRoutesByUserId.size());
        linkedRoutesByUserId = routeService.getLinkedRoutesByUserId(user.getId());
        Assert.assertTrue(linkedRoutesByUserId.size() == 0);

        routeService.deleteRouteById(route.getId());
    }
}
