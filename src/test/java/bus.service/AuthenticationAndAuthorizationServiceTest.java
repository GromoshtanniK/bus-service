package bus.service;


import bus.service.beans.RegistrationUser;
import bus.service.beans.User;
import bus.service.service.AuthenticationAndAuthorizationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationAndAuthorizationServiceTest {

    private AuthenticationAndAuthorizationService service;

    @Before
    public void init() {
        service = new AuthenticationAndAuthorizationService();
    }

    @Test
    public void saveRegistrationUserTest() {
        RegistrationUser registrationUser = new RegistrationUser();
        registrationUser.setUserName("test");
        registrationUser.setPassword("123");
        registrationUser.setPasswordConfirmed("123");
        registrationUser.setEmail("test@test.com");

        User user = service.saveRegistrationUser(registrationUser);
        Assert.assertNotNull(user.getId());
        user = service.getUserByUsername("test");
        Assert.assertNotNull(user);

        service.deleteUser(user);
        user = service.getUserByUsername("test");
        Assert.assertNull(user);

    }
}
