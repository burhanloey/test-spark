package user;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(long id);
}
