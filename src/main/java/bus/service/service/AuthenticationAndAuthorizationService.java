package bus.service.service;

import bus.service.beans.User;

public class AuthenticationAndAuthorizationService {
    public boolean isValidCredentials(User user) {
        if (user.getUserName().equals("admin") && user.getPassword().equals("password")) {
            user.setRole(User.DISPATCHER);
            return true;
        }
        return false;
    }
}
