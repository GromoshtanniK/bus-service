package bus.service.web.servlets;

import bus.service.beans.RegistrationUser;
import bus.service.service.AuthenticationAndAuthorizationService;
import bus.service.web.constants.Path;
import bus.service.web.constants.RequestAttributes;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = Path.REGISTRATION_SERVLET)
public class RegistrationServlet extends HttpServlet {

    private AuthenticationAndAuthorizationService authenticationAndAuthorizationService = new AuthenticationAndAuthorizationService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(Path.REGISTRATION_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RegistrationUser registrationUser = getRegistrationUserFromRequest(req);
        RegistrationUserValidation validation = new RegistrationUserValidation(registrationUser);
        if (validation.isValid()) {
            req.getRequestDispatcher(Path.SUCCESSFUL_REGISTRATION_JSP).forward(req, resp);
            authenticationAndAuthorizationService.saveRegistrationUser(registrationUser);
        } else {
            resp.sendRedirect(Path.REGISTRATION_SERVLET + validation.getErrorParameters());
        }
    }

    private RegistrationUser getRegistrationUserFromRequest(HttpServletRequest request) {
        RegistrationUser registrationUser = new RegistrationUser();
        registrationUser.setUserName(request.getParameter(RequestAttributes.USERNAME));
        registrationUser.setEmail(request.getParameter(RequestAttributes.EMAIL));
        registrationUser.setPassword(request.getParameter(RequestAttributes.PASSWORD));
        registrationUser.setPasswordConfirmed(request.getParameter(RequestAttributes.PASSWORD_CONFIRM));
        return registrationUser;
    }

    class RegistrationUserValidation {

        private boolean validUserName;
        private boolean validEmail;
        private boolean validPassword;
        private RegistrationUser registrationUser;

        public RegistrationUserValidation(RegistrationUser registrationUser) {
            this.registrationUser = registrationUser;
            check();
        }

        private void check() {
            checkUserName();
            checkEmail();
            checkPassword();
        }

        private void checkUserName() {
            validUserName = !registrationUser.getUserName().isEmpty();
        }

        private void checkPassword() {
            validPassword = !registrationUser.getPassword().isEmpty()
                && !registrationUser.getPasswordConfirmed().isEmpty()
                && registrationUser.getPassword().equals(registrationUser.getPasswordConfirmed());
        }

        private void checkEmail() {
            validEmail = !registrationUser.getEmail().isEmpty();
        }

        public boolean isValid() {
            return validUserName && validEmail && validPassword;
        }

        public String getErrorParameters() {
            String errorParams = "?";

            if (!validUserName) {
                errorParams = errorParams + "username_error=error&";
            }

            if (!validEmail) {
                errorParams = errorParams + "email_error=error&";
            }

            if (!validPassword) {
                errorParams = errorParams + "password_error=error&";
            }

            errorParams = errorParams + "username=" + registrationUser.getUserName() + "&";
            errorParams = errorParams + "email=" + registrationUser.getEmail();

            return errorParams;
        }
    }
}
