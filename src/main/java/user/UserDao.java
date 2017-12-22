package user;

import java.util.Optional;

public interface UserDao {
    Optional<User> fetchOne(long id);
}
