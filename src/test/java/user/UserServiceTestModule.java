package user;

import com.google.inject.AbstractModule;
import user.impl.DefaultUserService;
import user.mapper.UserMapper;
import user.stub.UserDaoStub;

public class UserServiceTestModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(UserMapper.class);
        bind(UserService.class).to(DefaultUserService.class);
        bind(UserDao.class).to(UserDaoStub.class);
    }
}
