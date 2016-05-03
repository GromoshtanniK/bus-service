package bus.service.service;

import bus.service.beans.RegistrationUser;
import bus.service.beans.User;
import bus.service.dao.UserDao;

import java.sql.SQLException;

public class AuthenticationAndAuthorizationService {

    private UserDao userDao = new UserDao();

    public User saveRegistrationUser(RegistrationUser registrationUser) {
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
        return user;
    }

    public User getUserByUsername(String userName) {
        User user = null;
        try {
            user =  userDao.getUserByUsername(userName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public void deleteUser(User user) {
        try {
            userDao.deleteUserById(user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
