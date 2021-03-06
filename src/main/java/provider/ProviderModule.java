package provider;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.gson.Gson;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;

import javax.inject.Singleton;
import javax.sql.DataSource;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

public class ProviderModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(Pattern.class)
                .annotatedWith(IntegerRegex.class)
                .toProvider(IntegerRegexProvider.class)
                .in(Singleton.class);
    }

    @Provides @Singleton
    Gson provideGson() {
        return new Gson();
    }

    @Provides @Singleton
    Cache<String, Object> provideCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(5, TimeUnit.MINUTES)
                .build();
    }

    @Provides @Singleton
    DataSource provideDataSource() {
        HikariConfig config = new HikariConfig("/hikari/hikari.properties");
        config.setJdbcUrl("jdbc:mysql://localhost:3306/test_spark?useSSL=false");
        return new HikariDataSource(config);
    }

    @Provides @Singleton
    Jdbi provideJdbi(final DataSource dataSource) {
        return Jdbi.create(dataSource);
    }
}
