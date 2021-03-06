package bus.service.web.servlets;

import bus.service.beans.User;
import bus.service.service.AuthenticationAndAuthorizationService;
import bus.service.service.RouteService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;
import bus.service.web.constants.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Path.PROFILE_SERVLET)
public class ProfileServlet extends HttpServlet {

    private RouteService routeService = new RouteService();
    private AuthenticationAndAuthorizationService authenticationAndAuthorizationService = new AuthenticationAndAuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(SessionAttributes.USER);
        req.setAttribute(RequestAttributes.LINKED_ROUTES, routeService.getLinkedRoutesByUserId(user.getId()));
        req.setAttribute(RequestAttributes.NOT_LINKED_ROUTES, routeService.getNotLinkedRoutesByUserId(user.getId()));
        req.getRequestDispatcher(Path.PROFILE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute(SessionAttributes.USER);
        String addRouteId = req.getParameter("add");
        if (addRouteId != null) {
            routeService.addRouteUserSub(Long.valueOf(addRouteId), user.getId());
        }

        String deleteRouteId = req.getParameter("delete");
        if (deleteRouteId != null) {
            routeService.deleteRouteUserSub(Long.valueOf(deleteRouteId), user.getId());
        }
    }
}
