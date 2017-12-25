import com.google.inject.Guice;
import com.google.inject.Injector;
import provider.ProviderModule;
import registration.RegistrationModule;
import routes.UserApiRoute;
import user.UserModule;

import static spark.Spark.port;

public class Main {
    public static void main(String[] args) {
        port(3000);

        Injector app = Guice.createInjector(new UserModule(), new RegistrationModule(), new ProviderModule());
        app.getInstance(UserApiRoute.class).init();
    }
}
