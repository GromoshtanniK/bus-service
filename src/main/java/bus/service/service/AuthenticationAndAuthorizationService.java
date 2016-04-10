package bus.service.service;

import bus.service.beans.RegistrationUser;
import bus.service.beans.User;
import bus.service.dao.UserDao;

import java.sql.SQLException;

public class AuthenticationAndAuthorizationService {

    private UserDao userDao = new UserDao();

    public boolean isValidCredentials(User user) {
        if (user.getUserName().equals("admin") && user.getPassword().equals("password")) {
            user.setRole(User.DISPATCHER);
            return true;
        }
        return false;
    }

    public void saveRegistrationUser(RegistrationUser registrationUser) {
        User user = new User();
        user.setUserName(registrationUser.getUserName());
        user.setPassword(registrationUser.getPassword());
        user.setEmail(registrationUser.getEmail());
        user.setRole(User.REGISTERED_USER);
        try {
            user.setId(userDao.saveUser(user));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
