package user.stub;

import user.User;
import user.UserDao;

import java.util.Optional;

import static user.Role.ADMIN;

public class UserDaoStub implements UserDao {
    @Override
    public Optional<User> fetchOne(final long id) {
        if (id == 1) {
            final User admin = User.builder()
                    .id(1L)
                    .username("superadmin")
                    .password("password")
                    .role(ADMIN)
                    .build();

            return Optional.of(admin);
        }

        return Optional.empty();
    }
}
