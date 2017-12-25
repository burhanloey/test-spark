package user.mapper;

import user.Role;
import user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class UserMapper {

    private static Set<Role> collectRoles(final List<Map<String, Object>> results) {
        return results.stream()
                .filter(it -> it.get("role") != null)
                .map(it -> {
                    final String role = (String) it.get("role");
                    return Role.valueOf(role);
                })
                .collect(toSet());
    }

    /**
     * Fold results of users and user_role join query into User object with one-to-many relationship with Role,
     * wrapped in Optional.
     *
     * @param results Query results
     * @return Optional of User
     */
    public static Optional<User> fold(final List<Map<String, Object>> results) {
        if (results.isEmpty()) {
            return Optional.empty();
        }

        final Set<Role> roles = collectRoles(results);

        final Map<String, Object> anyResult = results.get(0);

        final User user = User.builder()
                .id((Long) anyResult.get("id"))
                .username((String) anyResult.get("username"))
                .password((String) anyResult.get("password"))
                .roles(roles)
                .build();

        return Optional.of(user);
    }
}
