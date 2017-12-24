package user;

import com.google.inject.AbstractModule;
import user.impl.DefaultUserDao;
import user.impl.DefaultUserService;

public class UserModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserDao.class).to(DefaultUserDao.class);
        bind(UserService.class).to(DefaultUserService.class);
    }
}
