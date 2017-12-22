package user.impl;

import org.jdbi.v3.core.Jdbi;
import user.User;
import user.UserDao;
import user.mapper.UserMapper;

import javax.inject.Inject;
import java.util.Optional;

public class DefaultUserDao implements UserDao {

    private final Jdbi jdbi;
    private final UserMapper userMapper;

    @Inject
    public DefaultUserDao(final Jdbi jdbi, final UserMapper userMapper) {
        this.jdbi = jdbi;
        this.userMapper = userMapper;
    }

    @Override
    public Optional<User> fetchOne(final long id) {
        return jdbi.withHandle(handle -> handle
                .createQuery("select * from users where id = :id")
                .bind("id", id)
                .map(userMapper)
                .findFirst());
    }
}