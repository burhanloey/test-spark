package registration;

import com.google.inject.AbstractModule;
import registration.impl.DefaultRegistrationService;

public class RegistrationModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(RegistrationService.class).to(DefaultRegistrationService.class);
    }
}
