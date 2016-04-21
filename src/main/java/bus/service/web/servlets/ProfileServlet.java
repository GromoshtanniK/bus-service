package bus.service.web.servlets;

import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Path.PROFILE_SERVLET)
public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute(); сортированые роуты, см jsp
        req.getRequestDispatcher(Path.PROFILE_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String addRouteNumber = req.getParameter("add");
        if (addRouteNumber != null) {
            add(addRouteNumber);
        }

        String deleteRouteId = req.getParameter("delete");
        if (deleteRouteId != null) {
            delete(deleteRouteId);
        }
    }

    private void delete(String routeId) {
        System.out.println(routeId);
    }


    private void add(String routeId) {
        System.out.println(routeId);
    }
}
