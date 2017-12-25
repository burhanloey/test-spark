package user;

import java.util.Optional;

public interface UserService {
    Optional<User> getUser(String username);
    void add(User user);
}
