package routes;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.Gson;
import user.User;
import user.UserService;

import javax.inject.Inject;

import static spark.Spark.get;
import static spark.Spark.post;

public class StaticPageRoute {

    private final Gson gson;
    private final UserService userService;
    private final Cache<String, Object> cache;

    @Inject
    public StaticPageRoute(final Gson gson, final UserService userService, final Cache<String, Object> cache) {
        this.gson = gson;
        this.userService = userService;
        this.cache = cache;
    }

    public void init() {
        get("/", (req, res) -> cache.get("/", k -> "Hello World!"));

        get("/user/:username", (req, res) -> {
            res.type("application/json");

            final String username = req.params(":username");
            return cache.get("/user/" + username, k ->
                    userService.getUser(username).orElse(User.empty()));
        }, gson::toJson);

        post("/user", "application/json", (req, res) -> {
            final User user = gson.fromJson(req.body(), User.class);

            return userService.getUser(user.getUsername())
                    .map(it -> {
                        res.status(409);
                        return "Username taken";
                    })
                    .orElseGet(() -> {
                        userService.add(user);
                        return "Registered";
                    });
        });
    }

}
