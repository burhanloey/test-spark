package user;

import lombok.Builder;
import lombok.Data;

import java.util.Arrays;
import java.util.Set;

import static java.util.Collections.singleton;
import static user.Role.ANONYMOUS;

@Data
@Builder
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final Set<Role> roles;

    public boolean hasRoles(final Role... roles) {
        return this.roles.containsAll(Arrays.asList(roles));
    }

    public static User getAnonymous() {
        return User.builder()
                .roles(singleton(ANONYMOUS))
                .build();
    }

    public static User empty() {
        return User.builder().build();
    }
}
