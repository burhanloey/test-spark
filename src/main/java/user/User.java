package user;

import lombok.Builder;
import lombok.Data;

import static user.Role.ANONYMOUS;

@Data
@Builder
public class User {
    private final Long id;
    private final String username;
    private final String password;
    private final Role role;

    public static User getAnonymous() {
        return User.builder()
                .role(ANONYMOUS)
                .build();
    }

    public static User empty() {
        return User.builder().build();
    }
}
