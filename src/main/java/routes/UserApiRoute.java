package routes;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.Gson;
import registration.RegistrationService;
import user.User;
import user.UserService;

import javax.inject.Inject;

import static spark.Spark.*;

public class UserApiRoute {

    private final Gson gson;
    private final UserService userService;
    private final RegistrationService registrationService;
    private final Cache<String, Object> cache;

    @Inject
    public UserApiRoute(final Gson gson, final UserService userService,
                        final RegistrationService registrationService, final Cache<String, Object> cache) {
        this.gson = gson;
        this.userService = userService;
        this.registrationService = registrationService;
        this.cache = cache;
    }

    public void init() {
        path("/user", () -> {
            get("/:username", (req, res) -> {
                res.type("application/json");

                final String username = req.params(":username");
                return cache.get(req.pathInfo(), k ->
                        userService.getUser(username).orElse(User.empty()));
            }, gson::toJson);

            post("", "application/json", (req, res) -> {
                final User user = gson.fromJson(req.body(), User.class);

                final boolean isRegistered = registrationService.registerUser(user);
                if (isRegistered) {
                    return "Registered";
                }

                res.status(409);
                return "Username taken";
            });
        });
    }

}
