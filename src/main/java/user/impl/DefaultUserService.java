package user.impl;

import user.User;
import user.UserDao;
import user.UserService;

import javax.inject.Inject;
import java.util.Optional;

public class DefaultUserService implements UserService {

    private final UserDao userDao;

    @Inject
    public DefaultUserService(final UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<User> getUser(final String username) {
        return userDao.fetchOne(username);
    }

    @Override
    public void add(final User user) {
        userDao.insert(user);
    }
}
