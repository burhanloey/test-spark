package user.stub;

import user.User;
import user.UserDao;

import java.util.Optional;

public class UserDaoStub implements UserDao {
    @Override
    public Optional<User> fetchOne(String username) {
        return Optional.empty();
    }

    @Override
    public void insert(User user) {

    }
}
