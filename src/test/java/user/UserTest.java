package user;

import com.google.common.collect.ImmutableSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static user.Role.*;

public class UserTest {

    private User user;

    @Before
    public void init() {
        user = User.builder()
                .roles(ImmutableSet.of(ADMIN, SUBSCRIBER))
                .build();
    }

    @Test
    public void testHasAnyRole() {
        assertTrue(user.hasAnyRole(ADMIN, ANONYMOUS));
        assertTrue(user.hasAnyRole(ADMIN, SUBSCRIBER));
        assertTrue(user.hasAnyRole(SUBSCRIBER, ANONYMOUS));
        assertTrue(user.hasAnyRole(Role.values()));
        assertFalse(user.hasAnyRole(ANONYMOUS));
    }

    @Test
    public void testHasRoles() {
        assertTrue(user.hasRoles(ADMIN, SUBSCRIBER));
        assertTrue(user.hasRoles(ADMIN));
        assertFalse(user.hasRoles(ADMIN, ANONYMOUS));
        assertFalse(user.hasRoles(ANONYMOUS));
        assertFalse(user.hasRoles(Role.values()));
    }

    @Test
    public void testEmpty() {
        final User emptyUser = User.empty();

        assertNull(emptyUser.getId());
        assertNull(emptyUser.getUsername());
        assertNull(emptyUser.getPassword());
        assertNull(emptyUser.getRoles());
    }
}
