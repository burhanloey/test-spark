package routes;

import com.github.benmanes.caffeine.cache.Cache;
import com.google.gson.Gson;
import provider.IntegerRegex;
import user.User;
import user.UserService;

import javax.inject.Inject;
import java.util.regex.Pattern;

import static java.lang.Long.parseLong;
import static spark.Spark.get;
import static spark.Spark.post;

public class StaticPageRoute {

    private final Gson gson;
    private final UserService userService;
    private final Cache<String, Object> cache;
    private final Pattern integerRegex;

    @Inject
    public StaticPageRoute(final Gson gson, final UserService userService, final Cache<String, Object> cache,
                           @IntegerRegex final Pattern integerRegex) {
        this.gson = gson;
        this.userService = userService;
        this.cache = cache;
        this.integerRegex = integerRegex;
    }

    public void init() {
        get("/", (req, res) -> cache.get("/", k -> "Hello World!"));

        get("/user/:param", (req, res) -> {
            res.type("application/json");

            final String param = req.params(":param");
            if (integerRegex.matcher(param).matches()) {
                final long id = parseLong(param);
                return cache.get("/user/" + id, k -> userService.getUser(id).orElse(User.empty()));
            }

            return cache.get("/user/" + param, k -> userService.getUser(param).orElse(User.empty()));
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
