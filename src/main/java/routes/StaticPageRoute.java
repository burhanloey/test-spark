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

        get("/user/:id", (req, res) -> {
            res.type("application/json");

            final String idParam = req.params(":id");
            if (!integerRegex.matcher(idParam).matches()) {
                return User.empty();
            }

            final long id = parseLong(idParam);
            return cache.get("/user/" + id, k -> userService.getUser(id).orElse(User.empty()));
        }, gson::toJson);
    }

}
