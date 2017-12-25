package user;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

public class UserServiceTest {

    private UserService userService;
    private Random random;

    @Before
    public void init() {
        Injector injector = Guice.createInjector(new UserServiceTestModule());
        userService = injector.getInstance(UserService.class);
        random = new Random();
    }

    @Test
    public void testGetUser() {
    }
}
