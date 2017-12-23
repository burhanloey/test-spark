package user;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static user.Role.ADMIN;

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
        final User admin = User.builder()
                .id(1L)
                .username("superadmin")
                .password("password")
                .role(ADMIN)
                .build();

        final long randomId = 1 + Math.abs(random.nextLong());

        assertEquals(userService.getUser(1), Optional.of(admin));
        assertEquals(userService.getUser(0), Optional.empty());
        assertEquals(userService.getUser(randomId), Optional.empty());
    }
}
