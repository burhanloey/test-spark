package registration.impl;

import registration.RegistrationService;
import user.User;
import user.UserService;

import javax.inject.Inject;

public class DefaultRegistrationService implements RegistrationService {

    private final UserService userService;

    @Inject
    public DefaultRegistrationService(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean registerUser(final User user) {
        return userService.getUser(user.getUsername())
                .map(it -> false)  // Username already exists
                .orElseGet(() -> {
                    userService.add(user);
                    return true;
                });
    }
}
