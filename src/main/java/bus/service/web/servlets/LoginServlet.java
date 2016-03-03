package bus.service.web.servlets;

import bus.service.beans.User;
import bus.service.service.AuthenticationAndAuthorizationService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;
import bus.service.web.constants.SessionAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(urlPatterns = Path.LOGIN_SERVLET)
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isLogout = req.getParameter(RequestAttributes.LOG_OUT) != null;

        if (isLogout) {
            req.getSession().removeAttribute(SessionAttributes.USER);
        }

        req.getRequestDispatcher(Path.LOGIN_JSP).forward(req, resp);
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = getUserFromRequest(req);
        AuthenticationAndAuthorizationService service = new AuthenticationAndAuthorizationService();

        if (service.isValidCredentials(user)) {
            HttpSession session = req.getSession();
            session.setAttribute(SessionAttributes.USER, user);
            resp.sendRedirect(Path.PROFILE_SERVLET);
        } else {
            resp.sendRedirect(Path.LOGIN_SERVLET + "?error=visible");
        }
    }

    private User getUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setUserName(request.getParameter(RequestAttributes.USERNAME));
        user.setPassword(request.getParameter(RequestAttributes.PASSWORD));
        return user;
    }
}
