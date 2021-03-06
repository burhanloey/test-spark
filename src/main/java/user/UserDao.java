package user;

import java.util.Optional;

public interface UserDao {
    Optional<User> fetchOne(String username);
    void insert(User user);
}
