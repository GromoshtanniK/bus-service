package bus.service.web.filters;

import bus.service.beans.User;
import bus.service.web.constants.Path;
import bus.service.web.constants.SessionAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = Path.PROFILE_SERVLET + "/*")
public class ProfileFilter implements Filter {


    public void init(FilterConfig filterConfig) throws ServletException {
    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(SessionAttributes.USER);

        if (user.isGuest()) {
            response.sendRedirect(Path.LOGIN_SERVLET);
        } else {
            chain.doFilter(request, response);
        }

    }

    public void destroy() {

    }
}
