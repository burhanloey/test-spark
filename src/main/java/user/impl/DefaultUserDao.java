package user.impl;

import org.jdbi.v3.core.Jdbi;
import user.User;
import user.UserDao;
import user.mapper.UserMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DefaultUserDao implements UserDao {

    private final Jdbi jdbi;

    @Inject
    public DefaultUserDao(final Jdbi jdbi) {
        this.jdbi = jdbi;
    }

    @Override
    public Optional<User> fetchOne(final String username) {
        final List<Map<String, Object>> results = jdbi.withHandle(handle -> handle
                .createQuery("select * from users " +
                        "left join user_role on users.id = user_role.user_id " +
                        "where username = :username")
                .bind("username", username)
                .mapToMap()
                .list());

        return UserMapper.fold(results);
    }

    @Override
    public void insert(final User user) {
        jdbi.useTransaction(handle -> {
            final Long id = handle
                    .createUpdate("insert into users (username, password) values (:username, :password)")
                    .bindBean(user)
                    .executeAndReturnGeneratedKeys("id")
                    .mapTo(Long.class)
                    .findOnly();

            user.getRoles().forEach(role -> {
                handle.createUpdate("insert into user_role (user_id, role) values (:user_id, :role)")
                        .bind("user_id", id)
                        .bind("role", role)
                        .execute();
            });
        });
    }
}
