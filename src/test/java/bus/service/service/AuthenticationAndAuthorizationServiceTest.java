package java.bus.service.service;

import bus.service.service.AuthenticationAndAuthorizationService;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by andrey on 12/03/16.
 */
public class AuthenticationAndAuthorizationServiceTest {

    private AuthenticationAndAuthorizationService authorizationService;

    @Before
    public void prepareTestObjects() {
        authorizationService = new AuthenticationAndAuthorizationService();
    }


    @Test
    public void testIsValidCredentials() {
        //todo
    }
}
